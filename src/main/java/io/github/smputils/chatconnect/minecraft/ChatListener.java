package io.github.smputils.chatconnect.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.minecraft.events.MinecraftChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftChatEvent(e.getPlayer().getDisplayName(), e.getMessage()));
    }

}
