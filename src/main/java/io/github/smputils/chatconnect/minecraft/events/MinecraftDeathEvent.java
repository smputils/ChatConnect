package io.github.smputils.chatconnect.minecraft.events;

import io.github.smputils.chatconnect.discord.DiscordBot;

public class MinecraftDeathEvent extends MinecraftEvent {

    private String deathMessage;

    public MinecraftDeathEvent(String userName, String deathMessage) {
        super(userName);
        setDeathMessage(deathMessage);
    }

    public String getDeathMessage() {
        return deathMessage;
    }

    private void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    public void display(DiscordBot bot) {
        bot.sendMessage(deathMessage);
    }

}
