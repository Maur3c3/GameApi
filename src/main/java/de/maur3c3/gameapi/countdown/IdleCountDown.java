package de.maur3c3.gameapi.countdown;

import de.maur3c3.gameapi.config.GameConfig;
import de.maur3c3.gameapi.gamestates.GameStateManager;
import de.maur3c3.gameapi.gamestates.LobbyState;
import org.bukkit.Bukkit;

public class IdleCountDown extends CountDown{
    private int seconds = 45;
    private boolean isRunning;
    public IdleCountDown() {
        super(20, 20);


    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void on() {
        if(!isRunning) return;
        seconds--;
        if(seconds <= 0) {
            seconds = 45;
            int onlinePlayers = Bukkit.getOnlinePlayers().size();
            Bukkit.broadcastMessage(GameConfig.PREFIX + " Es werden noch §3" + (GameConfig.MINPLAYERS - onlinePlayers) + " §7benötigt damit die Runde startet.");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
