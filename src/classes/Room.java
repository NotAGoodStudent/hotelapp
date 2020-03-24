package classes;

import java.util.ArrayList;
public class Room {

    private int roomnumb;
    private int capacity;

    public Room(int roomnumb, int capacity) {
        this.roomnumb = roomnumb;
        this.capacity = capacity;
    }

    public int getRoomnumb() {
        return roomnumb;
    }

    public void setRoomnumb(int roomnumb) {
        this.roomnumb = roomnumb;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
