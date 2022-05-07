package fi.fabianadrian.greentext;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GreenText extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (event.isCancelled() || !event.getPlayer().hasPermission("greentext.use")) return;

        String plainMessage = PlainTextComponentSerializer.plainText().serialize(event.message());

        if (!isGreenTextCharacter(plainMessage.charAt(0))) return;

        String[] lines = plainMessage.split("(?=[<>])");
        List<Component> components = new ArrayList<>();
        for (String line : lines) {
            char prefix = line.charAt(0);
            switch (prefix) {
                case '>' -> {
                    event.setCancelled(true);
                    components.add(
                            Component.text(
                                    line,
                                    TextColor.color(47, 125, 28)
                            ).hoverEvent(
                                    event.getPlayer().name().asHoverEvent()
                            )
                    );
                }
                case '<' -> {
                    event.setCancelled(true);
                    components.add(
                            Component.text(
                                    line,
                                    TextColor.color(20, 80, 133)
                            ).hoverEvent(
                                    event.getPlayer().name().asHoverEvent()
                            )
                    );
                }
            }
        }

        Bukkit.broadcast(Component.join(JoinConfiguration.newlines(), components));
    }

    private boolean isGreenTextCharacter(char character) {
        Set<Character> characters = Set.of('<', '>');
        return characters.contains(character);
    }
}
