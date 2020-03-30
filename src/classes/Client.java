package classes;

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


}


