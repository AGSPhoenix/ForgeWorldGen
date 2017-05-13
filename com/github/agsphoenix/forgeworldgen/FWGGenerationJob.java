package com.github.agsphoenix.forgeworldgen;

import java.util.ArrayList;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

//@Mod.EventBusSubscriber
class FWGGenerationJob {
	Ticket ticket;
	ArrayList<ChunkPos> chunks;
	ICommandSender sender;
	
	
	FWGGenerationJob(World world, int lowX, int lowZ, int highX, int highZ, ICommandSender sender){
		ticket = ForgeChunkManager.requestTicket(ForgeWorldGen.instance, world, Type.NORMAL);
		chunks = new ArrayList<ChunkPos>(Math.abs(highX-lowX)*Math.abs(highZ-lowZ));
		this.sender = sender;
		
		MinecraftForge.EVENT_BUS.register(this);
		for (int i = lowX; i < highX; i++) {
			for (int j = lowZ; j < highZ; j++) {
				// is this an off by one? FIXME
				chunks.add(new ChunkPos(i,j));
			}
		}
		
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void serverTickEvent(TickEvent.ServerTickEvent e) {
		if (e.phase == Phase.START){return;}
		
		//load some more chunks
		
		ChunkPos here = chunks.get(0);
		ChunkPos south = new ChunkPos(here.chunkXPos,here.chunkZPos+1);
		ChunkPos east = new ChunkPos(here.chunkXPos+1,here.chunkZPos);
		ChunkPos southeast = new ChunkPos(here.chunkXPos+1,here.chunkZPos+1);
		
		ForgeChunkManager.forceChunk(ticket, here);
		ForgeChunkManager.forceChunk(ticket, south);
		ForgeChunkManager.forceChunk(ticket, east);
		ForgeChunkManager.forceChunk(ticket, southeast);
		
		feedback("Processed "+here.toString());
		
		chunks.remove(0);
		
		if (chunks.isEmpty()) {
			feedback("Generation job complete.");
			MinecraftForge.EVENT_BUS.unregister(this);
			//I think this should remove the only reference to the current job, and it'll get GC'd
			
			//FIXME This should probably wait a few ticks to allow settling 
			for (ChunkPos chunkPos : ticket.getChunkList()) {
				ForgeChunkManager.unforceChunk(ticket, chunkPos);
			}
		}
		
		
	}
	
	private void feedback(String s){
		if (sender.sendCommandFeedback()) {
			sender.sendMessage(new TextComponentString(s));
		}
	}
}
