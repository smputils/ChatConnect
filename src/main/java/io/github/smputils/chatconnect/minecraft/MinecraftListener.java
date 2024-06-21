package io.github.smputils.chatconnect.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.minecraft.events.MinecraftChatEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftJoinEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftLeaveEvent;

public class MinecraftListener implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftChatEvent(e.getPlayer().getDisplayName(), e.getMessage()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftJoinEvent(e.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftLeaveEvent(e.getPlayer().getDisplayName()));
    }

}
