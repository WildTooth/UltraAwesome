package com.github.wildtooth.awesome.core.util;

import com.github.wildtooth.awesome.core.listener.Listener;
import com.github.wildtooth.awesome.core.listener.chat.ChatListener;
import com.github.wildtooth.awesome.core.listener.server.ServerListener;
import net.labymod.api.event.EventBus;

public class Listeners {
    private final Listener[] listeners;

    public Listeners(EventBus eventBus) {
        this.listeners = new Listener[] {
            new ChatListener(eventBus),
            new ServerListener(eventBus)
        };
    }

    public ChatListener getChatListener() {
        return (ChatListener) this.listeners[0];
    }

    public ServerListener getServerListener() {
        return (ServerListener) this.listeners[1];
    }

    public void enableAll() {
        for (Listener listener : this.listeners) {
            listener.enable();
        }
    }

    public void disableAll() {
        for (Listener listener : this.listeners) {
            listener.disable();
        }
    }
}
