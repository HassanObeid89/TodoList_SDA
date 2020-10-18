
package hassan.todoList;


import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class UserInterface extends FileHandler{

    private int statusOpen = 0, statusClosed = 0;

    /**
     * Display the list of tasks with two sorted options
     * Sorted by task date
     * Sorted by task name 
     */
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
                System.out.println("Number of Tasks open : " + statusOpen +"\nNumber of Tasks Closed : " + statusClosed);
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
     * Function allow the user to edit the details of existing tasks.
     * @param editIndex parameter that holds the index number of the task to be edit.
     * @param inputField parameter that holds the new user input.
     * @param changeField Parameter holds the user choice.
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

    /**
     * Function allows the user to delete task depending on task number.
     * @param removeIndex parameter that holds the user input which specify the
     *                    index number to be deleted.
     * @throws IOException An exception thrown if for incorrect input.
     */
    public void deleteRecord(int removeIndex) throws IOException
    {

        todoList.remove(removeIndex);

        System.out.println("Record deleted successfully ");
    }





}
