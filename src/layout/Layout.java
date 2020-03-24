package layout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import classes.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import controller.*;

public class Layout extends JFrame
{
    // so i can commit properly

    public static Hotel currentHotel;
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Hotel> hotels = new ArrayList<>();
    public static final long serialVersionUID = 1L;
    public JPanel panel;
    public static JLabel managtext, managtext1, managtext2, dni, name, surname, guests, nights, caltext, hotelname, addroom, roomnumb, personnumb, checkbooking, intrname, dniIC, nameIC, surnameIC, guestsIC, nightsIC, hotelIC, roomnumbIC, getPersonnumbbackIC;
    public JTable management2, management1;
    public JButton bookingit, save, save1, delete;
    public JDateChooser choosedate;
    public static JCalendar bookdate;
    public static JTextField jdni, jname, jsurname, jpersonnumb, jnightnumb, jhotel, jroomnumb,  jpersonnumbback, jcheckbooking, area1, area2;
    public ImageIcon  trueic, falseic, falseredic, trueredic;
    public static DefaultTableModel deftable, deftable2;
    public static JOptionPane jop;

    public Layout()
    {
        setVisible(true);
        setSize(new Dimension(1200,700));
        setLocationRelativeTo(null);
        setTitle("RuralCiv");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        initComponents();
    }

    private void initComponents()
    {
        addPanels();
        addTextManagement();
        addTextClient();
        addTextBack();
        createIcons();
        addKeyListenerToTextField();
        addHotelName();
        getBookingInfo();
        registerRoomButton();

    }

    public void addPanels()
    {
        int width = this.getWidth()/3;
        int height = this.getHeight();

        for(int i = 0; i < 3;i++)
        {
            JPanel jp = new JPanel();
            jp.setBounds(width*i, 0, width-2, height);
            jp.setBackground(Color.DARK_GRAY);
            jp.setLayout(null);
            this.getContentPane().add(jp);

        }

    }

    public JLabel defineFontTitle()
    {
        JLabel label = new JLabel();
        label.setFont(new Font("Montserrat", Font.BOLD, 27));
        label.setForeground(Color.white);
        return label;
    }

    public JLabel defineFontNonTitle()
    {
        JLabel label = new JLabel();
        label.setFont(new Font("Montserrat", Font.PLAIN, 14));
        label.setForeground(Color.white);
        return label;
    }

