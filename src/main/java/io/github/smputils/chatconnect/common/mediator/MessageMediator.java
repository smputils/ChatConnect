package io.github.smputils.chatconnect.common.mediator;

import io.github.smputils.chatconnect.common.ChatMessage;
import io.github.smputils.chatconnect.common.observer.Publisher;

// Mediator where all messages pass through and are processed
public class MessageMediator {
    
    // Singleton
    private static MessageMediator instance = new MessageMediator();

    private MessageMediator() {}

    public static MessageMediator getInstance() {
        return instance;
    }

    // Publisher of messages originating from Discord
    private Publisher<ChatMessage> discordMessages = new Publisher<>();
    
    // Publisher of messages originating from Minecraft
    private Publisher<ChatMessage> minecraftMessages = new Publisher<>();

    public Publisher<ChatMessage> getDiscordMessages() {
        return discordMessages;
    }

    public Publisher<ChatMessage> getMinecraftMessages() {
        return minecraftMessages;
    }

}
