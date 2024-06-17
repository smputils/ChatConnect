package io.github.smputils.chatconnect.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.smputils.chatconnect.common.ChatMessage;
import io.github.smputils.chatconnect.common.mediator.MessageMediator;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        MessageMediator.getInstance()
                .getMinecraftMessages()
                .publish(new ChatMessage(e.getPlayer().getDisplayName(), e.getMessage()));
    }

}
