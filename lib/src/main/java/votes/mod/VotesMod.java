package com.votes.mod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Mod(modid = VotesMod.MODID, version = VotesMod.VERSION)
public class VotesMod {
    public static final String MODID = "votesmod";
    public static final String VERSION = "1.0";

    private static int voteTimerSeconds;
    private static Map<String, Integer> playerVotes = new HashMap<>();
    private static Map<String, Boolean> playerImmunities = new HashMap<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "votesmod.cfg"));
        config.load();
        Property timerProp = config.get("general", "voteTimerSeconds", 60);
        voteTimerSeconds = timerProp.getInt();
        config.save();

        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerCommand(new CommandSetTimer());
        GameRegistry.registerCommand(new CommandVote());
        GameRegistry.registerCommand(new CommandImmunity());
    }

    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        String message = event.getMessage();
        if (message.startsWith("/vote")) {
            String[] args = message.split(" ");
            if (args.length == 2) {
                String targetPlayer = args[1];
                votePlayer(targetPlayer, event.getUsername());
            } else {
                event.setCanceled(true);
                event.getPlayer().sendMessage(new TextComponentString("Usage: /vote [pseudo]"));
            }
        }
    }

    private void votePlayer(String targetPlayer, String voter) {
        if (!playerVotes.containsKey(targetPlayer)) {
            playerVotes.put(targetPlayer, 1);
        } else {
            int currentVotes = playerVotes.get(targetPlayer);
            playerVotes.put(targetPlayer, currentVotes + 1);
        }
        MinecraftServer.getServer().getPlayerList().sendMessage(new TextComponentString(voter + " a voté pour " + targetPlayer));
    }

    public static class CommandSetTimer extends CommandBase {
        @Override
        public String getName() {
            return "set_timer";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/set_timer vote <seconds>";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws WrongUsageException {
            if (args.length == 2 && args[0].equalsIgnoreCase("vote")) {
                try {
                    voteTimerSeconds = Integer.parseInt(args[1]);
                    sender.sendMessage(new TextComponentString("Le temps du vote a été défini à " + voteTimerSeconds + " secondes."));
                } catch (NumberFormatException e) {
                    sender.sendMessage(new TextComponentString("Veuillez entrer un nombre valide."));
                }
            } else {
                throw new WrongUsageException(getUsage(sender));
            }
        }
    }

    public static class CommandVote extends CommandBase {
        @Override
        public String getName() {
            return "vote";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/vote [pseudo]";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws WrongUsageException {
            if (args.length == 1) {
                String targetPlayer = args[0];
                votePlayer(targetPlayer, sender.getName());
            } else {
                throw new WrongUsageException(getUsage(sender));
            }
        }
    }

    public static class CommandImmunity extends CommandBase {
        @Override
        public String getName() {
            return "immunity";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/immunity [pseudo]";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws WrongUsageException {
            if (args.length == 1) {
                String player = args[0];
                playerImmunities.put(player, true);
                sender.sendMessage(new TextComponentString(player + " est désormais immunisé contre les votes."));
            } else {
                throw new WrongUsageException(getUsage(sender));
            }
        }
    }
}
