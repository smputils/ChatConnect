package io.github.smputils.chatconnect.minecraft.events;

public class MinecraftLeaveEvent extends MinecraftEvent {

    public MinecraftLeaveEvent(String userName) {
        super(userName);
    }

    public void accept(MinecraftEventVisitor visitor) {
        visitor.doMinecraftLeaveEvent(this);
    }

}
