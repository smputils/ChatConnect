package io.github.smputils.chatconnect.discord.visitors;

import java.awt.Color;
import java.util.Date;

import io.github.smputils.chatconnect.discord.DiscordBot;
import io.github.smputils.chatconnect.minecraft.events.MinecraftChatEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftDeathEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftEventVisitor;
import io.github.smputils.chatconnect.minecraft.events.MinecraftJoinEvent;
import io.github.smputils.chatconnect.minecraft.events.MinecraftLeaveEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedVisitor extends MinecraftEventVisitor {

    private DiscordBot bot;

    public EmbedVisitor(DiscordBot bot) {
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
        MessageEmbed embed = getBaseEmbedBuilder()
                .setAuthor(event.getUserName())
                .setDescription(event.getMessage())
                .setColor(new Color(3, 144, 252))
                .build();

        getBot().sendEmbed(embed);
    }

    @Override
    public void doMinecraftJoinEvent(MinecraftJoinEvent event) {
        MessageEmbed embed = getBaseEmbedBuilder()
                .setTitle(event.getUserName() + " joined the game")
                .setColor(new Color(3, 252, 78))
                .build();

        getBot().sendEmbed(embed);
    }

    @Override
    public void doMinecraftLeaveEvent(MinecraftLeaveEvent event) {
        MessageEmbed embed = getBaseEmbedBuilder()
                .setTitle(event.getUserName() + " left the game")
                .setColor(new Color(252, 148, 3))
                .build();

        getBot().sendEmbed(embed);
    }

    @Override
    public void doMinecraftDeathEvent(MinecraftDeathEvent event) {
        MessageEmbed embed = getBaseEmbedBuilder()
                .setTitle(event.getDeathMessage())
                .setColor(new Color(252, 49, 3))
                .build();

        getBot().sendEmbed(embed);
    }

    private EmbedBuilder getBaseEmbedBuilder() {
        return (new EmbedBuilder())
                .setTimestamp((new Date()).toInstant())
                .setFooter("ChatConnect");
    }

}
