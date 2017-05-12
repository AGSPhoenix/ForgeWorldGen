package com.github.agsphoenix.forgeworldgen;

import java.util.Iterator;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.server.command.ForgeCommand;

class ForgeWorldGenCommand extends CommandBase {
    /**
     * Gets the name of the command
     */
    @Override
    public String getName()
    {
        return "fwg";
    }
    
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
    	// TODO
    	return true;
	}
    
	@Override
	public String getUsage(ICommandSender sender) {
//		return "/fwg <x> <y> <dimension>";
		return "what is the usage? it's a difficult question because usage is impossible to describe. one might ask the same about birds. what are birds? we just don't know.";
	}
	
	@Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length != 5)
        {
            throw new WrongUsageException("/fwg worldID lowX lowY highX highX");
        }
        
        //TODO
        int lowX = Integer.parseInt(args[1]);
        int lowY = Integer.parseInt(args[2]);
        int highX = Integer.parseInt(args[3]);
        int highY = Integer.parseInt(args[4]);
        
        new FWGGenerationJob(getWorldFromName(args[0]), lowX, lowY, highX, highY, sender);        
    }
	
	World getWorldFromName(String s){
//    	WorldServer[] Worlds = DimensionManager.getWorlds();
//    	for (WorldServer world : Worlds)
//    	{
//    		if (world.getWorldInfo().getWorldName() == s)
//    		{
//    			return world;
//    		}
//    	}
    	return DimensionManager.getWorld(Integer.parseInt(s));
    	}
}
