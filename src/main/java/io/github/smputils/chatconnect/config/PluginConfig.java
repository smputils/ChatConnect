package io.github.smputils.chatconnect.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginConfig {

    private JavaPlugin plugin;
    private FileConfiguration config;

    public PluginConfig(JavaPlugin plugin) {
        this.plugin = plugin;

        config = plugin.getConfig();

        config.addDefault("bot.token", "");
        config.addDefault("bot.channel_id", "");
        config.addDefault("minecraft.message_format", "<%s> %s");

        config.options().copyDefaults(true);

        saveConfig();
    }

    private void saveConfig() {
        plugin.saveConfig();
    }

    public String getDiscordToken() {
        return config.getString("bot.token");
    }

    public String getDiscordChannelId() {
        return config.getString("bot.channel_id");
    }

    public String getMinecraftMessageFormat() {
        return config.getString("minecraft.message_format");
    }

}