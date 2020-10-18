
package hassan.todoList;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class TodoDemo {
    private ArrayList<Task> todoList = new ArrayList<>();
    private int statusOpen = 0, statusClosed = 0;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String filename = null;



    public void displayInput() {
        int counter = 0;
        int choice;


        System.out.println("Please Enter your choice - for sorting");
        System.out.println("1.Sort based on Date");
        System.out.println("2.Sort based on Project");

        choice = scanInput();


        if (choice == 1)
        {
            todoList.sort(Comparator.comparing(Task::getTaskDate));
        }
        else if (choice == 2)
        {
            todoList.sort(Comparator.comparing(Task::getProjectName));
        }else {

            throw new IndexOutOfBoundsException("Option not available");
        }

                String format1 = "%-9s %-40s %-43s %-12s %-15s";
                System.out.println(String.format(format1, "Task No", "Task Name", "ProjectName", "Status", " Date"));
                System.out.println("----------------------------------------------------------------------------------------------------------------------");


                for (Task file : todoList) {
                    counter = counter + 1;

                   System.out.println(String.format(format1, counter, file.getTaskName(), file.getProjectName(), file.getStatus(), formatter.format(file.getTaskDate())));
                    String changeCase = file.getStatus().toUpperCase();
                    if (changeCase.equals("OPEN")) {
                        statusOpen = statusOpen + 1;
                    } else if (changeCase.equals("CLOSED")) {
                        statusClosed = statusClosed + 1;
                    }
                }
                System.out.println("Number of Tasks open : " + statusOpen + "\n Number of Tasks Closed : " + statusClosed);
                statusOpen = 0;
                statusClosed = 0;


        }


    /**
     * Function to read integer input from console.
     * @return return the user input.
     */
    public int scanInput()
    {

        Scanner sc = new Scanner(System.in);
        return Integer.valueOf(sc.nextInt());


    }

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
     * Function to add new task and check the date of the task with the current date.
     * @param taskName Name of the new task.
     * @param projectName Name of the project related to the new task.
     * @param taskStatus Specify the task status whether open or close.
     * @param taskDate Set task date and check this date if its greater then today date.
     * @throws IOException
     * @throws ParseException
     */
    public void addRecord(String taskName,String projectName,String taskStatus,Date taskDate) throws IOException, ParseException
    {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date(); // Get the current date. Consider only the Date portion for comparison
        Date todayWithZeroTime = formatter.parse(formatter.format(today));
        Date inputDateWithZeroTime = formatter.parse(formatter.format(taskDate));

        if (inputDateWithZeroTime.compareTo(todayWithZeroTime)<0)
        {
            System.out.println("Date Entered should be greater than today");
        }
        else
        {
            todoList.add(new Task(taskName,projectName,taskStatus,taskDate));
        }

    }

    /**
     *
     * @param editIndex
     * @param inputField
     * @param changeField
     * @throws IOException
     * @throws ParseException
     */
    public void editRecord(int editIndex,String inputField,int changeField) throws IOException, ParseException

    {
        Date date = null;

        if (changeField == 1)
        {
            todoList.set(editIndex,new Task(inputField, todoList.get(editIndex).getProjectName(), todoList.get(editIndex).getStatus(), todoList.get(editIndex).getTaskDate()));
        }
        if (changeField == 2)
        {
            todoList.set(editIndex,new Task(todoList.get(editIndex).getTaskName(),inputField, todoList.get(editIndex).getStatus(), todoList.get(editIndex).getTaskDate()));
        }
        if (changeField == 3)
        {
            todoList.set(editIndex,new Task(todoList.get(editIndex).getTaskName(), todoList.get(editIndex).getProjectName(),inputField, todoList.get(editIndex).getTaskDate()));
        }
        if (changeField == 4)
        {

            try{

                date = formatter.parse(inputField);

            }catch(ParseException p){
                System.out.println(" Error occurred while editing the arraylist record");
            }

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            //* Get the current date. Consider only the Date portion for comparison

            Date today = new Date();
            Date todayWithZeroTime = formatter.parse(formatter.format(today));
            Date inputDateWithZeroTime = formatter.parse(formatter.format(date));

            if (inputDateWithZeroTime.compareTo(todayWithZeroTime)<0)
            {
                System.out.println("Date Entered should be greater than today");
            }
            else
            {
                todoList.set(editIndex,new Task(todoList.get(editIndex).getTaskName(), todoList.get(editIndex).getProjectName(), todoList.get(editIndex).getStatus(),date));
            }
        }
    }


    public void deleteRecord(int removeIndex) throws IOException
    {

        todoList.remove(removeIndex);

        System.out.println("Record deleted successfully ");
    }



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
