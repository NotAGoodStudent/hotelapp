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

   public static Hotel hotel;
   public FileClass f = new FileClass();


    public void createHotel(String hotelname)
    {
        hotel = new Hotel(hotelname);
        f.fillRoomList(hotel);
        f.fillClientList(hotel);
        f.fillConfirmedListArray(hotel, Layout.deftable2);
        f.fillPendentListArray(hotel, Layout.deftable);

    }



    public void addHotelRoom(int roomNumber, int personNumber)
    {

        Room r = Room.roomExists(hotel, roomNumber);
        if(r == null)
        {
            Room room = new Room(roomNumber, personNumber);
            hotel.getRoomList().add(room);
            f.addDataToRoomFile(room);
            JOptionPane.showMessageDialog(null, "Room added successfully!");


        }

        else
            {
                int option = JOptionPane.showConfirmDialog(null, "The room already exists, would you like to increase the guest capacity from "+ r.getCapacity()+ " to "+(r.getCapacity()+personNumber)+"?");
                switch (option){
                    case 0:
                        r.setCapacity((r.getCapacity()+personNumber));
                        f.readAndReplaceRooms(r);
                        JOptionPane.showMessageDialog(null, "Room has been updated!");

                        break;
                    case 1:
                    case 2:
                        break;


                }





            }


    }

    public LocalDate returnLDfromJcal(Component c)
    {
        JCalendar jc = ((JCalendar) c);
        long ms = jc.getDate().getTime();
        return Instant.ofEpochMilli(ms).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean addBooking(LocalDate in, LocalDate out, int numb, String id, int nights, String name, String surname, DefaultTableModel deftable)
    {
        Client client = new Client(id, name, surname);
        Booking b = new Booking(in, numb, client);
        b.setEnd(out);

        for(Room r : hotel.getRoomList())
        {

            if(r.getCapacity() == numb && !Booking.isRoomBooked(r.getRoomnumb(), in, hotel) || r.getCapacity()+1 == numb && !Booking.isRoomBooked(r.getRoomnumb(), in, hotel))
            {

                b.setConfirmed(false);
                b.setRoom(r);
                hotel.getBookings().add(b);
                f.addPendentListToFile(b);
                deftable.addRow(new Object[]{client.getDNI().toUpperCase(), in.getDayOfMonth()+"/"+in.getMonthValue()+"/"+in.getYear(), b.getPersonNumb(), r.getRoomnumb()});
                Client c = Client.checkIfClientExists(hotel, id);
                if(c == null)
                {
                    hotel.getClientList().add(client);
                    f.addClientToFile(client);
                }
                JOptionPane.showMessageDialog(null, "Room booked!");
                return  true;
            }
        }



        JOptionPane.showMessageDialog(null, "There's no available rooms", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;


    }

    public void clearText(JTextField jdni, JTextField jname, JTextField surname, JTextField jpersonnumb, JTextField jnightnumb)
    {
        jdni.setText(null);
        jname.setText(null);
        surname.setText(null);
        jpersonnumb.setText(null);
        jnightnumb.setText(null);
    }

    public void clearIcons(JLabel dniIc, JLabel nameIc, JLabel surnameIc, JLabel personIc, JLabel nightIC)
    {
        dniIc.setIcon(null);
        nameIc.setIcon(null);
        surnameIc.setIcon(null);
        personIc.setIcon(null);
        nightIC.setIcon(null);
    }

    public void confirmBookings(int row, DefaultTableModel def, Hotel hotel, DefaultTableModel def2, JTable management2)
    {
        Client client = Client.returnClientFromRow(row, def, hotel);
        String[] strDate = ((String) def.getValueAt(row, 1)).split("/");
        LocalDate ld = objectToLD(strDate);
        System.out.println(client.getDNI() + " "+ client.getName()+" "+ client.getSurname());
        Booking booking = Booking.returnConfirmationBooking((String) def.getValueAt(row, 0), hotel, ld);
        if (booking != null && client != null) {
            booking.setConfirmed(true);
            def2.addRow(new Object[]{client.getDNI().toUpperCase(), client.getName().toUpperCase(), client.getSurname().toUpperCase(), def.getValueAt(row, 3), def.getValueAt(row, 1)});
            management2.getColumnModel().getColumn(4).setMinWidth(0);
            management2.getColumnModel().getColumn(4).setMaxWidth(0);
            management2.getColumnModel().getColumn(4).setWidth(0);
            def.removeRow(row);
            f.addConfirmedListToFile(booking);
            f.deletePendent(booking);
        }



    }

    public LocalDate objectToLD(String[] str)
    {
        int numb[] = new int[str.length];

        for(int x = 0; x < str.length;x++){

            numb[x] = Integer.parseInt(str[x]);
        }

        return  LocalDate.of(numb[2], numb[1], numb[0]);

    }

    public void clearTable(DefaultTableModel def)
    {
        while(def.getRowCount()!=0) def.removeRow(0);
    }

    public LocalDate returnLDFromDateChooser(JDateChooser jdc)
    {
        Date date = jdc.getDate();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void printDependingOnButton(JDateChooser jdc, JToggleButton jtb, DefaultTableModel def2, boolean toggled)
    {
        LocalDate ld = returnLDFromDateChooser(jdc);
        Booking.listBookings(ld, hotel, toggled, def2);
    }

    public void addClientToJlist(JTextField searchBar, DefaultListModel defcli)
    {
        ArrayList<Client> saveCoincidences = new ArrayList<>();
        for(Client c: hotel.getClientList())
        {
            if(c.getName().toLowerCase().contains(searchBar.getText()) && !checkIfExistentInCoincidences(saveCoincidences, c) || c.getSurname().toLowerCase().contains(searchBar.getText()) && !checkIfExistentInCoincidences(saveCoincidences, c) || c.getDNI().toLowerCase().contains(searchBar.getText()) && !checkIfExistentInCoincidences(saveCoincidences, c))
            {
                saveCoincidences.add(c);
            }

        }
        addCoincidencesToJlist(saveCoincidences, defcli);
        saveCoincidences.clear();

    }

    public void addCoincidencesToJlist(ArrayList<Client> coincidences, DefaultListModel defcli)
    {
        for(Client c: coincidences)
        {
            defcli.addElement(c);
        }
    }

    public static boolean checkIfExistentInCoincidences(ArrayList<Client> coincidences, Client c)
    {
       for(Client cli : coincidences)
       {
           if(cli.getDNI().equalsIgnoreCase(c.getDNI())) return true;
       }

       return false;
    }

    public void addBookingsToJlist(DefaultListModel defbooking, Client c)
    {
        for(Booking b : hotel.getBookings())
        {
            if(b.getClient().getDNI().equalsIgnoreCase(c.getDNI())) defbooking.addElement(b);
        }
    }

    public void deleteBooking(Booking b, DefaultTableModel def1, DefaultTableModel def2)
    {


        if(!b.isConfirmed())
        {
            System.out.println(b.isConfirmed());
            int x = returnRowToDeleteDef1(def1, b);
            Booking.deleteBooking(b, hotel);
            f.deletePendent(b);
            def1.removeRow(x);
        }
        else {

            System.out.println(b.isConfirmed());
            int x = returnRowToDeleteDef2(def2, b);
            Booking.deleteBooking(b, hotel);
            f.deleteConfirmed(b);
            def2.removeRow(x);
        }


    }

    public int returnRowToDeleteDef1(DefaultTableModel def, Booking b)
    {



        for(int x = 0; x < def.getRowCount();x++)
        {
            String[] str = def.getValueAt(x,1).toString().split("/");
            LocalDate ld = objectToLD(str);

            if(def.getValueAt(x, 0).equals(b.getClient().getDNI().toUpperCase()) && !b.isConfirmed() && ld.isEqual(b.getStart())) return x;
        }
        return -1;
    }

    public  int returnRowToDeleteDef2(DefaultTableModel def, Booking b)
    {
        for(int x = 0; x < def.getRowCount();x++)
        {
            String[] str = def.getValueAt(x,4).toString().split("/");
            LocalDate ld = objectToLD(str);
            if(def.getValueAt(x, 0).equals(b.getClient().getDNI().toUpperCase()) && b.isConfirmed() && ld.isEqual(b.getStart())) return x;
        }

        return -1;
    }







}


