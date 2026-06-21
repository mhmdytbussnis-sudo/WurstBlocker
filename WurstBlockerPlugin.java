package com.wurstblocker.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class WurstBlockerPlugin extends JavaPlugin {

    private WurstBlockerConfig config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.config = new WurstBlockerConfig(this);

        getServer().getPluginManager().registerEvents(new WurstDetector(this), this);
        getServer().getMessenger().registerIncomingPluginChannel(this, "minecraft:brand", new BrandChannelListener(this));

        getLogger().info("WurstBlocker enabled — protecting your server from Wurst clients.");
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterIncomingPluginChannels(this);
        getLogger().info("WurstBlocker disabled.");
    }

    public WurstBlockerConfig getWurstConfig() {
        return config;
    }
}
