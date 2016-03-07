package example.Quartz.ex01;

public class QuartzStart {

    public static void main(String[] args) {
        SchedulerService schedulerService = new SchedulerService();
        schedulerService.start();
    }
}
