package peche;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = PechePlugin.MODID, version = PechePlugin.VERSION)
public class PechePlugin {
    public static final String MODID = "peche";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Initialisation du plugin
    }
}
