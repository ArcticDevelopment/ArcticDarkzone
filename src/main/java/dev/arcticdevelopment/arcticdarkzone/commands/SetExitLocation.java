package dev.arcticdevelopment.arcticdarkzone.commands;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetExitLocation extends ASubCommand {

	public SetExitLocation(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if (!(sender instanceof Player)) return;

		Player player = (Player) sender;
		Location location = player.getLocation();
		String tpStringLocation = ASerializer.serializeLocation(location);

		if (!player.hasPermission("arctic.darkzone.admin")) {
			AOutput.error(player, AConfig.getString("messages.permission-denied"));
			return;
		}

		if (location.getWorld().getName().equals(AConfig.get("darkzone-world"))) {

			String message = AConfig.getString("messages.set-exit-inside-darkzone:");
			message = message.replaceAll("%world%", location.getWorld().getName());
			AOutput.error(player, message);
			return;
		}

		AConfig.set("exit-teleport-location",tpStringLocation);
		ArcticDarkzone.INSTANCE.saveConfig();

		String message = AConfig.getString("messages.set-exit-location");
		message = message.replaceAll("%world%", location.getWorld().getName());
		AOutput.send(player, message);
	}
}
