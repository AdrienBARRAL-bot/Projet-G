package votes;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class VoteCommand extends CommandBase {
    @Override
    public String getName() {
        return "vote";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/vote [pseudo]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            sender.sendMessage(new TextComponentString("Usage: /vote [pseudo]"));
            return;
        }

        String playerName = args[0];
        // TODO: Mettez en œuvre la logique de vote ici
        sender.sendMessage(new TextComponentString("Vous avez voté pour " + playerName));
    }
}
