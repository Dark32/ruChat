package ru.cubelife.chat;

import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utils {

    public static void privateMessage(Player p, String msg) {
        Location pLoc = p.getLocation();

        String m = ruChat.cfg.getString("private-chat-format");
        m = replaceSpecials(m, msg, p);

        double range = Math.pow(ruChat.cfg.getInt("private-chat-range"), 2);

        for (Player r : Bukkit.getOnlinePlayers()) {
            if (r == p) {
                continue;
            }
            if (r.getWorld() != p.getWorld()) {
                continue;
            }
            if (pLoc.distanceSquared(r.getLocation()) < range) {
                r.sendMessage(m);
            }
        }

        p.sendMessage(m);
    }

    public static void globalMessage(Player p, String msg) {
        String m = ruChat.cfg.getString("global-chat-format");
        m = replaceSpecials(m, msg, p);
        for (Player r : Bukkit.getOnlinePlayers()) {
            r.sendMessage(m);
        }
    }

    public static void saleMessage(Player p, String msg) {
        String m = ruChat.cfg.getString("sale-chat-format");
        m = replaceSpecials(m, msg, p);
        for (Player r : Bukkit.getOnlinePlayers()) {
            r.sendMessage(m);
        }
    }

    public static void helpMessage(Player p, String msg) {
        String m = ruChat.cfg.getString("help-chat-format");
        m = replaceSpecials(m, msg, p);
        for (Player r : Bukkit.getOnlinePlayers()) {
            if (ruChat.modes.get(r) == ChatMode.HELP || r.hasPermission("ruchat.help")) {
                r.sendMessage(m);
            }
        }
    }

    public static void changeMode(Player p, ChatMode cm) {
        if (ruChat.modes.containsKey(p)) {
            ruChat.modes.remove(p);
        }
        ruChat.modes.put(p, cm);
        p.sendMessage(ChatColor.AQUA + "Режим чата успешно изменен!");
    }

    public static String replaceSpecials(String s, String msg, Player p) {
      
        String worldName = p.getWorld().getName();
        if (ruChat.usePex) {
            PermissionUser user = PermissionsEx.getPermissionManager().getUser(p);
            s =    s.replace("$player", p.getDisplayName())
                    .replace("$world", worldName)
                    .replace("$msg", msg)
                    .replace("$prefix", user.getPrefix())
                    .replace("$suffix", user.getSuffix());
        } else {
            s = s.replace("$player", p.getDisplayName())
                    .replace("$world", worldName)
                    .replace("$msg", msg)
                    .replace("$prefix", "")
                    .replace("$suffix", "");
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
