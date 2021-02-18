package dev.arcticdevelopment.arcticdarkzone.Commands;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) return false;

		Player player = (Player) sender;

		if (!player.hasPermission("arctic.darkzone.admin")) {
			AOutput.error(player, AConfig.getString("messages.permission-denied"));
			return false;
		}
		ArcticDarkzone.INSTANCE.reloadConfig();
		AOutput.send(player,AConfig.getString("messages.reloaded-config"));

		return false;
	}
}
