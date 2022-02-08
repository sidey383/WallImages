package ru.sidey383.imageweb;

import java.util.Base64;
import java.util.HashMap;
import java.util.IllegalFormatException;

public class MapWebData {

    private int x;
    private int y;
    private String name;
    private HashMap<String, String> maps = new HashMap<>();
    private byte[][][] byteMaps;

    public void calculateMaps() throws IllegalFormatException {
        try{
            byteMaps = new byte[x][y][128*128];
            for(int i = 0; i < getWidth(); i++)
                for(int j = 0; j < getHeigh(); j++)
                    byteMaps[x][y] = Base64.getDecoder().decode(maps.get(i+"."+j));
        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public byte[][][] getMaps() {
        if(byteMaps == null) {
            try {
                calculateMaps();
            } catch (IllegalArgumentException e) {}
        }
        return byteMaps;
    }

    public int getWidth() {
        return x;
    }

    public int getHeigh() {
        return y;
    }

    public String getName() {
        return name;
    }

}
