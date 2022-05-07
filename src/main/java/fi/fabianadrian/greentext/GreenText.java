package fi.fabianadrian.greentext;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class GreenText extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (event.isCancelled() || !event.getPlayer().hasPermission("greentext.use")) return;

        String plainMessage = PlainTextComponentSerializer.plainText().serialize(event.message());
        char prefix = plainMessage.charAt(0);
        switch (prefix) {
            case '>' -> {
                event.setCancelled(true);
                Bukkit.broadcast(
                        Component.text(
                                plainMessage,
                                TextColor.color(47, 125, 28)
                        ).hoverEvent(
                                event.getPlayer().name().asHoverEvent()
                        )
                );
            }
            case '<' -> {
                event.setCancelled(true);
                Bukkit.broadcast(
                        Component.text(
                                plainMessage,
                                TextColor.color(20, 80, 133)
                        ).hoverEvent(
                                event.getPlayer().name().asHoverEvent()
                        )
                );
            }
        }
    }
}
