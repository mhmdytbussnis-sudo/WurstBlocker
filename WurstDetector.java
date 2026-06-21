package com.wurstblocker.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Arrays;
import java.util.List;

public class WurstDetector implements Listener {

    private final WurstBlockerPlugin plugin;

    private static final List<String> WURST_BRANDS = Arrays.asList(
            "wurst",
            "wurst-client",
            "wurstclient"
    );

    public WurstDetector(WurstBlockerPlugin plugin) {
        this.plugin = plugin;
    }

    // PlayerLoginEvent removed — brand hasn't been sent yet at that stage.
    // Detection is handled entirely by BrandChannelListener once the brand arrives.

    public void checkAndKick(Player player, String brand) {
        if (!isWurst(brand)) return;

        if (plugin.getWurstConfig().isLogEnabled()) {
            plugin.getLogger().warning("Wurst client detected for " + player.getName() + " (brand: " + brand + ")");
        }

        String kickMsg = plugin.getWurstConfig().getKickMessage();
        player.kickPlayer(kickMsg);

        if (plugin.getWurstConfig().isAlertEnabled()) {
            String alert = plugin.getWurstConfig().getAlertMessage()
                    .replace("{player}", player.getName())
                    .replace("{brand}", brand);
            Bukkit.getOnlinePlayers().stream()
                    .filter(p -> p.hasPermission("wurstblocker.alert"))
                    .forEach(p -> p.sendMessage(alert));
            Bukkit.getConsoleSender().sendMessage(alert);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        ClientBrandCache.remove(event.getPlayer().getUniqueId());
    }

    public boolean isWurst(String brand) {
        String lower = brand.toLowerCase();
        return WURST_BRANDS.stream().anyMatch(lower::contains);
    }
}
