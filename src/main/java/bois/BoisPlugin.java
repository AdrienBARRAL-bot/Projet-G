package bois;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe; // Importez cette classe pour utiliser la hache
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;

@Mod(modid = BoisPlugin.MODID, version = BoisPlugin.VERSION)
public class BoisPlugin {
    public static final String MODID = "bois";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        ItemStack heldItem = event.getHarvester().getHeldItemMainhand();

        // Vérifiez si le bloc récolté est une bûche
        if (block instanceof BlockLog) {
            // Si le joueur tient une hache (objet spécial), augmentez le nombre de bois récolté
            if (heldItem.getItem() instanceof ItemAxe) {
                int additionalWood = 1;  // Changez cela si vous voulez collecter plus de bois avec la hache
                event.getDrops().add(new ItemStack(/* Mettez ici l'Item correspondant à votre type de bois */, additionalWood));
            } else {
                // Sinon, récoltez 1 bois par bûche
                event.getDrops().add(new ItemStack(/* Mettez ici l'Item correspondant à votre type de bois */));
            }
        }
    }
}
