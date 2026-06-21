package com.wurstblocker.plugin;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BrandChannelListener implements PluginMessageListener {

    private final WurstBlockerPlugin plugin;

    public BrandChannelListener(WurstBlockerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("minecraft:brand")) return;

        String brand;
        try {
            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            brand = in.readUTF().trim().toLowerCase();
        } catch (Exception e) {
            // Malformed brand packet — ignore silently
            return;
        }

        ClientBrandCache.setBrand(player.getUniqueId(), brand);

        if (plugin.getWurstConfig().isLogEnabled()) {
            plugin.getLogger().info("Client brand for " + player.getName() + ": " + brand);
        }

        plugin.getServer().getScheduler().runTask(plugin, () -> {
            WurstDetector detector = new WurstDetector(plugin);
            detector.checkAndKick(player, brand);
        });
    }
}
