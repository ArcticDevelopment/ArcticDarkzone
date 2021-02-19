package dev.arcticdevelopment.arcticdarkzone.commands;

import dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static dev.arcticdevelopment.arcticdarkzone.ArcticDarkzone.*;

public class Reload extends ASubCommand {

	public Reload(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;

		Player player = (Player) sender;

		if (!player.hasPermission("arctic.darkzone.admin")) {
			AOutput.error(player, AConfig.getString("messages.permission-denied"));
			return;
		}

		INSTANCE.getConfig().options().copyDefaults(true);
		INSTANCE.saveConfig();
		ArcticAPI.configInit(INSTANCE, "prefix", "error-prefix");

		AOutput.send(player,AConfig.getString("messages.reloaded-config"));
	}
}
