package io.github.smputils.chatconnect.minecraft.events;

public abstract class MinecraftEventVisitor {

    public abstract void doMinecraftChatEvent(MinecraftChatEvent event);

    public abstract void doMinecraftJoinEvent(MinecraftJoinEvent event);

    public abstract void doMinecraftLeaveEvent(MinecraftLeaveEvent event);

    public abstract void doMinecraftDeathEvent(MinecraftDeathEvent event);

}
