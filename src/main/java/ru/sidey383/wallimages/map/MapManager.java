package ru.sidey383.wallimages.map;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.wallimages.Callback;
import ru.sidey383.wallimages.image.ImagePreparator;
import ru.sidey383.wallimages.map.db.MapImageInfo;
import ru.sidey383.wallimages.map.db.MapsImage;

public interface MapManager {

	public void createMap(@Nullable Callback<MapsImage> callback, @NotNull ImagePreparator preparator, @Nullable UUID owner, @NotNull String Name);


	public void loadImageByLocalMapID(@Nullable Callback<MapsImage> callback, int localMapID);

	public void loadImageByImageID(@Nullable Callback<MapsImage> callback, int imageID);

	public void loadImagesByOwner(@Nullable Callback<List<MapsImage>> callback, @Nullable UUID owner);

	public void loadImageInfoByLocalMapID(@Nullable Callback<MapImageInfo> callback, int localMapID);

	public void loadImageInfoByImageID(@Nullable Callback<MapImageInfo> callback, int imageID);

	public void loadImagesInfoByOwner(@Nullable Callback<List<MapImageInfo>> callback, @Nullable UUID owner);

	public void loadImagesInfoByOwner(@Nullable Callback<List<MapImageInfo>> callback, @Nullable UUID owner, int pageSize, int pageNumber);

	public void loadMap(int localMapID);

	/**
	 * give image item to player
	 * **/
	public void giveMapToPlayer(Player player, int imageID);


	/**
	 * give image item to player
	 * **/
	public void forceGiveMapToPlayer(Player player, int imageID);


	/**
	 * give image item to player
	 * **/
	public void giveMapToPlayer(Player player, int imageID, int count);


	/**
	 * give image item to player
	 * **/
	public void forceGiveMapToPlayer(Player player, int imageID, int count);
}
