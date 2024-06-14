package io.github.smputils.chatconnect;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatConnect extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}
