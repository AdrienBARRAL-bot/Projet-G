package peche;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemFishedEvent;

import java.util.Random;

@Mod(modid = PechePlugin.MODID, version = PechePlugin.VERSION)
public class PechePlugin {
    public static final String MODID = "peche";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        EntityPlayer player = event.getAngler();
        World world = player.world;
        Random random = new Random();

        // Générez un nombre aléatoire entre 1 et 3
        int nombrePoissons = random.nextInt(3) + 1;

        // Ajoutez le nombre généré de poissons à l'inventaire du joueur
        for (int i = 0; i < nombrePoissons; i++) {
            ItemStack poisson = new ItemStack(/* Mettez ici l'item correspondant à votre poisson */);
            EntityItem entityItem = new EntityItem(world, player.posX, player.posY, player.posZ, poisson);
            world.spawnEntity(entityItem);
        }
    }
}
