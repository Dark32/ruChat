package ru.dark32.chat;

import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utils {

    public static void privateMessage(Player player, String msg) {
        Location pLoc = player.getLocation();

        String _msg = ruChat.cfg.getString("private-chat-format");
        _msg = replaceSpecials(_msg, msg, player);
        double range = Math.pow(ruChat.cfg.getInt("private-chat-range"), 2);
        for (Player recipient : Bukkit.getOnlinePlayers()) {
            if (recipient == player) {
                continue;
            }
            if (recipient.getWorld() != player.getWorld()) {
                continue;
            }
            if (pLoc.distanceSquared(recipient.getLocation()) < range) {
                recipient.sendMessage(_msg);
            }
        }
        player.sendMessage(_msg);
    }

    public static void globalMessage(Player player, String msg) {
        String _msg = ruChat.cfg.getString("global-chat-format");
        _msg = replaceSpecials(_msg, msg, player);
        for (Player recipient : Bukkit.getOnlinePlayers()) {
            recipient.sendMessage(_msg);
        }
    }

    public static void saleMessage(Player player, String msg) {
        String _msg = ruChat.cfg.getString("sale-chat-format");
        _msg = replaceSpecials(_msg, msg, player);
        for (Player recipient : Bukkit.getOnlinePlayers()) {
            recipient.sendMessage(_msg);
        }
    }

    public static void helpMessage(Player player, String msg) {
        String _msg = ruChat.cfg.getString("help-chat-format");
        _msg = replaceSpecials(_msg, msg, player);
        for (Player recipient : Bukkit.getOnlinePlayers()) {
            if (ruChat.modes.get(recipient) == ChatMode.HELP || recipient.hasPermission("ruchat.help")) {
                recipient.sendMessage(_msg);
            }
        }
    }

    public static void changeMode(Player player, ChatMode cm) {
        if (ruChat.modes.containsKey(player)) {
            ruChat.modes.remove(player);
        }
        ruChat.modes.put(player, cm);
        player.sendMessage(ChatColor.AQUA + "Режим чата успешно изменен!");
    }

    public static String replaceSpecials(String s, String msg, Player player) {

        String worldName = player.getWorld().getName();
        if (ruChat.usePex) {
            PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
            s = s.replace("$player", player.getDisplayName()).replace("$world", worldName).replace("$msg", msg).replace("$prefix", user.getPrefix()).replace("$suffix", user.getSuffix());
        } else {
            s = s.replace("$player", player.getDisplayName()).replace("$world", worldName).replace("$msg", msg).replace("$prefix", "").replace("$suffix", "");
        }
        s = translateColor(s);
        return s;
    }

    public static String sMsg(String[] m) {
        String msg = "";
        for (String w : m) {
            msg += w;
        }
        return msg;
    }
    protected static Pattern chatColorPattern = Pattern.compile("(?i)&([0-9A-F])");// Цвет
    protected static Pattern chatMagicPattern = Pattern.compile("(?i)&([K])");// магия
    protected static Pattern chatBoldPattern = Pattern.compile("(?i)&([L])");//жирный
    protected static Pattern chatStrikethroughPattern = Pattern.compile("(?i)&([M])");//зачёркнутый
    protected static Pattern chatUnderlinePattern = Pattern.compile("(?i)&([N])");//подчёркнутый
    protected static Pattern chatItalicPattern = Pattern.compile("(?i)&([O])");//косой
    protected static Pattern chatResetPattern = Pattern.compile("(?i)&([R])");//сброс

    public static String translateColor(String string) {
        // ChatManager Permissions Prefix своровал код, чтобы не парится
        // protected static String translateColorCodes(String string) {
        if (string == null) {
            return "";
        }
        String newstring = string;
        newstring = chatColorPattern.matcher(newstring).replaceAll("\u00A7$1");
        newstring = chatMagicPattern.matcher(newstring).replaceAll("\u00A7$1");
        newstring = chatBoldPattern.matcher(newstring).replaceAll("\u00A7$1");
        newstring = chatStrikethroughPattern.matcher(newstring).replaceAll("\u00A7$1");
        newstring = chatUnderlinePattern.matcher(newstring).replaceAll("\u00A7$1");
        newstring = chatItalicPattern.matcher(newstring).replaceAll("\u00A7$1");
        newstring = chatResetPattern.matcher(newstring).replaceAll("\u00A7$1");
        return newstring;
        //
        // return s;
    }
}
