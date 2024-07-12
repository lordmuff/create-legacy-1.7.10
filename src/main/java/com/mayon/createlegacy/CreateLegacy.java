package com.mayon.createlegacy;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

import com.mayon.createlegacy.proxy.CommonProxy;
import com.mayon.createlegacy.tabs.CreateModDecoTab;
import com.mayon.createlegacy.tabs.CreateModOtherTab;
import com.mayon.createlegacy.tabs.CreateModTab;
import com.mayon.createlegacy.util.Reference;
import com.mayon.createlegacy.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public final class CreateLegacy {

    public static Logger logger;

    public static final CreativeTabs TAB_CREATE = new CreateModTab("tab_create");
    public static final CreativeTabs TAB_CREATE_DECORATIONS = new CreateModDecoTab("tab_create_decorations");
    public static final CreativeTabs TAB_CREATE_OTHER = new CreateModOtherTab();

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        RegistryHandler.otherPreInitRegistries();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        RegistryHandler.otherInitRegistries();
    }

    @EventHandler
    public void init(FMLPostInitializationEvent event) {
        RegistryHandler.otherPostInitRegistries();
    }
}
