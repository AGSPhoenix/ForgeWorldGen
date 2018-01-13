package com.github.agsphoenix.forgeworldgen;

import java.util.ArrayList;

import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

//@Mod.EventBusSubscriber
public class FWGGenerationJob {
	Ticket ticket;
	ArrayList<ChunkCoordIntPair> chunks;
	ICommandSender sender;
	
	
	FWGGenerationJob(World world, int lowX, int lowZ, int highX, int highZ, ICommandSender sender){
		this.ticket = ForgeChunkManager.requestTicket(ForgeWorldGen.instance, world, Type.NORMAL);
		this.ticket.setChunkListDepth(20);
		this.chunks = new ArrayList<ChunkCoordIntPair>(Math.abs(highX-lowX)*Math.abs(highZ-lowZ));
		this.sender = sender;
		
//		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		for (int i = lowX; i < highX; i++) {
			for (int j = lowZ; j < highZ; j++) {
				// is this an off by one? FIXME
				chunks.add(new ChunkCoordIntPair(i,j));
			}
		}
		
	}
	
//	@SubscribeEvent(priority=EventPriority.LOWEST)
	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent e) {
//		System.out.println("Server tick");
		if (e.phase == Phase.START){return;}
		
		//load some more chunks
		
		ChunkCoordIntPair here = chunks.get(0);
		ChunkCoordIntPair south = new ChunkCoordIntPair(here.chunkXPos,here.chunkZPos+1);
		ChunkCoordIntPair east = new ChunkCoordIntPair(here.chunkXPos+1,here.chunkZPos);
		ChunkCoordIntPair southeast = new ChunkCoordIntPair(here.chunkXPos+1,here.chunkZPos+1);
		
		ForgeChunkManager.forceChunk(ticket, here);
		ForgeChunkManager.forceChunk(ticket, south);
		ForgeChunkManager.forceChunk(ticket, east);
		ForgeChunkManager.forceChunk(ticket, southeast);
		
		feedback("Processed "+here.toString());
		
		chunks.remove(0);
		
		if (chunks.isEmpty()) {
			feedback("Generation job complete.");
//			MinecraftForge.EVENT_BUS.unregister(this);
			FMLCommonHandler.instance().bus().unregister(this);
			//I think this should remove the only reference to the current job, and it'll get GC'd
			
			//FIXME This should probably wait a few ticks to allow settling 
			for (ChunkCoordIntPair chunkPos : ticket.getChunkList()) {
				ForgeChunkManager.unforceChunk(ticket, chunkPos);
			}
		}
		
		
	}
	
	private void feedback(String s){
//		if (sender.sendCommandFeedback()) {
			sender.addChatMessage(new ChatComponentText(s));
//		}
	}
}
