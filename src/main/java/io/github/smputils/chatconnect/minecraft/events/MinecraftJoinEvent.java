package io.github.smputils.chatconnect.minecraft.events;

public class MinecraftJoinEvent extends MinecraftEvent {

    public MinecraftJoinEvent(String userName) {
        super(userName);
    }

    public void accept(MinecraftEventVisitor visitor) {
        visitor.doMinecraftJoinEvent(this);
    }

}
