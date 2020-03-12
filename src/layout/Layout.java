package layout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Layout extends JFrame
{
    private static final long serialVersionUID = 1L;
    public JPanel panel;
    public JLabel managtext, managtext1, managtext2, dni, name, surname, guests, nights, caltext, hotelname, addroom, roomnumb, personnumb, checkbooking, intrname;
    public JTable management2, management1;
    public JButton booking, save, save1, delete;
    public JDateChooser choosedate;
    public JCalendar bookdate;
    public JTextField jdni, jname, jsurname, jpersonnumb, jnightnumb, jhotel, jroomnumb,  jpersonnumbback, jcheckbooking;


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

        DefaultTableModel deftable = new DefaultTableModel();
        deftable.addColumn("Booking ID");
        deftable.addColumn("Day");
        deftable.addColumn("Persons");
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


        management2 = new JTable(deftable);
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

        name = defineFontNonTitle();
        name.setText("Name:");
        name.setBounds(8, 135, jp.getWidth(), 17);
        jp.add(name);

        jname = new JTextField();
        jname.setBounds(160, 130, 150, 23);
        jp.add(jname);

        surname = defineFontNonTitle();
        surname.setText("Surname: ");
        surname.setBounds(8, 170, jp.getWidth(), 17);
        jp.add(surname);

        jsurname = new JTextField();
        jsurname.setBounds(160, 165, 150, 23);
        jp.add(jsurname);

        guests = defineFontNonTitle();
        guests.setText("Guests: ");
        guests.setBounds(8, 205, jp.getWidth(), 17);
        jp.add(guests);

        jpersonnumb = new JTextField();
        jpersonnumb.setBounds(160, 200, 50, 23);
        jp.add(jpersonnumb);

        nights = defineFontNonTitle();
        nights.setText("Nights: ");
        nights.setBounds(8, 240, jp.getWidth(), 17);
        jp.add(nights);

        jnightnumb = new JTextField();
        jnightnumb.setBounds(160, 235, 50, 23);
        jp.add(jnightnumb);

        caltext = defineFontNonTitle();
        caltext.setText("Choose a date: ");
        caltext.setBounds(8, 285, jp.getWidth(), 17);
        jp.add(caltext);

        bookdate = new JCalendar();
        bookdate.setBounds(50, 330, 280, 220);
        jp.add(bookdate);

        booking = new JButton("Book now!");
        booking.setBounds(120, 590, 150, 30);
        booking.setEnabled(false);
        jp.add(booking);


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
        save.setEnabled(false);
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
        personnumb.setBounds(140, 215, jp.getWidth(),17);





    }
}