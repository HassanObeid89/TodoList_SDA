package hassan.todoList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TaskTest {

    Date date;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    @Test
    void checkIfGettingTheCorrectProjectName()
    {
        Task taskTest = new Task("Test","Test Project","Open",date);
        assertEquals("Test Project",taskTest.getProjectName());
    }

    @Test
    void checkIfGettingTheCorrectTaskStatus()
    {
        Task taskTest = new Task("Test","Test Project","Open",date);
        assertEquals("Open",taskTest.getStatus());
    }

    @Test
    void getTaskDateSuccess()
    {
        Task taskTest = new Task("Test","Test Project","Open",date);
        assertEquals(date,taskTest.getTaskDate());
    }

    @Test
    void setGetTaskNameSuccess() throws ParseException
    {
        Task taskTest = new Task("Test","Test Project","Open",date);
        taskTest.setTaskName("test");
        assertEquals("test",taskTest.getTaskName());

        taskTest.setProjectName("test project");
        assertEquals("test project",taskTest.getProjectName());

        taskTest.setStatus("open");
        assertEquals("open",taskTest.getStatus());

        date = formatter.parse("2018-10-01");
        taskTest.setTaskDate(date);
        assertEquals(date,taskTest.getTaskDate());
    }




}