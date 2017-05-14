package net.minplay.proxy.pluginmanager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePluginManager extends Plugin {
    private static BungeePluginManager instance;

    public static BungeePluginManager getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Commands());
    }
}
