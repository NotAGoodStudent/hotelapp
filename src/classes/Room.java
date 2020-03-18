package classes;

import java.util.ArrayList;
public class Room
{
    private int roomnumb;
    private int capacity;
    private boolean hasDoubleBed;


    public Room(int roomnumb, int capacity, boolean hasDoubleBed) {
        this.roomnumb = roomnumb;
        this.capacity = capacity;
        this.hasDoubleBed = hasDoubleBed;
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

    public boolean isHasDoubleBed() {
        return hasDoubleBed;
    }

    public void setHasDoubleBed(boolean hasDoubleBed) {
        this.hasDoubleBed = hasDoubleBed;
    }
}
