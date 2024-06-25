package io.github.smputils.chatconnect.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginConfig {

    private JavaPlugin plugin;
    private FileConfiguration config;

    public PluginConfig(JavaPlugin plugin) {
        this.plugin = plugin;

        config = plugin.getConfig();

        config.addDefault("minecraft.events.message", true);
        config.addDefault("minecraft.events.join", true);
        config.addDefault("minecraft.events.leave", true);
        config.addDefault("minecraft.events.death", false);

        config.addDefault("bot.token", "");
        config.addDefault("bot.channel_id", "");
        config.addDefault("bot.message_type", "classic");

        config.options().copyDefaults(true);

        saveConfig();
    }

    private void saveConfig() {
        plugin.saveConfig();
    }

    public boolean getMinecraftEventMessage() {
        return config.getBoolean("minecraft.events.message");
    }

    public boolean getMinecraftEventJoin() {
        return config.getBoolean("minecraft.events.join");
    }

    public boolean getMinecraftEventLeave() {
        return config.getBoolean("minecraft.events.leave");
    }

    public boolean getMinecraftEventDeath() {
        return config.getBoolean("minecraft.events.death");
    }

    public String getDiscordToken() {
        return config.getString("bot.token");
    }

    public String getDiscordChannelId() {
        return config.getString("bot.channel_id");
    }

    public String getDiscordMessageType() {
        return config.getString("bot.message_type");
    }

}