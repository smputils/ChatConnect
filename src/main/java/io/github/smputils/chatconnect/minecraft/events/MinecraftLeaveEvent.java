package io.github.smputils.chatconnect.minecraft.events;

import io.github.smputils.chatconnect.discord.DiscordBot;

public class MinecraftLeaveEvent extends MinecraftEvent {

    public MinecraftLeaveEvent(String userName) {
        super(userName);
    }

    public void display(DiscordBot bot) {
        bot.sendMessage(getUserName() + " left.");
    }

}
