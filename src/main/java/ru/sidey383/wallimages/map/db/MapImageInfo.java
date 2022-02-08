package ru.sidey383.wallimages.map.db;

import java.util.UUID;

public class MapImageInfo {

    private UUID owner;

    private int imageID;

    private String name;

    private int width;

    private int height;

    public MapImageInfo(UUID owner, int imageID, String name, int width, int height) {
        this.owner = owner;
        this.imageID = imageID;
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }
}
