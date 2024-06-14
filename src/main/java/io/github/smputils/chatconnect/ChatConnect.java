package io.github.smputils.chatconnect;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.smputils.chatconnect.minecraft.ChatListener;

public class ChatConnect extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled!");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}
