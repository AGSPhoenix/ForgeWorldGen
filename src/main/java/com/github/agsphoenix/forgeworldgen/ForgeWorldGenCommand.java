package com.github.agsphoenix.forgeworldgen;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
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
    public String getCommandName()
    {
        return "fwg";
    }
    
//    @Override
//    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
//    {
//    	if (MinecraftServer.class.isInstance(sender)){return true;}
//    	if (EntityPlayerMP.class.isInstance(sender)){
//    		//FIXME check if player is OP
//    		
//    		String[] ops = server.getServer().getPlayerList().getOppedPlayers().getKeys();
//    		if (Arrays.asList(ops).contains(sender.getName())){
//    			return true;
//    		}
//    	}
//    	return false;
//	}
    
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/fwg worldID lowX lowZ highX highZ";
	}
	
	@Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length != 5)
        {
            throw new WrongUsageException("Usage: /fwg worldID lowX lowZ highX highZ");
        }
        
        //TODO
        int lowX = Integer.parseInt(args[1]);
        int lowZ = Integer.parseInt(args[2]);
        int highX = Integer.parseInt(args[3]);
        int highZ = Integer.parseInt(args[4]);
        
        new FWGGenerationJob(getWorldFromName(args[0]), lowX, lowZ, highX, highZ, sender);
    }
	
	World getWorldFromName(String s){
    	return DimensionManager.getWorld(Integer.parseInt(s));
    	}
}