package classes;

import java.util.ArrayList;
public class Room {

    private int roomnumb;
    private int capacity;
    private boolean isOccupied;

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

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public static Room roomExists(Hotel currentHotel, int roomNumber)
    {
        for(Room r : currentHotel.getRoomList())
        {
            System.out.println("L"+roomNumber);
            System.out.println(r.getRoomnumb());
            if(r.getRoomnumb()== roomNumber)
            {

                return r;
            }
        }
        return null;
    }

}
