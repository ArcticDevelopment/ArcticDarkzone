package dev.arcticdevelopment.arcticdarkzone.Commands;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWorld implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) return false;

		Player player = (Player) sender;
		Location location = player.getLocation();
		String worldString = location.getWorld().getName();

		if (args.length == 0) {

			AConfig.set("darkzone-world", location.getWorld().getName());
		} else {

			worldString = args[0];
			if (Bukkit.getWorld(worldString) == null) {

				String message = AConfig.getString("world-does-not-exsist");
				message = message.replaceAll("%world%", worldString);

				AOutput.error(player, message);
				return false;
			}

			AConfig.set("darkzone-world", worldString);
		}

		String message = AConfig.getString("set-world");
		message = message.replaceAll("%world%", worldString);

		ArcticDarkzone.INSTANCE.saveConfig();

		AOutput.send(player, message);

		return false;
	}
}

