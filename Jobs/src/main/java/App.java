import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class App {

	public static void main(String[] args) throws SchedulerException {
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("SON-TRIGGER", "GROUP")
				.withDescription("SIMPLE TRIGGER")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")
				/*.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(3)
						.repeatForever()*/
				 ).build();
		
		JobDetail sayHelloJob = JobBuilder.newJob(SayHello.class)
				.withIdentity("JOB SAY HELLO", "GROUP")
				.withDescription("JOB SAY HELLO EVERY 3 SECONDS")
				.build();
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(sayHelloJob, trigger);
	}

}
