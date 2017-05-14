package net.minplay.proxy.pluginmanager;

import net.md_5.bungee.api.event.AsyncEvent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventBus;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class ModifiedPluginEventBus extends EventBus {

    private static final Set<AsyncEvent<?>> uncompletedEvents = Collections.newSetFromMap(new WeakHashMap<AsyncEvent<?>, Boolean>());
    private static final Object lock = new Object();

    public static void completeIntents(Plugin plugin) {
        synchronized (lock) {
            for (AsyncEvent<?> event : uncompletedEvents) {
                try {
                    event.completeIntent(plugin);
                } catch (Throwable ignored) {
                }
            }
        }
    }

    @Override
    public void post(Object event) {
        if (event instanceof AsyncEvent) {
            synchronized (lock) {
                uncompletedEvents.add((AsyncEvent<?>) event);
            }
        }
        super.post(event);
    }

}
