package de.maur3c3.gameapi.listener;

import de.maur3c3.core.locations.LocationManager;
import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.config.GameConfig;
import de.maur3c3.gameapi.gamestates.GameStateManager;
import de.maur3c3.gameapi.gamestates.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConnectionListener implements Listener {
    private GameStateManager gameStateManager;

    public ConnectionListener() {
        gameStateManager = GameApi.getInstance().getGameStateManager();
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String message = GameConfig.PREFIX + " Der Spieler §3" + player.getDisplayName() + " §7hat das Spiel betreten.";
        event.setJoinMessage(message);
        if(gameStateManager.getCurrentGamestate() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGamestate();
            int amountPlayers = Bukkit.getOnlinePlayers().size();
            if(amountPlayers >= GameConfig.MINPLAYERS) {
                if(!lobbyState.getLobbyCountDown().isRunning())
                    lobbyState.getLobbyCountDown().setRunning(true);
                if(lobbyState.getIdleCountDown().isRunning()) {
                    lobbyState.getIdleCountDown().setRunning(false);
                }
            }
        }
        LocationManager locationManager = LocationManager.getInstance();
        Location location = locationManager.getLocation("gameapi.lobby");
        Inventory inventory = player.getInventory();
        inventory.addItem(new ItemStack(Material.PAPER));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String message = GameConfig.PREFIX + " Der Spieler §3" + player.getDisplayName() + " §7hat das Spiel verlassen.";
        event.setQuitMessage(message);
        if(gameStateManager.getCurrentGamestate() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGamestate();
            int amountPlayers = Bukkit.getOnlinePlayers().size();
            amountPlayers--;
            if(amountPlayers < GameConfig.MINPLAYERS) {
                if(lobbyState.getLobbyCountDown().isRunning())
                    lobbyState.getLobbyCountDown().setRunning(false);
                if(!lobbyState.getIdleCountDown().isRunning()) {
                    lobbyState.getIdleCountDown().setRunning(true);
                }
            }
        }
    }
}
