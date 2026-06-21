package com.wurstblocker.plugin;

import org.bukkit.ChatColor;

public class WurstBlockerConfig {

    private final WurstBlockerPlugin plugin;

    public WurstBlockerConfig(WurstBlockerPlugin plugin) {
        this.plugin = plugin;
    }

    public String getKickMessage() {
        return ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("kick-message",
                        "&cYou are using a hacked client (Wurst) which is not allowed on this server."));
    }

    public String getAlertMessage() {
        return ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("alert-message",
                        "&e[WurstBlocker] &c{player} &ewas kicked for using Wurst client. Brand: &f{brand}"));
    }

    public boolean isAlertEnabled() {
        return plugin.getConfig().getBoolean("alert-admins", true);
    }

    public boolean isLogEnabled() {
        return plugin.getConfig().getBoolean("log-to-console", true);
    }
}
