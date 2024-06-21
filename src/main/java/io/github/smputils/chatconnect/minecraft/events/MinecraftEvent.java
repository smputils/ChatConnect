package io.github.smputils.chatconnect.minecraft.events;

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

    public abstract void accept(MinecraftEventVisitor visitor);

}
