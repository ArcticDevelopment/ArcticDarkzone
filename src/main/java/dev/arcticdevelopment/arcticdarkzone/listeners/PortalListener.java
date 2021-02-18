package dev.arcticdevelopment.arcticdarkzone.listeners;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class PortalListener implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public static void enterPortal(PlayerPortalEvent event) {

		Player player = event.getPlayer();
		Location location = player.getLocation();

		if (!(location.getWorld().getName().equals(AConfig.getString("messages.darkzone-world")))) {

			return;
		}

		player.teleport(ASerializer.deserializeLocation(AConfig.getString("exit-teleport-location")));

		if (AConfig.getBoolean("spawn-fireworks-on-darkzone-exit")) {

			location = player.getLocation();
			Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
			FireworkMeta fireworkMeta = firework.getFireworkMeta();

			fireworkMeta.setPower(2);
			fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.AQUA).flicker(true).withTrail().build());

			firework.setFireworkMeta(fireworkMeta);
			firework.detonate();

		}
	}
}
