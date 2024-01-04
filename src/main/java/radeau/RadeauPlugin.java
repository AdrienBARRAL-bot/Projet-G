package radeau;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

@Mod(modid = RadeauPlugin.MODID, version = RadeauPlugin.VERSION)
public class RadeauPlugin {
    public static final String MODID = "radeau";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack heldItem = player.getHeldItemMainhand();

        // Vérifiez si le joueur utilise une hache en bois pour créer un radeau
        if (heldItem.getItem() == Items.WOODEN_AXE) {
            int woodCount = 0;

            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack slotStack = player.inventory.getStackInSlot(i);

                if (slotStack.getItem() == Item.getItemFromBlock(Blocks.LOG)) {
                    woodCount += slotStack.getCount();
                }
            }

            // Si le joueur a au moins 6 bois, créez un radeau et retirez 6 bois de l'inventaire
            if (woodCount >= 6) {
                ItemStack boatStack = new ItemStack(Items.BOAT);
                EntityBoat boat = new EntityBoat(player.world, player.posX, player.posY, player.posZ);
                player.world.spawnEntity(boat);
                player.inventory.clearMatchingItems(Item.getItemFromBlock(Blocks.LOG), 0, 6, null);
                player.inventory.addItemStackToInventory(boatStack);
            }
        }
    }
}
