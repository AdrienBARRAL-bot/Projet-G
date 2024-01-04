package epave;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;

@Mod(modid = EpavePlugin.MODID, version = EpavePlugin.VERSION)
public class EpavePlugin {
    public static final String MODID = "epave";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() == Blocks.CHEST) {
            EntityPlayer player = event.getHarvester();
            ItemStack heldItem = player.getHeldItemMainhand();

            // Vérifiez si le joueur tient l'objet spécial
            if (heldItem.getItem() == /* Mettez ici l'Item correspondant à votre objet spécial */) {
                TileEntityChest chest = (TileEntityChest) player.world.getTileEntity(event.getPos());

                // Ajoutez l'objet au coffre après la fermeture
                if (chest != null) {
                    ItemStack newItemStack = /* Mettez ici l'Item correspondant à l'objet à stocker */;
                    chest.setInventorySlotContents(0, newItemStack);
                    player.sendMessage(new TextComponentString("L'objet a été ajouté au coffre."));
                }
            }
        }
    }
}
