package com.wurstblocker.plugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClientBrandCache {

    private static final Map<UUID, String> cache = new ConcurrentHashMap<>();

    public static void setBrand(UUID uuid, String brand) {
        cache.put(uuid, brand);
    }

    public static String getBrand(UUID uuid) {
        return cache.get(uuid);
    }

    public static void remove(UUID uuid) {
        cache.remove(uuid);
    }
}
