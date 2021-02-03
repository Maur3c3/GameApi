package de.maur3c3.gameapi.gamestates;

import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.countdown.CountDown;
import de.maur3c3.gameapi.countdown.IdleCountDown;
import de.maur3c3.gameapi.countdown.LobbyCountDown;
import de.maur3c3.gameapi.maps.Map;
import de.maur3c3.gameapi.maps.MapVoting;

import java.util.ArrayList;

public class LobbyState extends GameState{
    private GameStateManager gameStateManager;
    private LobbyCountDown lobbyCountDown;
    private IdleCountDown idleCountDown;
    private MapVoting mapVoting;

    @Override
    public void start() {
        idleCountDown = new IdleCountDown();
        lobbyCountDown = new LobbyCountDown();
        idleCountDown.setRunning(true);
        lobbyCountDown.setRunning(false);
        ArrayList<Map> maps = new ArrayList<>();
        maps.add(new Map("Test1", "niemand") {
            @Override
            public boolean isPlayable() {
                return true;
            }
        });
        maps.add(new Map("Test2", "niemand") {
            @Override
            public boolean isPlayable() {
                return false;
            }
        });
        maps.add(new Map("Test3", "niemand") {
            @Override
            public boolean isPlayable() {
                return false;
            }
        });
        maps.add(new Map("Test4", "niemand") {
            @Override
            public boolean isPlayable() {
                return false;
            }
        });
        maps.add(new Map("Test5", "niemand") {
            @Override
            public boolean isPlayable() {
                return false;
            }
        });
        mapVoting = new MapVoting(maps);
    }

    @Override
    public void stop() {
        lobbyCountDown.stop();
        lobbyCountDown.endCountDown();
        idleCountDown.endCountDown();
        idleCountDown.stop();

    }

    public IdleCountDown getIdleCountDown() {
        return idleCountDown;
    }

    public LobbyCountDown getLobbyCountDown() {
        return lobbyCountDown;
    }

    public MapVoting getMapVoting() {
        return mapVoting;
    }
}
