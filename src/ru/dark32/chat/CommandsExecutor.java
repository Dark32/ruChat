package ru.dark32.chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        String cmdName = cmd.getName();

        if (args.length == 0) {
            if (cmdName.equalsIgnoreCase("g")) {
                Utils.changeMode((Player) sender, ChatMode.GLOBAL);
                return true;
            }
            if (cmdName.equalsIgnoreCase("p")) {
                Utils.changeMode((Player) sender, ChatMode.PRIVATE);
                return true;
            }
            if (cmdName.equalsIgnoreCase("s")) {
                Utils.changeMode((Player) sender, ChatMode.SALE);
                return true;
            }
            if (cmdName.equalsIgnoreCase("h")) {
                Utils.changeMode((Player) sender, ChatMode.HELP);
                return true;
            }
            if (cmdName.equalsIgnoreCase("chat")) {
                sender.sendMessage(ChatColor.YELLOW + "Создатели: Mayor, smilesdc. DevInLive.");
                sender.sendMessage(ChatColor.YELLOW + "Адаптировал под 1.4.7: dark32.");
                sender.sendMessage(ChatColor.YELLOW + "Соавторы: dark32, ufatos");
                return true;
            }
        } else {
            if (cmdName.equalsIgnoreCase("g")) {
                Utils.changeMode((Player) sender, ChatMode.GLOBAL);
                ((Player) sender).chat(Utils.sMsg(args));
                return true;
            }
            if (cmdName.equalsIgnoreCase("p")) {
                Utils.changeMode((Player) sender, ChatMode.PRIVATE);
                ((Player) sender).chat(Utils.sMsg(args));
                return true;
            }
            if (cmdName.equalsIgnoreCase("s")) {
                Utils.changeMode((Player) sender, ChatMode.SALE);
                ((Player) sender).chat(Utils.sMsg(args));
                return true;
            }
            if (cmdName.equalsIgnoreCase("h")) {
                Utils.changeMode((Player) sender, ChatMode.HELP);
                ((Player) sender).chat(Utils.sMsg(args));
                return true;
            }
        }
        return false;
    }
}
