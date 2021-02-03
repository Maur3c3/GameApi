package de.maur3c3.gameapi.countdown;

import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.config.GameConfig;
import de.maur3c3.gameapi.gamestates.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyCountDown extends CountDown {
    private int seconds;
    private boolean isRunning;
    public LobbyCountDown() {
        super(20, 20);
    }

    @Override
    public void start() {
        seconds = GameConfig.TIME;
    }

    @Override
    public void stop() {

    }

    @Override
    public void on() {
        if(!isRunning) return;
        seconds--;
        Bukkit.getOnlinePlayers().forEach(player -> player.setLevel(seconds));
        switch (seconds) {
            case 60: case 30:case  15:case  5:case  4:case  3:case  2:case  1:
                sendMessage();
                break;
            case 10:
                LobbyState lobbyState = (LobbyState) GameApi.getInstance().getGameStateManager().getCurrentGamestate();
                lobbyState.getMapVoting().endMapVoting();
                break;
            case 0:
                GameApi.getInstance().getGameStateManager().setGamestate(1);
        }

    }

    private void sendMessage() {
        Bukkit.broadcastMessage(GameConfig.PREFIX + " Die Runde beginnt in §3" + seconds + "§7 Sekunden.");

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
        if(!running) seconds = GameConfig.TIME;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
