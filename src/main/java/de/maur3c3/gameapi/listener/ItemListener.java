package de.maur3c3.gameapi.listener;

import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.gamestates.GameState;
import de.maur3c3.gameapi.gamestates.LobbyState;
import de.maur3c3.gameapi.maps.MapVoting;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if(itemStack.getType() == Material.PAPER) {
            GameState gameState = GameApi.getInstance().getGameStateManager().getCurrentGamestate();
            if(gameState instanceof LobbyState) {
                LobbyState lobbyState = (LobbyState) gameState;
                lobbyState.getMapVoting().openVoteInventory(player);
            }
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!event.getClickedInventory().getName().equals(MapVoting.inventoryName)) return;
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if(GameApi.getInstance().getGameStateManager().getCurrentGamestate() instanceof LobbyState) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            ItemStack itemStack = event.getCurrentItem();
            String mapName = itemStack.getItemMeta().getDisplayName().replaceFirst("§e", "");
            if(GameApi.getInstance().getGameStateManager().getCurrentGamestate() instanceof LobbyState) {
                LobbyState lobbyState = (LobbyState) GameApi.getInstance().getGameStateManager().getCurrentGamestate();
                lobbyState.getMapVoting().addVoteForPlayer(player, mapName);
            }

        }
    }
}
