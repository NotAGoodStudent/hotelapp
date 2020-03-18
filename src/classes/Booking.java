package classes;

import java.time.LocalDate;

public class Booking
{
    private int BookingID;
    private int personNumb;
    private int nights;
    private LocalDate ld;
    private Client client;


    public Booking(int bookingID, int personNumb, int nights, Client client, LocalDate ld) {
        BookingID = bookingID;
        this.personNumb = personNumb;
        this.client = client;
        this.ld = ld;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
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

    public LocalDate getLd() {
        return ld;
    }

    public void setLd(LocalDate ld) {
        this.ld = ld;
    }
}
