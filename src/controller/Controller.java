package controller;

import classes.Client;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import layout.Layout;
import classes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.print.Book;
import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class Controller
{


    public static ArrayList<Booking> checkSavedCoincidences = new ArrayList<>();
    public static ArrayList<Booking> checkSavedCoincidencesBooking = new ArrayList<>();
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
                if (c == null) Layout.clients.add(client);
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

    public static void addConfirmation(MouseEvent e, Hotel currentHotel)
    {
        int row = Layout.management1.rowAtPoint(e.getPoint());
        Booking propbooking = returnCliBooking((String) Layout.management1.getValueAt(row, 0));
        Layout.deftable2.addRow(new Object[]{Layout.management1.getValueAt(row, 0), propbooking.getClient().getName(), propbooking.getClient().getSurname(), Layout.management1.getValueAt(row, 3)});
        String id = (String) Layout.deftable.getValueAt(row, 0);
        String lili =  (String) Layout.deftable.getValueAt(row, 1);
        String[] ldStr = lili.split("/");
        LocalDate fild = returnLD(ldStr);
        Layout.deftable.removeRow(row);

        System.out.println(fild);

        for(Booking b : currentHotel.getPendentList())
        {

            if(b.getClient().getDNI().equalsIgnoreCase(id) && b.getStart().isEqual(fild))
            {
                System.out.println("YESS");
                Layout.confirmedBookings.add(b);
                break;
            }
        }


    }

    public static LocalDate returnLD(String[] ld)
    {
        int numb[] = new int[ld.length];
        for(int x = 0; x < ld.length;x++)
        {
            numb[x] = Integer.parseInt(ld[x]);
        }

        LocalDate ldd = LocalDate.of(numb[2], numb[1], numb[0]);
        System.out.println(ldd);
        return ldd;
    }

    public static void listBookingsIn(JDateChooser jdc)
    {
        LocalDate ld = getLDfromJChooser(jdc);
        for(Booking b : Layout.confirmedBookings)
        {
            System.out.println(b.getEnd());
            if(b.getStart().isEqual(ld))
            {
                Layout.deftable2.addRow(new Object[]{b.getClient().getDNI().toUpperCase(),  b.getClient().getName(), b.getClient().getSurname(), b.getRoom().getRoomnumb()});
            }
        }
    }

    public static void listBookingsOut(JDateChooser jdc)
    {
        LocalDate ld = getLDfromJChooser(jdc);


        System.out.println(Layout.confirmedBookings.isEmpty());
        for(Booking b : Layout.confirmedBookings)
        {
            System.out.println(b.getEnd());
            if(b.getEnd().isEqual(ld))
            {
                Layout.deftable2.addRow(new Object[]{b.getClient().getDNI().toUpperCase(),  b.getClient().getName(), b.getClient().getSurname(), b.getRoom().getRoomnumb()});
            }
        }
    }

    public static LocalDate getLDfromJChooser(JDateChooser jdc)
    {
        Date date = jdc.getDate();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Booking returnCliBooking(String id)
    {
        for(Booking  b : Layout.bookings)
        {

            if(b.getClient().getDNI().equalsIgnoreCase(id))
            {
                return b;
            }
        }

        return null;
    }

    public static void clearTable(DefaultTableModel deftable2)
    {
       while(deftable2.getRowCount()> 0) deftable2.removeRow(0);
    }

    public static void addCoincidences(ArrayList<Booking> savedCoincidences)
    {
        for(Booking b : savedCoincidences)
        {
            if(!checkNotRepeated(savedCoincidences, checkSavedCoincidences))
            {
                Client c = b.getClient();
                Layout.defclilist.addElement(c);
                checkSavedCoincidences.add(b);
            }
        }
    }

    public static boolean checkNotRepeated(ArrayList<Booking> savedCoincidences, ArrayList<Booking> repeated)
    {
        for(Booking b : savedCoincidences){

            for(Booking rep : repeated)  {

                if(b == rep)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addCoincidencesBooking(ArrayList<Booking> savedCoincidences, Client cli)
    {

        for(Booking b : savedCoincidences)
        {
            if(b.getClient().getDNI().equalsIgnoreCase(cli.getDNI()))
            {

                System.out.println("inn");
                Layout.defbookinglist.addElement(b);


            }
        }
    }







}
