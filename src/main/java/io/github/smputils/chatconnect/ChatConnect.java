package io.github.smputils.chatconnect;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.smputils.chatconnect.common.ChatMessage;
import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.discord.DiscordBot;
import io.github.smputils.chatconnect.minecraft.MinecraftListener;

public class ChatConnect extends JavaPlugin {

    private PluginConfig config;
    private DiscordBot bot;

    @Override
    public void onEnable() {
        config = new PluginConfig(this);
        bot = new DiscordBot(config);

        MessageMediator
                .getInstance()
                .getDiscordMessages()
                .subscribe((ChatMessage message) -> {
                    Bukkit.broadcastMessage("<" + message.userName() + "> " + message.message());
                });

        getServer().getPluginManager().registerEvents(new MinecraftListener(config), this);
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
