package io.github.smputils.chatconnect.minecraft.events;

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

    public void accept(MinecraftEventVisitor visitor) {
        visitor.doMinecraftDeathEvent(this);
    }
}
