package de.maur3c3.gameapi.commands;

import de.maur3c3.gameapi.GameApi;
import de.maur3c3.gameapi.config.GameConfig;
import de.maur3c3.gameapi.gamestates.LobbyState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(GameApi.getInstance().getGameStateManager().getCurrentGamestate() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) GameApi.getInstance().getGameStateManager().getCurrentGamestate();
            lobbyState.getLobbyCountDown().setSeconds(11);
            sender.sendMessage(GameConfig.PREFIX + " Du hast den §3Runden-Start§7 beschleunigt.");
        }
        return false;
    }
}
