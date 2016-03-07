package example.Quartz.ex03;

import java.text.ParseException;
import java.util.Date;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyQuartzScheduler {

    SchedulerFactory sf;
    Scheduler sched;

    private void createCronTrigger() throws SchedulerException, ParseException {
        // job will run every 7 seconds
        JobDetail job = JobBuilder.newJob(HelloCronJob.class).withIdentity("cronjob", "cronjobgroup1").build();
        CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule("0/7 * * * * ?");

        // CronTrigger the job to run on the every 20 seconds
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("crontrigger", "crontriggergroup1").withSchedule(cronBuilder).build();

        // Tell quartz to schedule the job using our crontrigger
        Date ft = sched.scheduleJob(job, cronTrigger);
        System.out.println(job.getKey() + " has been scheduled to run at: "+ ft + " and repeat based on expression: " + cronTrigger.getCronExpression());
    }

    private void createTrigger() throws SchedulerException {
        // computer a time that is on the next round minute
        Date runTime = DateBuilder.evenMinuteDate(new Date());

        System.out.println("------- Scheduling Job  ------------------- ");

        // define the job and tie it to our HelloTriggerJob class
        JobDetail job = JobBuilder.newJob(HelloTriggerJob.class).withIdentity("triggerjob", "triggerjobgroup1").build();

        // Trigger the job to run on the next round minute
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger","triggergroup1").startAt(runTime).build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " will run at: " + runTime);

    }

    public void init() throws SchedulerException, ParseException {
        System.out.println("------- Initializing ----------------------");
        sf = new StdSchedulerFactory();
        sched = sf.getScheduler();
        System.out.println("------- Initialization Complete -----------");

        createTrigger();

        //createCronTrigger();

        // Start up the scheduler (nothing can actually run until the
        // scheduler has been started)
        sched.start();

        System.out.println("------- Started Scheduler -----------------");

        // wait long enough so that the scheduler as an opportunity to
        // run the job!
        System.out.println("------- Waiting 125 seconds... -------------");
        try {
            // wait 125 seconds to show job
            Thread.sleep(125L * 1000L);
            // executing...
        } catch (Exception ex) {
            System.out.println("Error Exception : " + ex.getMessage());
        }

        // shut down the scheduler
        System.out.println("------- Shutting Down ---------------------");
        sched.shutdown(true);
        System.out.println("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) {
        try {
            new MyQuartzScheduler().init();
        } catch (SchedulerException ex) {
            System.out.println("Error SchedulerException : " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error ParseException : " + ex.getMessage());
        }
    }
}