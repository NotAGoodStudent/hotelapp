package classes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class FileClass
    {
        File roomFile, clientFile, confirmedBookingsFile, pendentBookingsFile, folder;
        BufferedWriter bWriter;
        FileWriter fWriter;
        BufferedReader bReader;
        FileReader fReader;


        public FileClass()
        {
            createDirectory();
            createFiles();
        }

        //We create a folder called 'Data' and if it already exixsts it'll print our an error
        public void createDirectory()
        {
            folder = new File("src"+ File.separator+"data");

            if(folder.mkdir()) System.out.println("Folder created");
            else System.err.println("Folder already exists");
        }

        //Here we create the files and we handle some possible program crashers

        public void createFiles()
        {
            roomFile = new File("src"+ File.separator+"data"+File.separator+"Room.txt");
            if(roomFile.exists()) System.err.println("File already exists");
            else
                {
                    try {
                        if(roomFile.createNewFile()) System.out.println("File created successfully");
                    } catch (IOException e) {
                        System.err.print("File could not be created: "+e);
                    }
                }
            clientFile = new File("src"+ File.separator+"Data"+ File.separator +"Client.txt");
            if(clientFile.exists()) System.err.println("File already exists");
            else
            {
                try {
                    if(clientFile.createNewFile()) System.out.println("File created successfully");
                } catch (IOException e) {
                    System.err.print("File could not be created: "+e);
                }
            }

            confirmedBookingsFile = new File("src"+ File.separator+"Data"+ File.separator +"Confirmed.txt");
            if(confirmedBookingsFile.exists()) System.err.println("File already exists");
            else
            {
                try {
                    if(confirmedBookingsFile.createNewFile()) System.out.println("File created successfully");
                } catch (IOException e) {
                    System.err.print("File could not be created: "+e);
                }
            }

            pendentBookingsFile = new File("src"+ File.separator+"Data"+ File.separator +"Pendent.txt");
            if(pendentBookingsFile.exists()) System.err.println("File already exists");
            else
            {
                try {
                    if(pendentBookingsFile.createNewFile()) System.out.println("File created successfully");
                } catch (IOException e) {
                    System.err.print("File could not be created: "+e);
                }
            }


        }

        public void addDataToRoomFile(Room r)  {
            try
            {

                fWriter = new FileWriter(roomFile, true);
                bWriter = new BufferedWriter(fWriter);

            } catch (IOException e) {
                e.printStackTrace();
            }


            try
            {
                bWriter.write(Integer.toString(r.getRoomnumb()));
                bWriter.write("-");
                bWriter.write(Integer.toString(r.getCapacity()));
                bWriter.write(System.lineSeparator());

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try {
                bWriter.close();
            } catch(IOException e)
            {
                e.printStackTrace();
            }


        }

        public void readAndReplaceRooms(Room r)
        {
            File temp = new File("src"+ File.separator+"Data"+ File.separator +"RoomTemp.txt");
            try{

                fReader = new FileReader(roomFile);
                fWriter = new FileWriter(temp);
                bReader = new BufferedReader(fReader);
                bWriter = new BufferedWriter(fWriter);

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

            String currentLine = "";
            while(true) {
                try {
                    if (!((currentLine = bReader.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String[] line = currentLine.split("-");


                if ((r.getRoomnumb() == Integer.parseInt(line[0]))) {
                    try {
                        bWriter.write(Integer.toString(r.getRoomnumb()));
                        bWriter.write("-");
                        bWriter.write(Integer.toString(r.getCapacity()));
                        bWriter.write(System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                else
                    {
                        try
                        {
                            bWriter.write(line[0]);
                            bWriter.write("-");
                            bWriter.write(line[1]);
                            bWriter.write(System.lineSeparator());
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
            }

            try
            {
                bReader.close();
                bWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            roomFile.delete();
            temp.renameTo(roomFile);


        }

        public void fillRoomList(Hotel hotel) {
            try
            {
                fReader = new FileReader(roomFile);
                bReader = new BufferedReader(fReader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String currentLine = "";

            while(true)
            {
                try
                {
                    if(!((currentLine = bReader.readLine())!= null)) break;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String[] line = currentLine.split("-");

                Room room = new Room(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                hotel.getRoomList().add(room);



            }

            try {
                bReader.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void addClientToFile(Client c)
        {
            try
            {
                fWriter = new FileWriter(clientFile, true);
                bWriter = new BufferedWriter(fWriter);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                bWriter.write(c.getDNI());
                bWriter.write("-");
                bWriter.write(c.getName());
                bWriter.write("-");
                bWriter.write(c.getSurname());
                bWriter.write(System.lineSeparator());


            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try {
                bWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }

        public void fillClientList(Hotel hotel)
        {
            try
            {
                fReader = new FileReader(clientFile);
                bReader = new BufferedReader(fReader);

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            String currentLine = "";
            while(true)
            {
                try{
                    if(!((currentLine = bReader.readLine())!= null)) break;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                    String[] line = currentLine.split("-");

                    Client client = new Client(line[0], line[1], line[2]);
                    hotel.getClientList().add(client);




            }

            try
            {
                bReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void addConfirmedListToFile(Booking b)
        {
            try {
                fWriter = new FileWriter(confirmedBookingsFile, true);
                bWriter = new BufferedWriter(fWriter);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                bWriter.write(b.getClient().getDNI()+"-"+b.getRoom().getRoomnumb()+"-"+b.getPersonNumb()+"-");
                bWriter.write(b.getStart().getDayOfMonth()+"/"+b.getStart().getMonthValue()+"/"+b.getStart().getYear()+"-");
                bWriter.write(b.getEnd().getDayOfMonth()+"/"+b.getEnd().getMonthValue()+"/"+b.getEnd().getYear());
                bWriter.write(System.lineSeparator());
            } catch(IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                bWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void fillConfirmedListArray(Hotel hotel, DefaultTableModel def)
        {
            try
            {
                fReader = new FileReader(confirmedBookingsFile);
                bReader = new BufferedReader(fReader);
            } catch(IOException e)
            {
                e.printStackTrace();
            }

            String currentLine = "";

            while(true)
            {
                try {
                    if (!((currentLine = bReader.readLine()) != null)) break;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                String[] line = currentLine.split("-");
                Client c = new Client("sdf", "sdf", "sdfg");
                Room r = new Room(1,2);
                for(Client client : hotel.getClientList())
                {
                    if(client.getDNI().equalsIgnoreCase(line[0]))
                    {

                        c = client;
                        break;
                    }
                }

                for(Room room : hotel.getRoomList())
                {
                    if(room.getRoomnumb()== Integer.parseInt(line[1]))
                    {

                        r = room;
                        break;
                    }
                }


                String[] startDate = line[3].split("/");
                String[] endDate = line[4].split("/");
                LocalDate startLD = LocalDate.of(Integer.parseInt(startDate[2]), Integer.parseInt(startDate[1]), Integer.parseInt(startDate[0]));
                LocalDate endLD = LocalDate.of(Integer.parseInt(endDate[2]), Integer.parseInt(endDate[1]), Integer.parseInt(endDate[0]));
                Booking b = new Booking(startLD, Integer.parseInt(line[2]), c);
                b.setEnd(endLD);
                b.setConfirmed(true);
                b.setRoom(r);
                hotel.getBookings().add(b);
                def.addRow(new Object[]{c.getDNI().toUpperCase(), c.getName().toUpperCase(), c.getSurname().toUpperCase(), b.getRoom().getRoomnumb(), startLD.getDayOfMonth()+"/"+startLD.getMonthValue()+"/"+startLD.getYear()});




            }

            try{
                bReader.close();
            } catch(IOException e)
            {
                e.printStackTrace();
            }


        }

        public void addPendentListToFile(Booking b)
        {
            try {
                fWriter = new FileWriter(pendentBookingsFile, true);
                bWriter = new BufferedWriter(fWriter);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                bWriter.write(b.getClient().getDNI()+"-"+b.getRoom().getRoomnumb()+"-"+b.getPersonNumb()+"-");
                bWriter.write(b.getStart().getDayOfMonth()+"/"+b.getStart().getMonthValue()+"/"+b.getStart().getYear()+"-");
                bWriter.write(b.getEnd().getDayOfMonth()+"/"+b.getEnd().getMonthValue()+"/"+b.getEnd().getYear());
                bWriter.write(System.lineSeparator());
            } catch(IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                bWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void fillPendentListArray(Hotel hotel, DefaultTableModel def)
        {
            try
            {
                fReader = new FileReader(pendentBookingsFile);
                bReader = new BufferedReader(fReader);
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            String currentLine = "";

            while (true)
            {
                try
                {
                    if(!((currentLine = bReader.readLine())!= null)) break;
                } catch(IOException e)
                {
                    e.printStackTrace();
                }

                String[] line = currentLine.split("-");
                Client c = new Client("sdf", "sdf", "sdf");
                Room r = new Room(1,2);
                for(Client client : hotel.getClientList())
                {
                    if(client.getDNI().equalsIgnoreCase(line[0]))
                    {
                        c = client;
                        break;
                    }
                }

                for(Room room : hotel.getRoomList())
                {
                    if(room.getRoomnumb() == Integer.parseInt(line[1]))
                    {
                        r = room;
                        break;
                    }
                }

                String[] startString = line[3].split("/");
                String[] endString = line[4].split("/");
                LocalDate startLD = LocalDate.of(Integer.parseInt(startString[2]), Integer.parseInt(startString[1]), Integer.parseInt(startString[0]));
                LocalDate endLD = LocalDate.of(Integer.parseInt(endString[2]), Integer.parseInt(endString[1]), Integer.parseInt(endString[0]));
                Booking b = new Booking(startLD, Integer.parseInt(line[2]), c);
                b.setEnd(endLD);
                b.setConfirmed(false);
                b.setRoom(r);
                hotel.getBookings().add(b);
                def.addRow(new Object[]{c.getDNI().toUpperCase(), startLD.getDayOfMonth()+"/"+startLD.getMonthValue()+"/"+startLD.getYear(), b.getPersonNumb(), r.getRoomnumb()});


            }
        }

        public void deleteConfirmed(Booking b)
        {
            File temp = new File("src"+ File.separator+"Data"+ File.separator +"RoomTemp.txt");
            try {

                fReader = new FileReader(confirmedBookingsFile);
                bReader = new BufferedReader(fReader);
                fWriter = new FileWriter(temp);
                bWriter = new BufferedWriter(fWriter);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            String currentLine = "";

            while(true)
            {
                try
                {
                    if(!((currentLine = bReader.readLine())!= null)) break;
                } catch(IOException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    String[] line = currentLine.split("-");
                    String[] ldStr = line[3].split("/");
                    LocalDate ld = LocalDate.of(Integer.parseInt(ldStr[2]), Integer.parseInt(ldStr[1]), Integer.parseInt(ldStr[0]));
                    if(b.getClient().getDNI().equalsIgnoreCase(line[0]) && b.getStart().isEqual(ld))
                    {
                        System.out.println("nein");
                    }

                    else
                        {
                            bWriter.write(b.getClient().getDNI()+"-"+b.getRoom().getRoomnumb()+"-"+b.getPersonNumb()+"-");
                            bWriter.write(b.getStart().getDayOfMonth()+"/"+b.getStart().getMonthValue()+"/"+b.getStart().getYear()+"-");
                            bWriter.write(b.getEnd().getDayOfMonth()+"/"+b.getEnd().getMonthValue()+"/"+b.getEnd().getYear());
                        }




                } catch(IOException e)
                {
                    e.printStackTrace();
                }



            }
            try
            {
                bWriter.close();
                bReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            confirmedBookingsFile.delete();
            temp.renameTo(confirmedBookingsFile);



        }

        public void deletePendent(Booking b)
        {
            File temp = new File("src"+ File.separator+"Data"+ File.separator +"RoomTemp.txt");
            try
            {

                fReader = new FileReader(pendentBookingsFile);
                bReader = new BufferedReader(fReader);
                fWriter = new FileWriter(temp);
                bWriter = new BufferedWriter(fWriter);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            String currentLine = "";

            while(true)
            {
                try
                {
                    if(!((currentLine = bReader.readLine())!= null)) break;
                } catch(IOException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    String[] line = currentLine.split("-");
                    String[] ldStr = line[3].split("/");
                    LocalDate ld = LocalDate.of(Integer.parseInt(ldStr[2]), Integer.parseInt(ldStr[1]), Integer.parseInt(ldStr[0]));
                    if(b.getClient().getDNI().equalsIgnoreCase(line[0]) && b.getStart().isEqual(ld))
                    {

                    }

                    else
                    {
                        bWriter.write(b.getClient().getDNI()+"-"+b.getRoom().getRoomnumb()+"-"+b.getPersonNumb()+"-");
                        bWriter.write(b.getStart().getDayOfMonth()+"/"+b.getStart().getMonthValue()+"/"+b.getStart().getYear()+"-");
                        bWriter.write(b.getEnd().getDayOfMonth()+"/"+b.getEnd().getMonthValue()+"/"+b.getEnd().getYear());
                    }


                }

                catch(IOException e)
                {
                    e.printStackTrace();
                }

            }
            try
            {
                bWriter.close();
                bReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


            pendentBookingsFile.delete();
            temp.renameTo(pendentBookingsFile);

        }

    }