    public void addTextManagement()
    {
        JLabel title = defineFontTitle();
        JPanel jp = (JPanel) this.getContentPane().getComponent(0);
        title.setText("Management");
        title.setBounds(0, 20, jp.getWidth(), 27);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        jp.add(title);

        managtext = defineFontNonTitle();
        managtext.setText("Available bookings: ");
        managtext.setBounds(5, 100, jp.getWidth(), 17);
        managtext.setHorizontalAlignment(SwingConstants.LEFT);
        jp.add(managtext);

        deftable = new DefaultTableModel();
        deftable.addColumn("Booking ID");
        deftable.addColumn("Day");
        deftable.addColumn("Guests");
        deftable.addColumn("Room number");
        management1 = new JTable(deftable);
        management1.setBounds(5, 130, 360, 200);
        management1.setFont(new Font("Montserrat", Font.PLAIN, 14));
        jp.add(management1);

        JScrollPane jsp = new JScrollPane(management1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(5, 130, 360, 200);
        jp.add(jsp);


        managtext1 = defineFontNonTitle();
        managtext1.setText("Bookings: ");
        managtext1.setBounds(5, 370, jp.getWidth(), 17);
        managtext1.setHorizontalAlignment(SwingConstants.LEFT);
        jp.add(managtext1);


        deftable2 = new DefaultTableModel();
        deftable2.addColumn("Booking ID");
        deftable2.addColumn("Day");
        deftable2.addColumn("Guests");
        deftable2.addColumn("Room number");
        management2 = new JTable(deftable2);
        management2.setBounds(5, 400, 360, 200);
        management2.setFont(new Font("Montserrat", Font.PLAIN, 14));
        jp.add(management2);

        JScrollPane jsp2 = new JScrollPane(management2,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp2.setBounds(5, 400, 360, 200);
        jp.add(jsp2);

        choosedate = new JDateChooser();
        choosedate.setBounds(265, 370 ,100, 20);
        jp.add(choosedate);

    }

    public void addTextClient()
    {
        JLabel title = defineFontTitle();
        JPanel jp = (JPanel) this.getContentPane().getComponent(1);
        title.setText("Clients");
        title.setBounds(0, 20, jp.getWidth(), 27);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        jp.add(title);

        dni = defineFontNonTitle();
        dni.setText("DNI:");
        dni.setBounds(8, 100, jp.getWidth(), 17);
        jp.add(dni);

        jdni = new JTextField();
        jdni.setBounds(160, 95, 150, 23);
        jp.add(jdni);

        dniIC = new JLabel();
        dniIC.setBounds(320, 97, 20, 20);
        jp.add(dniIC);

        name = defineFontNonTitle();
        name.setText("Name:");
        name.setBounds(8, 135, jp.getWidth(), 17);
        jp.add(name);

        jname = new JTextField();
        jname.setBounds(160, 130, 150, 23);
        jp.add(jname);

        nameIC = new JLabel();
        nameIC.setBounds(320, 132, 20, 20);
        jp.add(nameIC);

        surname = defineFontNonTitle();
        surname.setText("Surname: ");
        surname.setBounds(8, 170, jp.getWidth(), 17);
        jp.add(surname);

        jsurname = new JTextField();
        jsurname.setBounds(160, 165, 150, 23);
        jp.add(jsurname);

        surnameIC = new JLabel();
        surnameIC.setBounds(320, 167, 20, 20);
        jp.add(surnameIC);

        guests = defineFontNonTitle();
        guests.setText("Guests: ");
        guests.setBounds(8, 205, jp.getWidth(), 17);
        jp.add(guests);

        jpersonnumb = new JTextField();
        jpersonnumb.setBounds(160, 200, 50, 23);
        jp.add(jpersonnumb);

        guestsIC = new JLabel();
        guestsIC.setBounds(320, 202, 20, 20);
        jp.add(guestsIC);

        nights = defineFontNonTitle();
        nights.setText("Nights: ");
        nights.setBounds(8, 240, jp.getWidth(), 17);
        jp.add(nights);

        jnightnumb = new JTextField();
        jnightnumb.setBounds(160, 235, 50, 23);
        jp.add(jnightnumb);

        nightsIC = new JLabel();
        nightsIC.setBounds(320, 237, 20, 20);
        jp.add(nightsIC);

        caltext = defineFontNonTitle();
        caltext.setText("Choose a date: ");
        caltext.setBounds(8, 285, jp.getWidth(), 17);
        jp.add(caltext);

        bookdate = new JCalendar();
        bookdate.setBounds(50, 330, 280, 220);
        jp.add(bookdate);

        bookingit = new JButton("Book now!");
        bookingit.setBounds(120, 590, 150, 30);
        bookingit.setEnabled(false);
        jp.add(bookingit);


    }

    public void addTextBack()
    {
        JLabel title = defineFontTitle();
        JPanel jp = (JPanel) this.getContentPane().getComponent(2);
        title.setText("Back ");
        title.setBounds(0, 20, jp.getWidth(), 27);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        jp.add(title);

        hotelname = defineFontNonTitle();
        hotelname.setText("Hotel name: ");
        hotelname.setBounds(8, 100, jp.getWidth(), 17);
        jp.add(hotelname);

        jhotel = new JTextField();
        jhotel.setBounds(160, 95, 150, 23);
        jp.add(jhotel);

        save = new JButton("Save!");
        save.setEnabled(true);
        save.setBounds(160, 135, 90, 30);
        jp.add(save);

        addroom = defineFontNonTitle();
        addroom.setText("Register a new room: ");
        addroom.setBounds(8, 180, jp.getWidth(), 17);
        jp.add(addroom);

        roomnumb = defineFontNonTitle();
        roomnumb.setText("Numb: ");
        roomnumb.setBounds(8, 215, jp.getWidth(), 17);
        jp.add(roomnumb);

        jroomnumb = new JTextField();
        jroomnumb.setBounds(70, 210, 50, 23);
        jp.add(jroomnumb);

        personnumb = defineFontNonTitle();
        personnumb.setText("# Pers: ");
        personnumb.setBounds(250, 215, jp.getWidth(),17);
        jp.add(personnumb);

        jpersonnumbback = new JTextField();
        jpersonnumbback.setBounds(312, 210, 50, 23);
        jp.add(jpersonnumbback);

        save1 = new JButton("Save!");
        save1.setBounds(160,265,90,30);
        save1.setEnabled(true);
        jp.add(save1);


        checkbooking = defineFontNonTitle();
        checkbooking.setText("Check booking: ");
        checkbooking.setBounds(8, 320, jp.getWidth(), 17);
        jp.add(checkbooking);

        jcheckbooking = new JTextField();
        jcheckbooking.setBounds(145, 340, 150, 23);
        jp.add(jcheckbooking);

        intrname = defineFontNonTitle();
        intrname.setText("Client name: ");
        intrname.setBounds(8,360, jp.getWidth(), 17);
        jp.add(intrname);

        area1 = new JTextField();
        area1.setBounds(8, 400, 150, 150);
        jp.add(area1);

        area2 = new JTextField();
        area2.setBounds(230, 400, 150, 150);
        jp.add(area2);

        delete = new JButton("Delete!");
        delete.setEnabled(true);
        delete.setBounds(160, 600, 90, 30);
        jp.add(delete);

    }

    private void createIcons()
    {
        trueic = new ImageIcon("src/icons/dot1.png");
        falseic = new ImageIcon("src/icons/dot2.png");
        trueredic = new ImageIcon(trueic.getImage().getScaledInstance(dniIC.getWidth(), dniIC.getHeight(), Image.SCALE_SMOOTH));
        falseredic = new ImageIcon(falseic.getImage().getScaledInstance(dniIC.getWidth(), dniIC.getHeight(), Image.SCALE_SMOOTH));
    }

    private void addKeyListenerToTextField()
    {

        KeyListener ki = new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if(e.getComponent().equals(jname))
                {
                    if(isonlytext(jname))  nameIC.setIcon(trueredic);

                    else nameIC.setIcon(falseredic);
                }

                if(e.getComponent().equals(jdni))
                {
                    if(dnicheck(jdni)) dniIC.setIcon(trueredic);


                    else dniIC.setIcon(falseredic);
                }

                if(e.getComponent().equals(jsurname))
                {
                    if(isonlytext(jsurname)) surnameIC.setIcon(trueredic);

                    else surnameIC.setIcon(falseredic);


                }

                if(e.getComponent().equals(jpersonnumb))
                {
                    if(isonlynumbersguests(jpersonnumb)) guestsIC.setIcon(trueredic);

                    else guestsIC.setIcon(falseredic);
                }

                if(e.getComponent().equals(jnightnumb))
                {
                    if(isonlynumbersnights(jnightnumb)) nightsIC.setIcon(trueredic);

                    else nightsIC.setIcon(falseredic);
                }

                if(isonlytext(jname) && isonlytext(jsurname) && dnicheck(jdni) && isonlynumbersguests(jpersonnumb) && isonlynumbersnights(jnightnumb)) bookingit.setEnabled(true);

                else bookingit.setEnabled(false);




            }
        };
        jname.addKeyListener(ki);
        jdni.addKeyListener(ki);
        jsurname.addKeyListener(ki);
        jpersonnumb.addKeyListener(ki);
        jnightnumb.addKeyListener(ki);
    }

