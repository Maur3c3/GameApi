package de.maur3c3.gameapi.countdown;

import de.maur3c3.gameapi.GameApiPlugin;
import de.maur3c3.gameapi.config.GameConfig;
import org.bukkit.Bukkit;

public abstract class CountDown {
    private int taskID;

    public CountDown(long periode, long delay) {
        start();
        taskID = Bukkit.getScheduler().runTaskTimer(GameApiPlugin.getPlugin(GameApiPlugin.class), new Runnable() {

            @Override
            public void run() {
                on();
            }
        }, delay, periode).getTaskId();
    }

    public void endCountDown() {
        Bukkit.getScheduler().cancelTask(taskID);
        stop();
    }

    public abstract void start();
    public abstract void stop();
    public abstract void on();


    public int getTaskID() {
        return taskID;
    }
}
