package de.maur3c3.gameapi.gamestates;

import de.maur3c3.gameapi.config.GameConfig;
import org.bukkit.Bukkit;

public class IngameState extends GameState{
    @Override
    public void start() {
        Bukkit.broadcastMessage(GameConfig.PREFIX + " Die §3Runde §7startet nun");
    }

    @Override
    public void stop() {

    }
}
