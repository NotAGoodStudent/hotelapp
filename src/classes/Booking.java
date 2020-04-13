package classes;

import javax.swing.table.DefaultTableModel;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;

public class Booking
{
    private LocalDate start;
    private int personNumb;
    private boolean isConfirmed;
    private LocalDate end;
    private Client client;
    private Room room;


    public Booking(LocalDate start, int personNumb,Client client) {
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }


    @Override
    public String toString()
    {
        return this.getStart() + " - " +"Guests: "+ this.getPersonNumb();
    }

    public static boolean isRoomBooked(int numb, LocalDate in, Hotel hotel)
    {
        for(Booking b : hotel.getBookings()){


            if(b.getRoom().getRoomnumb() == numb && in.isBefore(b.getEnd())) return true;
        }

        return false;
    }

    public static Booking returnConfirmationBooking(String id, Hotel hotel, LocalDate ld)
    {
        for(Booking b : hotel.getBookings())
        {
            if(b.getClient().getDNI().equalsIgnoreCase(id) && b.getStart().isEqual(ld))
            {
                return b;
            }
        }

        return null;
    }

    public static void listBookings(LocalDate ld, Hotel hotel, boolean toggled, DefaultTableModel deftable2)
    {
        for(Booking b: hotel.getBookings())
        {
            if(toggled)
            {
                if(b.getEnd().isEqual(ld) && b.isConfirmed)
                {
                    deftable2.addRow(new Object[]{b.getClient().getDNI().toUpperCase(), b.getClient().getName().toUpperCase(), b.getClient().getSurname().toUpperCase(), b.getRoom().getRoomnumb()});
                }
            }

            else
                {
                    if(b.getStart().isEqual(ld) && b.isConfirmed)
                    {
                        deftable2.addRow(new Object[]{b.getClient().getDNI().toUpperCase(), b.getClient().getName().toUpperCase(), b.getClient().getSurname().toUpperCase(), b.getRoom().getRoomnumb()});
                    }
                }
        }
    }

    public static void deleteBooking(Booking booking, Hotel hotel)
    {
        if(hotel.getBookings().contains(booking)) {

            hotel.getBookings().remove(booking);
        }

    }



}
