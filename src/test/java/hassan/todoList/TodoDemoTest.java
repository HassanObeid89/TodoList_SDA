package hassan.todoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TodoDemoTest
{
    ArrayList<Task> taskList = new ArrayList<>();
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date, date1, date2;



    /**
     *
     * Load the ArrayList with Date before each method execution.
     */
    @BeforeEach
    void setup() throws ParseException {
        date = formatter.parse("2020-10-08");
        date1 = formatter.parse("2020-10-05");
        date2 = formatter.parse("2020-10-01");
        Task task = new Task("Test", "Test Project", "Open", date);
        Task task1 = new Task("First", "First Project", "Open", date1);
        Task task2 = new Task("Second", "Second Project", "Closed", date2);

		taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
    }


    /**
     *
     * Add element to Arraylist and Test whether added element is present in the list.
     */
    @Test
    void AddElementSuccess()
    {

        assertEquals("Test", taskList.get(0).getTaskName());
        assertEquals("Test Project", taskList.get(0).getProjectName());
        assertEquals("Open", taskList.get(0).getStatus());
        assertEquals(date, taskList.get(0).getTaskDate());

    }

    /**
     *
     * Add element to Arraylist and Test for the Failure case.
     */
    @Test
    void AddElementFailure()
    {

        Task task5 = new Task("test", "test Project", "Open", date);
        taskList.add(task5);
        assertEquals("Test", taskList.get(0).getTaskName());
        assertEquals("Test Project", taskList.get(0).getProjectName());
        assertEquals("Open", taskList.get(0).getStatus());
        assertEquals(date, taskList.get(0).getTaskDate());
    }

        /**
         * Edit elements in ArrayList and check whether it gets reflected.
         */


    @Test
    void EditElementFailure() throws ParseException
    {
        date = formatter.parse("2020-10-10");
        taskList.get(0).setTaskName("Test Edit");
        taskList.get(0).setProjectName("Edit Project");
        taskList.get(0).setStatus("Closed");
        taskList.get(0).setTaskDate(date);

        taskList.set(0,new Task(taskList.get(0).getTaskName(), taskList.get(0).getProjectName(), taskList.get(0).getStatus(), taskList.get(0).getTaskDate()));
        assertEquals("Test Edit", taskList.get(0).getTaskName());
        assertEquals("Edit Project", taskList.get(0).getProjectName());
        assertEquals("Closed", taskList.get(0).getStatus());
        assertEquals(date, taskList.get(0).getTaskDate());
    }
        /**
         * Check for failure condition of Edit function.
         */

    @Test
    void EditFailure() throws ParseException
    {
        date = formatter.parse("2018-10-10");
        taskList.get(0).setTaskName("test edit");
        taskList.get(0).setProjectName("edit project");
        taskList.get(0).setStatus("closed");
        taskList.get(0).setTaskDate(date);

        taskList.set(0,new Task(taskList.get(0).getTaskName(), taskList.get(0).getProjectName(), taskList.get(0).getStatus(), taskList.get(0).getTaskDate()));
        assertEquals("test edit", taskList.get(0).getTaskName());
        assertEquals("edit project", taskList.get(0).getProjectName());
        assertEquals("closed", taskList.get(0).getStatus());
        assertEquals(date, taskList.get(0).getTaskDate());
    }

        /**
         * Check whether element gets successfully deleted from Arraylist
         */

    @Test
    void DeleteSuccess()
    {

        taskList.remove(2);
        assertEquals(2, taskList.size());
    }


        /**
         * Check for failure condition of Delete function.
         */
    @Test
    void DeleteFailure()
    {
        taskList.remove(1);
        assertEquals(2, taskList.size());
    }

        /**
         * Check whether the Arraylist is sorted.
         * Copy the original list into another list  and sorted the list.
         * Compare the sorted list with original list.
         */


    @Test
    void SortSuccess()
    {
        ArrayList<Task> sorted = new ArrayList<>(taskList);
        sorted.sort(Comparator.comparing(Task::getTaskDate));
        taskList.sort(Comparator.comparing(Task::getTaskDate));
        assertEquals(sorted.get(0).getTaskDate(), taskList.get(0).getTaskDate());
    }




}