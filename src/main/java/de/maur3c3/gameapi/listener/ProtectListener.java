package de.maur3c3.gameapi.listener;

import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.gamestates.EndingState;
import de.maur3c3.gameapi.gamestates.GameState;
import de.maur3c3.gameapi.gamestates.LobbyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import javax.swing.text.html.parser.Entity;

public class ProtectListener implements Listener {
    GameApi gameApi;

    public ProtectListener() {
        gameApi = GameApi.getInstance();
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        GameState gameState = gameApi.getGameStateManager().getCurrentGamestate();
        if(gameState instanceof LobbyState || gameState instanceof EndingState) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        GameState gameState = gameApi.getGameStateManager().getCurrentGamestate();
        if(gameState instanceof LobbyState || gameState instanceof EndingState) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        GameState gameState = gameApi.getGameStateManager().getCurrentGamestate();
        if(gameState instanceof LobbyState || gameState instanceof EndingState) {
            event.setCancelled(true);
        }
    }

    //@EventHandler

}
