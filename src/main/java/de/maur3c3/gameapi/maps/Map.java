package de.maur3c3.gameapi.maps;

public abstract class Map {
    private String mapName;
    private String builder;

    public Map(String mapName, String builder) {
        this.mapName = mapName;
        this.builder = builder;
    }

    public abstract boolean isPlayable();

    public String getBuilder() {
        return builder;
    }

    public String getMapName() {
        return mapName;
    }
}
