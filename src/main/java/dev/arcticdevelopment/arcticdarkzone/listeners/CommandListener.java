package dev.arcticdevelopment.arcticdarkzone.listeners;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public static void onCommand(PlayerCommandPreprocessEvent event) {

		Player player = event.getPlayer();

		if (!player.getLocation().getWorld().getName().equals(AConfig.getString("darkzone-world"))) {
			return;
		}

		if (player.hasPermission("arctic.darkzone.bypass")) {

			String message = AConfig.getString("bypassed-darkzone");
			AOutput.send(player, message);
			return;
		}

		AOutput.error(player, AConfig.getString("messages.no-permission"));
		event.setCancelled(true);
	}
}
