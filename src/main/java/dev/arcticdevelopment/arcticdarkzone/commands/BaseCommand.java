package dev.arcticdevelopment.arcticdarkzone.commands;

import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BaseCommand extends ABaseCommand {

	public BaseCommand(String baseCommand) {

		super(baseCommand);
	}

	@Override
	public void executeBase(CommandSender sender, List<String> args) {
		System.out.println("test");

	}

	@Override
	public void executeFail(CommandSender sender, List<String> args) {

		executeBase(sender,args);
	}
}
