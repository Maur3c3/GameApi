package de.maur3c3.gameapi;

import de.maur3c3.gameapi.gamestates.GameStateManager;
import de.maur3c3.gameapi.maps.MapManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class GameApi {
    private static GameApi instance;
    private Location location;
    private GameStateManager gameStateManager;
    private MapManager mapManager;

    public GameApi(GameApiPlugin plugin) {
        instance = this;
        gameStateManager = new GameStateManager(plugin);
        gameStateManager.setGamestate(0);
        mapManager = new MapManager();
    }

    public static GameApi getInstance() {
        return instance;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public MapManager getMapManager() {
        return mapManager;
    }
}
