package com.github.agsphoenix.forgeworldgen;

import java.util.List;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class FWGLoadingCallback implements LoadingCallback {

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		//I think this only gets called when the world is loading, not when each chunk gets loaded successfully, so this should only be
		//leftovers from a shutdown while generating so just discard the chunks.
		ForgeWorldGen.logger.warn("ticketsLoaded called; if this happens after the initial server startup, I misunderstood how this works and bad things will happen");
		for (Ticket ticket : tickets) {
			for (ChunkCoordIntPair chunk : ticket.getChunkList()) {
				ForgeChunkManager.unforceChunk(ticket, chunk);
			}
		}
	}
}