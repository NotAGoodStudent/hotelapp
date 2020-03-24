package controller;

import classes.Client;
import com.toedter.calendar.JCalendar;
import layout.Layout;
import classes.*;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;


public class Controller
{


    public static void addData()
    {
        int numb = Integer.parseInt(Layout.jpersonnumb.getText());
        int nights = Integer.parseInt(Layout.jnightnumb.getText());
        Client client = new Client(Layout.jdni.getText().toString(), Layout.jname.getText().toString() ,Layout.jsurname.getText().toString());
        LocalDate gotIn = Controller.returnLDfromJcal(Layout.bookdate);
        LocalDate end = gotIn.plusDays(nights);
        System.out.println(end);
        Booking booking = new Booking(gotIn, numb, nights, client);
        booking.setEnd(end);
        for (Room r : Layout.currentHotel.getRoomList())
        {

            if (r.getCapacity() == numb && !isRoomBooked(r.getRoomnumb(), gotIn) || r.getCapacity()+1 == numb && !isRoomBooked(r.getRoomnumb(), gotIn)) {
                booking.setRoom(r);
                Layout.bookings.add(booking);
                Layout.currentHotel.setPendentList(Layout.bookings);
                System.out.println(Layout.currentHotel.getPendentList().isEmpty());
                Layout.deftable.addRow(new Object[]{client.getDNI().toUpperCase(), gotIn.getDayOfMonth() + "/" + gotIn.getMonthValue() + "/" + gotIn.getYear(), booking.getPersonNumb(), r.getRoomnumb()});
                Client c = Controller.checkIfClientNotNeW(Layout.clients, Layout.jdni);
                if (c != null) Layout.clients.add(client);
                Controller.clearText();
                Controller.clearIcons();

            }

            else Layout.jop.showMessageDialog(null,"The room is occupied", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public static Client checkIfClientNotNeW(ArrayList<Client> clients, JTextField jdni)
    {
        for(Client c : clients)
        {
            if(c.getDNI().equals(jdni.getText().toString()))
            {
                return c;
            }
        }

        return null;
    }

    public static void clearText()
    {
        Layout.jdni.setText(null);
        Layout.jname.setText(null);
        Layout.jsurname.setText(null);
        Layout.jpersonnumb.setText(null);
        Layout.jnightnumb.setText(null);

    }

    public static void clearIcons()
    {
        Layout.dniIC.setIcon(null);
        Layout.nameIC.setIcon(null);
        Layout.surnameIC.setIcon(null);
        Layout.nightsIC.setIcon(null);
        Layout.guestsIC.setIcon(null);

    }

    private static LocalDate returnLDfromJcal(Component c)
    {
        JCalendar jc = ((JCalendar) c);
        long ms = jc.getDate().getTime();
        return Instant.ofEpochMilli(ms).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Room registerRoom(int roomNum, int numbPersons, ArrayList<Room> rooms)
    {
        if(rooms.size() == 0)
        {
            return null;
        }
        else
            {
                for (Room r : rooms)
                {

                    if (r.getRoomnumb() == roomNum)
                    {
                        return r;
                    }
                }
                return null;

            }



    }

    public static Hotel checkIfHotelExists(JTextField jhotel, ArrayList<Hotel> hotels)
    {
        for(Hotel h : hotels)
        {
            if(h.getHotelName().equalsIgnoreCase(jhotel.getText()))
            {
                return h;
            }
        }

        return null;
    }

    public static boolean isRoomBooked(int roomNumb, LocalDate in)
    {

        for(Booking b : Layout.currentHotel.getPendentList())
        {


            if(b.getRoom().getRoomnumb() == roomNumb && in.isBefore(b.getEnd())) return true;

        }

        return false;
    }

}
