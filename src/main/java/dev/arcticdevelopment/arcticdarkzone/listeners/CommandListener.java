package dev.arcticdevelopment.arcticdarkzone.listeners;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
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

		String command = event.getMessage();
		Player player = event.getPlayer();

		if (!player.getLocation().getWorld().getName().equals(AConfig.getString("darkzone-world"))) {
			return;
		}

		if (player.hasPermission("arctic.darkzone.bypass")) {

			String message = ArcticDarkzone.INSTANCE.getConfig().getString("messages.bypassed-darkzone");
			message = message.replaceAll("%command%", command);
			AOutput.send(player, message);
			return;
		}

		AOutput.error(player, ArcticDarkzone.INSTANCE.getConfig().getString("messages.no-permission"));
		event.setCancelled(true);
	}
}
