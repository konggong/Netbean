package example.Quartz.ex02;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

    public HelloJob() {}

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
      System.out.println("Hello!  HelloJob is executing.");
    }
  }
