package dev.arcticdevelopment.arcticdarkzone.commands;

import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BaseCommand extends ABaseCommand {

	public BaseCommand(String baseCommand) {

		super(baseCommand);
	}

	@Override
	public void executeBase(CommandSender sender, List<String> args) {
		System.out.println("test");

		if (!(sender instanceof Player)) return;
		createHelp().send((Player) sender);

	}

	@Override
	public void executeFail(CommandSender sender, List<String> args) {

		executeBase(sender,args);
	}
}
