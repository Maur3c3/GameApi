package de.maur3c3.gameapi.commands;

import de.maur3c3.core.locations.LocationManager;
import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.config.GameConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            GameApi.getInstance().setLocation(location);
            LocationManager.getInstance().setLocation("gameapi.lobby", location);
            player.sendMessage(GameConfig.PREFIX + " Du hast den §3Lobby-Spawn §7umgesetzt.");

        }
        return false;
    }
}