    private boolean isonlynumbersguests(JTextField text)
    {
        if(text.getText().matches("\\d+"))
        {
            if(Integer.parseInt(text.getText()) < 5)
            {
                return true;
            }

            else return false;
        }

        else
            {
                return false;
            }
    }

    private boolean isonlynumbersnights(JTextField text)
    {
        if(text.getText().matches("\\d+"))
        {
            if(Integer.parseInt(text.getText()) < 31)
            {
                return true;
            }

            else return  false;
        }

        else
        {
            return false;
        }
    }

    private boolean isonlytext(JTextField text)
    {
        if(text.getText().matches("[a-zA-Z]+\\.?") || text.getText().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]+\\.?") || text.getText().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ_ ]+\\.?") || text.getText().matches("[a-zA-Z_ ]+\\.?"))
        {
            return true;
        }

        else return false;
    }

    private boolean dnicheck(JTextField text)
    {
        if(text.getText().matches("(?=.*[0-9])(?=.*[A-Za-z]).{9,9}"))
        {
            return true;
        }

        else return false;

    }

    private void addHotelName()
    {

        ActionListener ac = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == save && !jhotel.getText().isBlank())
                {
                    if(hotels.size() == 0)
                    {
                        System.out.println("check");
                        Layout.super.setTitle(jhotel.getText());
                        Hotel hotel = new Hotel(jhotel.getText());
                        currentHotel = hotel;
                        hotels.add(hotel);
                    }

                    else
                        {


                            Hotel hotel = Controller.checkIfHotelExists(jhotel, hotels);
                            if(hotel != null)
                            {
                                System.out.println("in");
                                int option = jop.showConfirmDialog(null, "This hotel name already exists, would you like to access it's management panel?");
                                switch (option){
                                    case 0:

                                        currentHotel.setRoomList(rooms);
                                        currentHotel.setPendentList(bookings);
                                        currentHotel.setClientList(clients);
                                        Layout.super.setTitle(jhotel.getText());
                                        currentHotel = hotel;
                                        rooms = currentHotel.getRoomList();
                                        bookings = currentHotel.getPendentList();
                                        clients = currentHotel.getClientList();
                                        jop.showMessageDialog(null,"Access allowed!");
                                        break;

                                    case 1:

                                        break;
                                    case 2:
                                        break;

                                }
                            }
                            else
                                {
                                    currentHotel.setRoomList(rooms);
                                    currentHotel.setPendentList(bookings);
                                    currentHotel.setClientList(clients);
                                    Layout.super.setTitle(jhotel.getText());
                                    hotel = new Hotel(jhotel.getText());
                                    currentHotel = hotel;
                                    rooms = currentHotel.getRoomList();
                                    bookings = currentHotel.getPendentList();
                                    clients = currentHotel.getClientList();
                                    hotels.add(hotel);
                                }
                        }
                }


            }
        }; save.addActionListener(ac);

    }

    private void getBookingInfo()
    {
        ActionListener ac = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == bookingit)
                {

                    Controller.addData();

                    bookingit.setEnabled(false);



                }

            }
        };
        bookingit.addActionListener(ac);
    }

    private void registerRoomButton()
    {
        ActionListener ac =  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == save1)
                {
                    int persnumb = Integer.parseInt(jpersonnumbback.getText());
                    int roomnumb = Integer.parseInt(jroomnumb.getText());
                    Room r = Controller.registerRoom(roomnumb, persnumb, rooms);
                    if(r == null)
                    {
                        Room room = new Room(roomnumb, persnumb);
                        rooms.add(room);
                        currentHotel.setRoomList(rooms);
                        jop.showMessageDialog(null, "Room added successfully!");
                    }

                    else
                        {
                            int sum = r.getCapacity() + persnumb;

                            int option = jop.showConfirmDialog(null, "This room already exists, would you like to update the max capacity to "+ sum+" ?");
                           switch (option)
                           {
                               case 0:


                                   r.setCapacity(sum);
                                   jop.showMessageDialog(null, "Capacity updated successfully!");
                                   break;

                               case 1:

                                    break;
                               case 2:

                                    break;
                           }
                        }
                }
            }
        }; save1.addActionListener(ac);
    }



















}