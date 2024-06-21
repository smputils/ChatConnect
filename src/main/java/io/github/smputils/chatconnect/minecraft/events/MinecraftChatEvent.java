package io.github.smputils.chatconnect.minecraft.events;

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

}
