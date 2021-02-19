package dev.arcticdevelopment.arcticdarkzone.commands;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetWorld extends ASubCommand {

	public SetWorld(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;

		Player player = (Player) sender;
		Location location = player.getLocation();
		String worldString = location.getWorld().getName();

		if (!player.hasPermission("arctic.darkzone.admin")) {
			AOutput.error(player, AConfig.getString("messages.permission-denied"));
			return;
		}

		if (args.size() == 0) {

			AConfig.set("darkzone-world", location.getWorld().getName());
		} else {

			worldString = args.get(0);
			if (Bukkit.getWorld(worldString) == null) {

				String message = AConfig.getString("messages.world-does-not-exist");
				message = message.replaceAll("%world%", worldString);

				AOutput.error(player, message);
				return;
			}

			AConfig.set("darkzone-world", worldString);
		}

		String message = AConfig.getString("messages.set-world");
		message = message.replaceAll("%world%", worldString);

		ArcticDarkzone.INSTANCE.saveConfig();

		AOutput.send(player, message);
	}
}

