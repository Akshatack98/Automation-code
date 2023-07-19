package Allsongs;

import java.util.*;

public class RecentlyPlayedStore {
    private int capacity;
    private Map<String, Queue<String>> userSongs;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.userSongs = new HashMap<>();
    }

    public void addSong(String user, String song) {
        if (!userSongs.containsKey(user)) {
            userSongs.put(user, new LinkedList<>());
        }

        Queue<String> songsQueue = userSongs.get(user);

        // If the song was already played, remove it from the queue to update its position
        if (songsQueue.contains(song)) {
            songsQueue.remove(song);
        }

        // Add the new song to the front of the queue
        songsQueue.add(song);

        // If the number of songs for the user exceeds the capacity, remove the least recently played song
        if (songsQueue.size() > capacity) {
            songsQueue.poll();
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        if (userSongs.containsKey(user)) {
            return new ArrayList<>(userSongs.get(user));
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        int initialCapacity = 3;
        RecentlyPlayedStore store = new RecentlyPlayedStore(initialCapacity);

        store.addSong("User1", "S1");
        store.addSong("User1", "S2");
        store.addSong("User1", "S3");
        System.out.println(store.getRecentlyPlayedSongs("User1"));

        store.addSong("User1", "S4");
        System.out.println(store.getRecentlyPlayedSongs("User1")); 

        store.addSong("User1", "S2");
        System.out.println(store.getRecentlyPlayedSongs("User1")); 

        store.addSong("User1", "S1");
        System.out.println(store.getRecentlyPlayedSongs("User1")); 
    }
}
