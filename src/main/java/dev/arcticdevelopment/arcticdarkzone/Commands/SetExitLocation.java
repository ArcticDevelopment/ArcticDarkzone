package dev.arcticdevelopment.arcticdarkzone.Commands;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetExitLocation implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) return false;

		Player player = (Player) sender;
		Location location = player.getLocation();
		String tpStringLocation = ASerializer.serializeLocation(location);

		if (location.getWorld().getName().equals(AConfig.get("darkzone-world"))) {

			String message = ArcticDarkzone.INSTANCE.getConfig().getString("messages.set-exit-inside-darkzone:");
			message = message.replaceAll("%world%", location.getWorld().getName());
			AOutput.error(player, message);
			return false;
		}

		AConfig.set("exit-teleport-location",tpStringLocation);
		ArcticDarkzone.INSTANCE.saveConfig();

		String message = ArcticDarkzone.INSTANCE.getConfig().getString("messages.set-exit-location");
		message = message.replaceAll("%world%", location.getWorld().getName());
		AOutput.send(player, message);

		return false;
	}
}
