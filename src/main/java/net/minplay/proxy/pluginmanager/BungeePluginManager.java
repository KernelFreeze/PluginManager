package net.minplay.proxy.pluginmanager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePluginManager extends Plugin {
    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Commands());
    }
}
