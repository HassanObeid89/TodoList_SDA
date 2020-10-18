package hassan.todoList;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileHandler {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String filename = null;
    ArrayList<Task> todoList = new ArrayList<>();

    /**
     * Function to read data from the input file.
     * @throws IOException throws an exception when reading failed.
     */
    public void inputReader() throws IOException
    {
        Date date = null;
        JFileChooser chooser = new JFileChooser();

        File file = null;
        int returnValue = chooser.showOpenDialog( null ) ;
        if( returnValue == JFileChooser.APPROVE_OPTION )
        {
            file = chooser.getSelectedFile() ;
        }
        if(file != null)
        {
            filename = file.getPath();
        }

        BufferedReader inpFile = new BufferedReader(new FileReader(filename));
        String str;
        try{
            while((str = inpFile.readLine()) != null)
            {
                String str1 = str.toString();
                String[] arr = str1.split(";");
                try{
                    date = formatter.parse(arr[3]);
                }catch(ParseException p){
                    System.out.println("Error while reading the Date field");
                }

                todoList.add(new Task(arr[0],arr[1],arr[2],date));

            }
        }catch(IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Function to write from the file to the console when requested.
     * @throws IOException
     */
    public void outputWriter() throws IOException
    {
        BufferedWriter writer = new  BufferedWriter(new FileWriter(filename));

        for (Task out: todoList)
        {
            writer.write(out.getTaskName() + ";"+out.getProjectName()+";"+out.getStatus()+ ";"+ formatter.format(out.getTaskDate())+"\n");
        }

        writer.close();
    }
}
