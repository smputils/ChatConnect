package io.github.smputils.chatconnect.discord;

import java.time.Duration;

import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.config.PluginConfig;
import io.github.smputils.chatconnect.discord.listeners.MessageListener;
import io.github.smputils.chatconnect.discord.visitors.EmbedVisitor;
import io.github.smputils.chatconnect.discord.visitors.PlainTextVisitor;
import io.github.smputils.chatconnect.minecraft.events.MinecraftEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftEventVisitor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public class DiscordBot {

    private JDA jda;
    private PluginConfig config;
    private MinecraftEventVisitor visitor;

    public DiscordBot(PluginConfig config) {
        jda = JDABuilder
                .createLight(config.getDiscordToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageListener(config))
                .build();

        this.config = config;

        setVisitor(createVisitor(config.getDiscordMessageType()));

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MessageMediator
                .getInstance()
                .getMinecraftEvents()
                .subscribe((MinecraftEvent event) -> {
                    event.accept(getVisitor());
                });

    }

    private MinecraftEventVisitor getVisitor() {
        return visitor;
    }

    private void setVisitor(MinecraftEventVisitor visitor) {
        this.visitor = visitor;
    }

    public void sendMessage(CharSequence message) {
        jda.getTextChannelById(config.getDiscordChannelId())
                .sendMessage(message)
                .submit();
    }

    public void sendEmbed(MessageEmbed embed) {
        jda.getTextChannelById(config.getDiscordChannelId())
                .sendMessage("")
                .addEmbeds(embed)
                .submit();
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

    private MinecraftEventVisitor createVisitor(String messageType) {
        return switch (messageType) {
            case "classic" -> new PlainTextVisitor(this);
            case "embed" -> new EmbedVisitor(this);
            default -> new PlainTextVisitor(this);
        };
    }

}
