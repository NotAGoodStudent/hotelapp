package classes;

import java.time.LocalDate;

public class Booking
{
    private LocalDate start;
    private int personNumb;
    private int nights;
    private LocalDate end;
    private Client client;
    private Room room;


    public Booking(LocalDate start, int personNumb, int nights, Client client) {
        this.start = start;
        this.personNumb = personNumb;
        this.client = client;

    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getPersonNumb() {
        return personNumb;
    }

    public void setPersonNumb(int personNumb) {
        this.personNumb = personNumb;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate ld) {
        this.end = ld;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
