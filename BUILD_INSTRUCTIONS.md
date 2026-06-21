# WurstBlocker — Build & Install Instructions

## Requirements
- Java JDK 8 or higher
- Maven 3.x (https://maven.apache.org/download.cgi)

## Build the Plugin

```bash
cd WurstBlocker
mvn clean package
```

The compiled `.jar` file will be at:
```
WurstBlocker/target/WurstBlocker-1.0.0.jar
```

## Install on Your Server

1. Copy `WurstBlocker-1.0.0.jar` to your server's `plugins/` folder.
2. Start or restart the server.
3. A `plugins/WurstBlocker/config.yml` file will be created automatically.

## Configuration (`plugins/WurstBlocker/config.yml`)

| Option | Description |
|---|---|
| `kick-message` | Message shown to the Wurst player when kicked |
| `alert-message` | Message sent to admins (use `{player}` and `{brand}`) |
| `alert-admins` | `true` = notify admins with `wurstblocker.alert` permission |
| `log-to-console` | `true` = log detections in console |

## Admin Permission

Give this permission to admins to receive detection alerts:
```
wurstblocker.alert
```

## How It Works

Wurst (and most hacked clients) send their client name in the `minecraft:brand`
plugin message channel when connecting. This plugin listens to that channel and
immediately kicks any player whose brand contains "wurst".

## Compatibility

- Spigot 1.13+
- Paper 1.13+
- Any fork based on either (Purpur, Pufferfish, etc.)
