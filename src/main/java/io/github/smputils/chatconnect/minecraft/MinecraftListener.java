package io.github.smputils.chatconnect.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.minecraft.events.MinecraftChatEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftDeathEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftJoinEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftLeaveEvent;

public class MinecraftListener implements Listener {

    private PluginConfig config;

    public MinecraftListener(PluginConfig config) {
        setConfig(config);
    }

    public PluginConfig getConfig() {
        return config;
    }

    private void setConfig(PluginConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if (!getConfig().getMinecraftEventMessage()) {
            return;
        }

        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftChatEvent(e.getPlayer().getDisplayName(), e.getMessage()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!getConfig().getMinecraftEventJoin()) {
            return;
        }

        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftJoinEvent(e.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (!getConfig().getMinecraftEventLeave()) {
            return;
        }

        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftLeaveEvent(e.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (!getConfig().getMinecraftEventDeath()) {
            return;
        }

        MessageMediator.getInstance()
                .getMinecraftEvents()
                .publish(new MinecraftDeathEvent(e.getEntity().getDisplayName(), e.getDeathMessage()));
    }

}
