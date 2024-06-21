package io.github.smputils.chatconnect.discord;

import java.time.Duration;

import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.discord.listeners.MessageListener;
import io.github.smputils.chatconnect.minecraft.events.MinecraftChatEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftJoinEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftLeaveEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordBot {

    private JDA jda;

    public DiscordBot(PluginConfig config) {
        jda = JDABuilder
                .createLight(config.getDiscordToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageListener(config))
                .build();

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MessageMediator
                .getInstance()
                .getMinecraftEvents()
                .subscribe((MinecraftEvent event) -> {
                    if (event instanceof MinecraftChatEvent) {
                        MinecraftChatEvent minecraftChatEvent = (MinecraftChatEvent) event;

                        jda.getTextChannelById(config.getDiscordChannelId())
                                .sendMessage(
                                        "<" + minecraftChatEvent.getUserName() + "> " + minecraftChatEvent.getMessage())
                                .submit();
                    } else if (event instanceof MinecraftJoinEvent) {
                        MinecraftJoinEvent minecraftJoinEvent = (MinecraftJoinEvent) event;

                        jda.getTextChannelById(config.getDiscordChannelId())
                                .sendMessage(minecraftJoinEvent.getUserName() + " joined.")
                                .submit();
                    } else if (event instanceof MinecraftLeaveEvent) {
                        MinecraftLeaveEvent minecraftLeaveEvent = (MinecraftLeaveEvent) event;

                        jda.getTextChannelById(config.getDiscordChannelId())
                                .sendMessage(minecraftLeaveEvent.getUserName() + " left.")
                                .submit();
                    }
                });

    }

    public void shutdown() throws InterruptedException {
        // Initial shutdown, allowing for some RestActions to still go through
        jda.shutdown();
        // Wait up to 10 seconds for requests to finish
        if (!jda.awaitShutdown(Duration.ofSeconds(10))) {
            jda.shutdownNow(); // Cancel request queue
            jda.awaitShutdown(); // Wait until shutdown is complete (indefinitely)
        }
    }

}
