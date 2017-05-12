package com.github.agsphoenix.forgeworldgen;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.server.command.ForgeCommand;

@Mod(modid = ForgeWorldGen.MODID, version = ForgeWorldGen.VERSION, name = ForgeWorldGen.NAME)
public class ForgeWorldGen {
	@Mod.Instance(ForgeWorldGen.MODID)
    public static ForgeWorldGen instance;
	
    public static final String MODID = "forgeworldgen";
    public static final String VERSION = "0.0.1";
    public static final String NAME = "ForgeWorldGen";

    static org.apache.logging.log4j.Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ForgeChunkManager.setForcedChunkLoadingCallback(instance, new FWGLoadingCallback());
    }

    @EventHandler
    public void init(FMLServerStartingEvent event)
    {
        // register command here i think
    	event.registerServerCommand(new ForgeWorldGenCommand());
    	
    	
    }
    
    
}
