package io.github.smputils.chatconnect.minecraft.events;

import io.github.smputils.chatconnect.discord.DiscordBot;

public abstract class MinecraftEvent {

    private String userName;

    public MinecraftEvent(String userName) {
        setUserName(userName);
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public abstract void display(DiscordBot bot);

}
