package de.maur3c3.gameapi.maps;

import de.maur3c3.core.inventorymanager.ItemStackCreator;
import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.config.GameConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MapVoting {
    public static final String inventoryName = "§3Map-Voting";
    private HashMap<Map, ArrayList<UUID>> mapVotes = new HashMap<>();
    private Inventory inventory;
    private boolean isRunning = true;

    public MapVoting(List<Map> maps) {
        inventory = Bukkit.createInventory(null, 9 * 1, inventoryName);
        Collections.shuffle(maps);
        maps.forEach(map -> mapVotes.put(map, new ArrayList<>()));
        createInventory();
        updateInventory();
    }

    private void createInventory() {
        for (Map map : mapVotes.keySet()) {
            ItemStack itemStack = new ItemStackCreator(Material.PAPER).setDisplayName("§e" + map.getMapName()).create();
            inventory.addItem(itemStack);
        }
    }
    public void openVoteInventory(Player player) {
        player.openInventory(inventory);
    }

    public void addVoteForPlayer(Player player, String mapName) {
        removePlayersCurrentVote(player);
        mapVotes.entrySet()
                .stream()
                .filter(mapArrayListEntry -> mapArrayListEntry
                        .getKey()
                        .getMapName().equals(mapName))
                .findAny()
                .ifPresent(mapArrayListEntry -> mapArrayListEntry.getValue().add(player.getUniqueId()));
        updateInventory();
        player.sendMessage(GameConfig.PREFIX + " Du hast für die Map §e" + mapName + " §7abgestimmt.");
    }

    private void removePlayersCurrentVote(Player player) {
        Bukkit.broadcastMessage("player remove");
        UUID uuid = player.getUniqueId();
        mapVotes.values().forEach(uuids -> uuids.remove(uuid));
    }

    private void updateInventory() {
        //Bukkit.broadcastMessage("update");
        for(ItemStack itemStack : inventory.getContents()) {
            if(itemStack != null) {

                String mapName = itemStack.getItemMeta().getDisplayName().replaceFirst("§e", "");
                ItemMeta itemMeta = itemStack.getItemMeta();
                Optional<java.util.Map.Entry<Map, ArrayList<UUID>>> optionalMap = mapVotes.entrySet().stream().filter(mapArrayListEntry -> mapArrayListEntry
                        .getKey()
                        .getMapName()
                        .equals(mapName))
                        .findAny();
                Bukkit.broadcastMessage("ist present:" + optionalMap.get().getValue().size());
                optionalMap.ifPresent(mapArrayListEntry -> itemMeta.setLore(Arrays.asList("§7- §3" + mapArrayListEntry.getValue().size())));
                itemStack.setItemMeta(itemMeta);
            }
        }

    }
    public void endMapVoting() {
        isRunning = false;
        HashMap<Map, Integer> finalVotes = new HashMap<>();
        mapVotes.forEach((key, value) -> finalVotes.put(key, value.size()));
        Map finalMap = null;
        int votes = -1;
        for (java.util.Map.Entry<Map, Integer> entry : finalVotes.entrySet()) {
            Map map = entry.getKey();
            int vote = entry.getValue();
            if (vote > votes) {
                finalMap = map;
                votes = vote;
            }
        }
        if(finalMap != null) {
            GameApi.getInstance().getMapManager().setCurrentMap(finalMap);
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1f, 1f));
            Bukkit.broadcastMessage(GameConfig.PREFIX + " Das §3Map-Voting §7wurde beendet es wird auf der Map §e" + finalMap.getMapName() + " §7gespielt");
        }



    }

}
