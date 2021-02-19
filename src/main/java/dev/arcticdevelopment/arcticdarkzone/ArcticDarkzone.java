package dev.arcticdevelopment.arcticdarkzone;

import dev.arcticdevelopment.arcticdarkzone.commands.BaseCommand;
import dev.arcticdevelopment.arcticdarkzone.commands.Reload;
import dev.arcticdevelopment.arcticdarkzone.commands.SetExitLocation;
import dev.arcticdevelopment.arcticdarkzone.commands.SetWorld;
import dev.arcticdevelopment.arcticdarkzone.listeners.CommandListener;
import dev.arcticdevelopment.arcticdarkzone.listeners.PortalListener;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcticDarkzone extends JavaPlugin {

	public static ArcticDarkzone INSTANCE;

	@Override
	public void onEnable() {

		INSTANCE = this;

		loadConfig();

		ArcticAPI.configInit(this, "prefix", "error-prefix");

		registerCommands();
		registerListeners();
	}

	@Override
	public void onDisable() {

	}

	private void loadConfig() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void registerCommands() {

		ABaseCommand apiCommand = new BaseCommand("darkzone");

		apiCommand.registerCommand(new SetExitLocation("setexit"));
		apiCommand.registerCommand(new SetExitLocation("setworld"));
		//TODO fix reload command
		//apiCommand.registerCommand(new SetExitLocation("reload"));
	}

	private void registerListeners() {

		getServer().getPluginManager().registerEvents(new CommandListener(), this);
		getServer().getPluginManager().registerEvents(new PortalListener(), this);
	}
}
