package ru.dark32.chat;

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

        Player player = event.getPlayer();

        ChatMode chatMode = ruChat.modes.get(player);
        boolean isGlobal = event.getMessage().startsWith("!") && event.getMessage().length() > 1;
        boolean isSale = event.getMessage().startsWith("#") && event.getMessage().length() > 1;
        if (isSale) {
            event.setMessage(event.getMessage().substring(1));
            Utils.saleMessage(player, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [S]: %2$s");
            return;
        }
        if (isGlobal) {
            event.setMessage(event.getMessage().substring(1));
            Utils.globalMessage(player, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [G]: %2$s");
            return;
        }
        if (chatMode == ChatMode.GLOBAL) {
            Utils.globalMessage(player, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [G]: %2$s");
            return;
        }
        if (chatMode == ChatMode.PRIVATE) {
            Utils.privateMessage(player, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [P]: %2$s");
            return;
        }
        if (chatMode == ChatMode.SALE) {
            Utils.saleMessage(player, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [S]: %2$s");
            return;
        }
        if (chatMode == ChatMode.HELP) {
            Utils.helpMessage(player, event.getMessage());
            event.getRecipients().clear();
            event.setFormat("%1$s [H]: %2$s");
            return;
        }

    }
}
