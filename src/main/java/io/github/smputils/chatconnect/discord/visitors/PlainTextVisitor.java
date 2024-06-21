package io.github.smputils.chatconnect.discord.visitors;

import io.github.smputils.chatconnect.discord.DiscordBot;
import io.github.smputils.chatconnect.minecraft.events.MinecraftChatEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftDeathEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftEventVisitor;
import io.github.smputils.chatconnect.minecraft.events.MinecraftJoinEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftLeaveEvent;

public class PlainTextVisitor extends MinecraftEventVisitor {

    private DiscordBot bot;

    public PlainTextVisitor(DiscordBot bot) {
        setBot(bot);
    }

    private DiscordBot getBot() {
        return bot;
    }

    private void setBot(DiscordBot bot) {
        this.bot = bot;
    }

    @Override
    public void doMinecraftChatEvent(MinecraftChatEvent event) {
        getBot().sendMessage("<" + event.getUserName() + "> " + event.getMessage());
    }

    @Override
    public void doMinecraftJoinEvent(MinecraftJoinEvent event) {
        getBot().sendMessage(event.getUserName() + " joined the game");
    }

    @Override
    public void doMinecraftLeaveEvent(MinecraftLeaveEvent event) {
        getBot().sendMessage(event.getUserName() + " left the game");
    }

    @Override
    public void doMinecraftDeathEvent(MinecraftDeathEvent event) {
        getBot().sendMessage(event.getDeathMessage());
    }

}
