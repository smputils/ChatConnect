package io.github.smputils.chatconnect.discord.listeners;

import io.github.smputils.chatconnect.common.ChatMessage;
import io.github.smputils.chatconnect.common.mediator.MessageMediator;
import io.github.smputils.chatconnect.config.PluginConfig;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    PluginConfig config;

    public MessageListener(PluginConfig config) {
        this.config = config;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        Member member = event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete();

        if (!member.getUser().isBot() && message.getChannelId().equals(config.getDiscordChannelId())) {
            MessageMediator.getInstance().getDiscordMessages()
                    .publish(new ChatMessage(member.getEffectiveName(), message.getContentRaw()));
        }
    }

}
