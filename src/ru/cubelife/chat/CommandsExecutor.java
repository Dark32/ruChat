package ru.cubelife.chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			return false;
		
		String cmdName = cmd.getName();
		
		if(args.length == 0) {
			if(cmdName.equalsIgnoreCase("global") || cmdName.equalsIgnoreCase("g")) {
				Utils.changeMode((Player) sender, ChatMode.GLOBAL);
				return true;
			} else if(cmdName.equalsIgnoreCase("private") || cmdName.equalsIgnoreCase("p")) {
				Utils.changeMode((Player) sender, ChatMode.PRIVATE);
				return true;
			} else if(cmdName.equalsIgnoreCase("sale") || cmdName.equalsIgnoreCase("s")) {
				Utils.changeMode((Player) sender, ChatMode.SALE);
				return true;
			} else if(cmdName.equalsIgnoreCase("help") || cmdName.equalsIgnoreCase("h")) {
				Utils.changeMode((Player) sender, ChatMode.HELP);
				return true;
			} else if(cmdName.equalsIgnoreCase("chat")) {
				sender.sendMessage(ChatColor.RED + "Создатели: Mayor, smilesdc. DevInLive.");
				sender.sendMessage(ChatColor.RED + "Адаптировал под 1.4.7: dark32.");
				
                                return true;
			}
		} else {
			if(cmdName.equalsIgnoreCase("global") || cmdName.equalsIgnoreCase("g")) {
				Utils.changeMode((Player) sender, ChatMode.GLOBAL);
				((Player)sender).chat(Utils.sMsg(args));
				return true;
			} else if(cmdName.equalsIgnoreCase("private") || cmdName.equalsIgnoreCase("p")) {
				Utils.changeMode((Player) sender, ChatMode.PRIVATE);
				((Player)sender).chat(Utils.sMsg(args));
				return true;
			} else if(cmdName.equalsIgnoreCase("sale") || cmdName.equalsIgnoreCase("s")) {
				Utils.changeMode((Player) sender, ChatMode.SALE);
				((Player)sender).chat(Utils.sMsg(args));
				return true;
			} else if(cmdName.equalsIgnoreCase("help") || cmdName.equalsIgnoreCase("h")) {
				Utils.changeMode((Player) sender, ChatMode.HELP);
				((Player)sender).chat(Utils.sMsg(args));
				return true;
			}
		}
		return false;
	}

}
