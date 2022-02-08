package ru.sidey383.wallimages.map.db;

import ru.sidey383.wallimages.Callback;

import java.util.List;
import java.util.UUID;

public interface UserMapCountDatabase {

    public UserPermanentMaps getPermamentMaps(UUID uuid);

    public List<UserTemporaryMaps> getTemporaryMaps(UUID uuid);

    public GroupMaps getGroupMaps(UUID uuid);

    public void getAsynchPermamentMaps(Callback<List<UserTemporaryMaps>> callback, UUID uuid);

    public void getAsynchTemporaryMaps(Callback<List<UserTemporaryMaps>> callback, UUID uuid);

    public void getAsynchGroupMaps(Callback<List<UserTemporaryMaps>> callback, UUID uuid);

    public void setPermamentMaps(UUID uuid, int count);

    public void removeTemporaryMaps(int id);

    public void addTemporaryMaps(UserTemporaryMaps maps);

    public void setGroupMaps(String group, int count);

    public class UserTemporaryMaps {
        int id; //primary key
        UUID uuid;
        int count;
        long start;
        long end;
    }

    public class UserPermanentMaps {
        UUID uuis; //primary key
        int count;
    }

    public class GroupMaps {
        String name; //primary key
        int count;
    }

}
