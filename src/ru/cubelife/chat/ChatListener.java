package ru.cubelife.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

@SuppressWarnings("deprecation")
public class ChatListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        ruChat.modes.put(p, ChatMode.PRIVATE);
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Player p = event.getPlayer();

        ChatMode cm = ruChat.modes.get(p);
        boolean g = event.getMessage().startsWith("!") && event.getMessage().length() > 1;
        boolean s = event.getMessage().startsWith("#") && event.getMessage().length() > 1;
        if (s) {
            event.setMessage(event.getMessage().substring(1));
            Utils.saleMessage(p, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [S]: %2$s");
            return;
        }
        if (g) {
            event.setMessage(event.getMessage().substring(1));
            Utils.globalMessage(p, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [G]: %2$s");
            return;
        }
        if (cm == ChatMode.GLOBAL) {
            Utils.globalMessage(p, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [G]: %2$s");
            return;
        }
        if (cm == ChatMode.PRIVATE) {
            Utils.privateMessage(p, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [P]: %2$s");
            return;
        }
        if (cm == ChatMode.SALE) {
            Utils.saleMessage(p, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [S]: %2$s");
            return;
        }
        if (cm == ChatMode.HELP) {
            Utils.helpMessage(p, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [H]: %2$s");
            return;
        }

    }
}
