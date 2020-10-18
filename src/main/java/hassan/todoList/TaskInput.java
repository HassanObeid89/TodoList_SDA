package hassan.todoList;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class TaskInput {

    private int option, taskNo;
    private boolean setExit = false;
    private String  taskName, projectName,status, taskDate;
    private int changeField;
    private UserInterface demo;
    private Date date;
    private FileHandler fileHandler;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

public void displayOption() throws ParseException
    {
        demo = new UserInterface();

        try{
            demo.inputReader();
        }catch(IOException a)
        {
            System.out.println(" Error Reading inputFile ");
        }


        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("  %55s", "                To do List          "));
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%45s","      1- Display List      "));
        System.out.println(String.format("%45s","      2- Add New Task      "));
        System.out.println(String.format("%45s","      3- Edit Task         "));
        System.out.println(String.format("%45s","      4- Delete Task       "));
        System.out.println(String.format("%45s","      5- Save & Exit       "));
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Enter correct option");
        option =  scanInput();

        while(!(setExit))
        {

            if(option == 1)
            {
                displayTask();
            }


            if(option == 2)
            {
                addTask();
            }


            if(option == 3)
            {
                editTask();
            }


            if (option == 4)
            {
                deleteTask();
            }


            if(option == 5)
            {
                saveExitTask();
            }
        }
    }

    private void addTask() throws ParseException
    {
        System.out.println("Enter the Task name   :  ");
        taskName = scanString();
        System.out.println("Enter the ProjectName :  ");
        projectName = scanString();
        System.out.println("Enter the Status      :  ");
        status = scanString();
        System.out.println("Enter the TaskDate(yyyy-MM-dd)      :  ");
        taskDate = scanString();
        try{
            date = formatter.parse(taskDate);

        }catch(ParseException p)
        {
            System.out.println(" Error occurred while trying to covert date ");
        }

        try{
            demo.addRecord(taskName,projectName,status,date);
            System.out.println("Enter the option");
            option = scanInput();
        }catch(IOException f)
        {
            System.out.println("Problem occurred when trying to add a record ");
        }
    }

    private void displayTask()
    {
        demo.displayInput();
        System.out.println("Enter the option");
        option = scanInput();
    }

    private int scanInput()
    {
        Scanner sc = new Scanner(System.in);
        return Integer.valueOf(sc.nextLine());

    }

    private void editTask() throws ParseException
    {
        System.out.println("Enter the Task No you want  to Edit");
        taskNo = scanInput();
        taskNo = taskNo - 1;
        System.out.println("Enter the field you want to Edit");
        System.out.println("1.Task Name  ");
        System.out.println("2.Project Name ");
        System.out.println("3.Status ");
        System.out.println("4.Date");
        option = scanInput();
        if (option ==1)
        {
            System.out.println("Edit the Task Name : " );
            taskName = scanString();
            changeField = 1;
            try
            {
                demo.editRecord(taskNo,taskName,changeField);
            }catch (IOException e) {

                System.out.println("Error occurred while trying to edit Task Name");
            }
            System.out.println("Enter the option");
            option = scanInput();

        }
        if (option == 2)
        {
            System.out.println("Edit the Project Name : ");
            projectName = scanString();
            changeField = 2;
            try{
                demo.editRecord(taskNo,projectName,changeField);

            }catch(IOException f)
            {
                System.out.println("Error occurred while trying to edit ProjectName");
            }
            System.out.println("Enter the option");
            option = scanInput();

        }
        if (option == 3)
        {
            System.out.println("Edit the status : ");
            status = scanString();
            changeField = 3;
            try
            {
                demo.editRecord(taskNo,status,changeField);
            }catch (IOException g) {
                System.out.println("Error occurred while trying to edit Status");
            }
            System.out.println("Enter the option");
            option = scanInput();
        }
        if (option == 4)
        {
            System.out.println("Edit the TaskDate(yyyy-MM-dd) : ");
            taskDate = scanString();
            changeField = 4;
            try
            {
                demo.editRecord(taskNo,taskDate,changeField);
            }catch (IOException g) {
                System.out.println("Error occurred while trying to edit Date");
            }
            System.out.println("Enter the option");
            option = scanInput();
        }
    }


    public String scanString()
    {
        Scanner sc = new Scanner(System.in);

        //sc.nextLine();
        //sc.next();
       // return sc.nextLine();
        return String.valueOf(sc.nextLine());
    }

    private void deleteTask() {
        System.out.println("Enter the Task No you want  to delete ");
        taskNo = scanInput();
        taskNo = taskNo - 1;

            try {
                demo.deleteRecord(taskNo);
            } catch (IOException g) {
                System.out.println(" Problem occurred when trying to delete a record");
            }

            System.out.println("Enter the option");
            option = scanInput();
        }


    private void saveExitTask()
    {
        try{
            demo.outputWriter();
        }catch(IOException e)
        {
            System.out.println("raised during when trying to write into the file");
        }
        setExit = true;
    }




}
