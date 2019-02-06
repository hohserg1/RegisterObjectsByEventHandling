package registerobjectsbyeventhandling;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "test")
public class Registration{

    public static List<Item> itemsToRegister = new LinkedList<>();
    public static List<Class<? extends TileEntity>> tilesToRegister = new LinkedList<>();
    public static List<Block> blocksToRegister = new LinkedList<>();
    public static List<EntityEntry> entityToRegister = new LinkedList<>();
	
    @SubscribeEvent
    public void onRegistryNewRegistry(RegistryEvent.NewRegistry event) {
        new RegistryBuilder<Rune>()
                .setName(new ResourceLocation(EM.ID, "rune"))
                .setType(Rune.class)
                .add((IForgeRegistry.AddCallback<RuneEffect>) (owner, stage, id, obj, oldObj) -> {
                    /*register rune logic*/
                })
                .create();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        blocksToRegister.forEach(e.getRegistry()::register);
        tilesToRegister.forEach(tile -> GameRegistry.registerTileEntity(tile, new ResourceLocation("test", tile.getSimpleName())));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        itemsToRegister.forEach(e.getRegistry()::register);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> e) {
        entityToRegister.forEach(e.getRegistry()::register);
    }
}