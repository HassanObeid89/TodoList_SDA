import java.util.*;

public class Task {

    private String taskName;
    private String projectName;
    private String status;
    private Date taskDate;


    public Task(String taskName, String projectName, String status, Date taskDate)
        {
            this.taskName = taskName;
            this.projectName = projectName;
            this.status = status;
            this.taskDate = taskDate;
        }



    public String getTaskName()
    {
        return taskName;
    }


    public String getProjectName()
    {
        return projectName;
    }

    public String getStatus()
    {
        return status;
    }

    public Date getTaskDate()
    {
        return taskDate;
    }


    public void setTaskName(String setTaskName)
    {
        taskName = setTaskName;
    }

    public void setProjectName(String setProjectName)
    {
        projectName = setProjectName;
    }

    public void setTaskDate(Date setTaskDate)
    {
        taskDate = setTaskDate;
    }

    public void setStatus(String setStatus)
    {
        status = setStatus;
    }
}
