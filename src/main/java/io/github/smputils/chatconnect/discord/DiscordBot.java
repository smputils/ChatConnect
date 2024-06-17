package io.github.smputils.chatconnect.discord;

import java.time.Duration;

import io.github.smputils.chatconnect.common.ChatMessage;
import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.discord.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordBot {

    private JDA jda;

    public DiscordBot(PluginConfig config) {
        jda = JDABuilder
                .createLight(config.getDiscordToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageListener(config))
                .build();

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MessageMediator
                .getInstance()
                .getMinecraftMessages()
                .subscribe((ChatMessage message) -> {
                    jda.getTextChannelById(config.getDiscordChannelId())
                            .sendMessage("<" + message.userName() + "> " + message.message())
                            .submit();
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
