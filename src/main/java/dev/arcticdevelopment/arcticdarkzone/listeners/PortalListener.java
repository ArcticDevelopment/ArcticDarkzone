package dev.arcticdevelopment.arcticdarkzone.listeners;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalListener implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public static void enterPortal(PlayerPortalEvent event) {

		Player player = event.getPlayer();
		Location location = player.getLocation();

		if (!(location.getWorld().getName().equals(AConfig.getString("darkzone-world")))) {

			return;
		}

		player.teleport(ASerializer.deserializeLocation(AConfig.getString("exit-teleport-location")));
		//TODO: Spawn fireworks
	}
}
