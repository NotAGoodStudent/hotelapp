package classes;
import classes.*;

import java.util.ArrayList;

public class Hotel
{
    private String hotelName;
    private ArrayList <Room> roomList = new ArrayList<>();
    private ArrayList <Client> clientList = new ArrayList<>();
    private ArrayList <Booking> pendentList = new ArrayList<>();
    private ArrayList <Booking> confirmedList = new ArrayList<>();

    public Hotel(String hotelName)
    {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    public ArrayList<Booking> getPendentList() {
        return pendentList;
    }

    public void setPendentList(ArrayList<Booking> pendentList) {
        this.pendentList = pendentList;
    }

    public ArrayList<Booking> getConfirmedList() {
        return confirmedList;
    }

    public void setConfirmedList(ArrayList<Booking> confirmedList) {
        this.confirmedList = confirmedList;
    }
}