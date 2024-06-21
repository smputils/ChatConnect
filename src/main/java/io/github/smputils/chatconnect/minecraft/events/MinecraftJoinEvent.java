package io.github.smputils.chatconnect.minecraft.events;

import io.github.smputils.chatconnect.discord.DiscordBot;

public class MinecraftJoinEvent extends MinecraftEvent {

    public MinecraftJoinEvent(String userName) {
        super(userName);
    }

    public void display(DiscordBot bot) {
        bot.sendMessage(getUserName() + " joined.");
    }

}
