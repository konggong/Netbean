package example.Quartz.ex01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class InitiatQuartzTrigger implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        System.out.println("this will get printed after every 5 seconds..." + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));

    }

}
