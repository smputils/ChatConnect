package io.github.smputils.chatconnect;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.minecraft.ChatListener;

public class ChatConnect extends JavaPlugin {

    PluginConfig config;

    @Override
    public void onEnable() {
        config = new PluginConfig(this);

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getLogger().info("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}
