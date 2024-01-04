package eau;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import java.util.Random;

@Mod(modid = EauPlugin.MODID, version = EauPlugin.VERSION)
public class EauPlugin {
    public static final String MODID = "eau";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() == Blocks.WATER || event.getState().getBlock() == Blocks.FLOWING_WATER) {
            EntityPlayer player = event.getHarvester();
            ItemStack heldItem = player.getHeldItemMainhand();

            // Si le joueur tient l'objet spécial, augmentez le nombre d'eau récolté
            if (heldItem.getItem() == Items.WATER_BUCKET) {
                int additionalWater = 1;  // Changez cela si vous voulez collecter plus d'eau avec l'objet spécial
                event.getDrops().add(new ItemStack(Items.WATER_BUCKET, additionalWater));
            } else {
                // Sinon, récoltez un nombre d'eau aléatoire en fonction de la météo
                int waterCollected = getRandomWaterAmount(player.world.rand);
                event.getDrops().add(new ItemStack(Items.WATER_BUCKET, waterCollected));
            }
        }
    }

    private int getRandomWaterAmount(Random random) {
        // Retourne un nombre aléatoire entre 0 et 3
        return random.nextInt(4);
    }
}
