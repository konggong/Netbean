package example.Quartz.ex02;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.*;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

            // Trigger the job to run now, and then repeat every 10 seconds
            CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(cronBuilder).build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            
            //scheduler.shutdown();

        } catch (SchedulerException se) {
            System.out.println("Error : " + se.getMessage());
        }
    }
}
