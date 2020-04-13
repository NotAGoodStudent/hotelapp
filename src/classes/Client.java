package classes;

import javax.swing.table.DefaultTableModel;

public class Client
{
    private String DNI;
    private String name;
    private String surname;

    public Client(String DNI, String name, String surname)
    {
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString()
    {
        return this.getName().toUpperCase().charAt(0) + "." + this.getSurname() + " - " + this.getDNI();
    }

    public static Client checkIfClientExists(Hotel hotel, String id)
    {
        for(Client c : hotel.getClientList())
        {
            if(c.getDNI().equalsIgnoreCase(id))
            {

                return c;
            }
        }

        return null;
    }

    public static Client returnClientFromRow(int row, DefaultTableModel def, Hotel hotel)
    {
        for(Client c : hotel.getClientList()){

            if(c.getDNI().equalsIgnoreCase((String) def.getValueAt(row, 0))){

                System.out.println("Exists ofc");
                return c;
            }
        }

        return null;
    }


}


