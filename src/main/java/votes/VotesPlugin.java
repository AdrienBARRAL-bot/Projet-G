package votes;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.Collections;
import java.util.List;

@Mod(modid = VotesPlugin.MODID, version = VotesPlugin.VERSION)
public class VotesPlugin {
    public static final String MODID = "votes";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new VoteCommand());
        event.registerServerCommand(new DoubleVoteCommand());
        event.registerServerCommand(new ImmunCommand());
    }
}
