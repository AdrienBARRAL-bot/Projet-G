package naufrages;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

@Mod(modid = NaufragesPlugin.MODID, version = NaufragesPlugin.VERSION)
public class NaufragesPlugin {
    public static final String MODID = "naufrages";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerWakeUp(PlayerWakeUpEvent event) {
        // Réinitialisez le niveau de faim et de soif à chaque début de journée (réveil du joueur)
        resetFaimSoif(event.getEntityPlayer());
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        // Vérifiez périodiquement le niveau de faim et de soif pour le joueur
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            checkFaimSoif(player);
        }
    }

    private void resetFaimSoif(EntityPlayer player) {
        // Remettez le niveau de faim et de soif à leur valeur maximale
        player.getFoodStats().setFoodLevel(20);
        player.getFoodStats().setFoodSaturationLevel(5.0f);
        player.sendMessage(new TextComponentString("Vous vous réveillez avec une faim et une soif renouvelées."));
    }

    private void checkFaimSoif(EntityPlayer player) {
        // Vérifiez si le joueur a faim ou soif et appliquez les effets en conséquence
        // Vous pouvez ajouter des mécanismes ici pour réduire la faim et la soif au fil du temps
        // et appliquer des effets négatifs si elles atteignent un certain seuil bas.
    }
}
