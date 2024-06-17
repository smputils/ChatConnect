package io.github.smputils.chatconnect;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.discord.DiscordBot;
import io.github.smputils.chatconnect.minecraft.ChatListener;

public class ChatConnect extends JavaPlugin {

    private PluginConfig config;
    private DiscordBot bot;

    @Override
    public void onEnable() {
        config = new PluginConfig(this);
        bot = new DiscordBot(config);
        
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getLogger().info("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        try {
            bot.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getLogger().info("Plugin Disabled!");
    }
}
