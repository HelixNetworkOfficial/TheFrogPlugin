package me.frog.crimefrog;

import me.frog.crimefrog.commands.ElmoSongCommand;
import me.frog.crimefrog.commands.GetDevice;
import me.frog.crimefrog.events.FoodWorkshop;
import me.frog.crimefrog.events.T1Device;
import me.frog.crimefrog.items.ItemManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrimeFrog extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemManager.init();
        getCommand("HackT1").setExecutor(new GetDevice());
        getCommand("elmosong").setExecutor(new ElmoSongCommand());
        getServer().getPluginManager().registerEvents(new T1Device(this), this);
        getServer().getPluginManager().registerEvents(new FoodWorkshop(this), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
}