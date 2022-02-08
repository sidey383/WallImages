package ru.sidey383.wallimages.map.simple;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.Async;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.wallimages.Callback;
import ru.sidey383.wallimages.image.ImagePreparator;
import ru.sidey383.wallimages.map.MapManager;
import ru.sidey383.wallimages.map.db.*;
import ru.sidey383.wallimages.map.render.MapImageRender;

import java.util.*;
import java.util.logging.Level;

public class SimpleMapManager implements MapManager {

    private final LocalImageDatabase localImageDatabase;
    private final SharedDatabase sharedDatabase;
    private final UserMapCountDatabase userDatabase;
    private final Plugin plugin;
    private BukkitScheduler scheduler;


    private final Set<Integer> loadableMaps = new HashSet<>();
    private final Set<Integer> loadedMaps = new HashSet<>();

    public SimpleMapManager(Plugin plugin, LocalImageDatabase localImageDatabase, SharedDatabase sharedDatabase, UserMapCountDatabase userDatabase){
        this.plugin = plugin;
        this.localImageDatabase = localImageDatabase;
        this.sharedDatabase = sharedDatabase;
        this.userDatabase = userDatabase;
    }

    @Override
    public void createMap(@Nullable Callback<MapsImage> callback, @NotNull ImagePreparator preparator, @Nullable UUID owner, @NotNull String Name) {

    }

    @Override
    public void loadImageByLocalMapID(@Nullable Callback<MapsImage> callback, int localMapID) {

    }

    @Override
    public void loadImageByImageID(@Nullable Callback<MapsImage> callback, int imageID) {

    }

    @Override
    public void loadImagesByOwner(@Nullable Callback<List<MapsImage>> callback, @Nullable UUID owner) {

    }

    @Override
    public void loadImageInfoByLocalMapID(@Nullable Callback<MapImageInfo> callback, int localMapID) {

    }

    @Override
    public void loadImageInfoByImageID(@Nullable Callback<MapImageInfo> callback, int imageID) {

    }

    @Override
    public void loadImagesInfoByOwner(@Nullable Callback<List<MapImageInfo>> callback, @Nullable UUID owner) {

    }

    @Override
    public void loadImagesInfoByOwner(@Nullable Callback<List<MapImageInfo>> callback, @Nullable UUID owner, int pageSize, int pageNumber) {

    }

    public void loadImagesByOwner(@Nullable Callback<List<MapImageInfo>> callback, @Nullable UUID owner, int pageSize, int pageNumber) {
        runTaskAsynch(()->{
            try {
                List<SharedDatabase.ImageData> imagesData = sharedDatabase.getImagesData(owner, pageSize, pageNumber);
                // not ready
            }catch (Exception e) {

            }
        });
    }

    @Override
    public void loadMap(int localMapID) {
        runTaskAsynch(new LoadMapTask(localMapID));
    }

    @Override
    public void giveMapToPlayer(Player player, int imageID) {

    }

    @Override
    public void forceGiveMapToPlayer(Player player, int imageID) {

    }

    @Override
    public void giveMapToPlayer(Player player, int imageID, int count) {

    }

    @Override
    public void forceGiveMapToPlayer(Player player, int imageID, int count) {

    }

    private void runTaskAsynch(Runnable runnable) {
        scheduler.runTaskAsynchronously(plugin, runnable);
    }

    private class LoadMapTask implements Runnable {

        public final int localMapID;

        public LoadMapTask(int localMapID) {
            this.localMapID = localMapID;
        }

        public void run() {
            if(!startLoad())
                return;
            LocalImageDatabase.LocalMapID mapInfo = localImageDatabase.getLocalMapByServerMapID(localMapID);
            if(mapInfo == null) {
                onFailLoad();
                return;
            }
            SharedDatabase.MapData data = sharedDatabase.getMapData(mapInfo.mapID);
            if(data == null){
                onFailLoad();
                return;
            }
            MapView view = Bukkit.getMap(localMapID);
            try {
                view.addRenderer(new MapImageRender(data.data));
            } catch (NullPointerException e){
                onFailLoad(e);
                return;
            }
            onSuccessfulLoad();
        }

        private boolean startLoad() {
            synchronized (loadedMaps) {
                if(loadedMaps.contains(localMapID)) return false;
            }
            synchronized (loadableMaps) {
                if(loadableMaps.contains(localMapID)) {
                    return false;
                }else{
                    loadableMaps.add(localMapID);
                }
            }
            return true;
        }

        private void onFailLoad() {
            synchronized (loadableMaps) {
                loadableMaps.remove(localMapID);
            }
        }

        private void onFailLoad(Exception e) {
            onFailLoad();
            plugin.getLogger().log(Level.WARNING, String.format("error on load map %d", localMapID), e);
        }

        private void onSuccessfulLoad() {
            synchronized (loadedMaps) {
                loadedMaps.add(localMapID);
            }
            synchronized (loadableMaps) {
                loadableMaps.remove(localMapID);
            }
        }

    }
}
