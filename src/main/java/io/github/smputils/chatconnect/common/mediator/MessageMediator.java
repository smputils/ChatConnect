package io.github.smputils.chatconnect.common.mediator;

import io.github.smputils.chatconnect.common.ChatMessage;
import io.github.smputils.chatconnect.common.observer.Publisher;
import io.github.smputils.chatconnect.minecraft.events.MinecraftEvent;

// Mediator where all messages pass through and are processed
public class MessageMediator {

    // Singleton
    private static MessageMediator instance = new MessageMediator();

    private MessageMediator() {
    }

    public static MessageMediator getInstance() {
        return instance;
    }

    // Publisher of messages originating from Discord
    private Publisher<ChatMessage> discordMessages = new Publisher<>();

    // Publisher of events originating from Minecraft
    private Publisher<MinecraftEvent> minecraftEvents = new Publisher<>();

    public Publisher<ChatMessage> getDiscordMessages() {
        return discordMessages;
    }

    public Publisher<MinecraftEvent> getMinecraftEvents() {
        return minecraftEvents;
    }

}
