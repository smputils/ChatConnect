package io.github.smputils.chatconnect.minecraft.events;

import io.github.smputils.chatconnect.discord.DiscordBot;

public class MinecraftChatEvent extends MinecraftEvent {

    private String message;

    public MinecraftChatEvent(String userName, String message) {
        super(userName);
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public void display(DiscordBot bot) {
        bot.sendMessage("<" + getUserName() + "> " + getMessage());
    }

}
