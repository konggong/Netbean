package example.Quartz.ex01;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerService extends Thread {
    
    static SchedulerFactory sf = new StdSchedulerFactory();
    static Scheduler scheduler;

    @Override
    public void start() {
        this.run();
    }
    
    @Override
    public void run() {
        try {
            scheduler = sf.getScheduler();
            CronScheduleBuilder cronScheduleBuilder;
            CronTrigger trigger;
            String corn = "0/1 * * * * ?";
            if (!corn.isEmpty()) {
                cronScheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
                trigger = newTrigger().withIdentity("cronTrigger1", "QuartzTriggerGroup").withSchedule(cronScheduleBuilder).build();

                JobDetail job = newJob(InitiatQuartzTrigger.class).withIdentity("job1", "QuartzTriggerGroup").build();
                // Tell quartz to schedule the job using our trigger
                scheduler.scheduleJob(job, trigger);
            }
            scheduler.start();
            System.out.println("Start...");
            
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            System.out.println("Error : " + e.getMessage());
        }
    }
}
