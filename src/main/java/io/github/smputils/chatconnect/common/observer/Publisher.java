package io.github.smputils.chatconnect.common.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher<T> {
    
    private List<Subscriber<T>> subscribers = new ArrayList<>();

    public void publish(T t) {
        for (var subscriber : subscribers) {
            subscriber.update(t);
        }
    }

    public void subscribe(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }

}
