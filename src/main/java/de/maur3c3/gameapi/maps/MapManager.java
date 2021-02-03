package de.maur3c3.gameapi.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class MapManager {
    ArrayList<Map> maps = new ArrayList<>();
    Map currentMap = null;

    public void addMap(Map map) {
        maps.add(map);
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }
}
