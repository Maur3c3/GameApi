package de.maur3c3.gameapi;

import de.maur3c3.gameapi.commands.GameApiCommand;
import de.maur3c3.gameapi.commands.SetLobbyCommand;
import de.maur3c3.gameapi.commands.StartCommand;
import de.maur3c3.gameapi.listener.ConnectionListener;
import de.maur3c3.gameapi.listener.ItemListener;
import de.maur3c3.gameapi.listener.ProtectListener;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GameApiPlugin extends JavaPlugin {


    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        GameApi gameApi = new GameApi(this);
        getCommand("gameapi").setExecutor(new GameApiCommand());
        PluginCommand command = getCommand("setup");
        command.setExecutor(new SetLobbyCommand());
        command.setPermission("gameapi.setlobby");
        getCommand("start").setExecutor(new StartCommand());


        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new ProtectListener(), this);
        pluginManager.registerEvents(new ItemListener(), this);
    }
}
