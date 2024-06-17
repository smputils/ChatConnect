package io.github.smputils.chatconnect.common.observer;

@FunctionalInterface
public interface Subscriber<T> {
    
    public void update(T t);

}
