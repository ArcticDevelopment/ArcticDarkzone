package dev.arcticdevelopment.arcticdarkzone;

import dev.arcticdevelopment.arcticdarkzone.Commands.SetExitLocation;
import dev.arcticdevelopment.arcticdarkzone.Commands.SetWorld;
import dev.arcticdevelopment.arcticdarkzone.listeners.CommandListener;
import dev.arcticdevelopment.arcticdarkzone.listeners.PortalListener;
import dev.kyro.arcticapi.ArcticAPI;
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

		getCommand("setexit").setExecutor(new SetExitLocation());
		getCommand("setworld").setExecutor(new SetWorld());
	}

	private void registerListeners() {

		getServer().getPluginManager().registerEvents(new CommandListener(), this);
		getServer().getPluginManager().registerEvents(new PortalListener(), this);
	}
}
