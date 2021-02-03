package de.maur3c3.gameapi.gamestates;

import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.GameApiPlugin;

public class GameStateManager {
    private GameApiPlugin plugin;
    private GameState[] gamestates;
    private GameState currentGamestate;

    public GameStateManager(GameApiPlugin plugin) {
        this.plugin = plugin;
        gamestates = new GameState[3];
        gamestates[GameState.LOBBY_STATE] = new LobbyState();
        gamestates[GameState.INGAME_STATE] = new IngameState();
        gamestates[GameState.ENDING_STATE] = new EndingState();
    }

    public void setGamestate(int gamestateID) {
        if(currentGamestate != null) {
            currentGamestate.stop();
        }
        currentGamestate = gamestates[gamestateID];
        currentGamestate.start();
    }

    public void stopCurrentGamestate() {
        if(currentGamestate !=null) {
            currentGamestate.stop();
            currentGamestate = null;
        }
    }

    public GameState getCurrentGamestate() {
        return currentGamestate;
    }
}
