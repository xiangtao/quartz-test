import com.letv.quartz.container.BuildCronTriggerException;
import com.letv.quartz.container.JobAndTriggerExisting;
import com.letv.quartz.container.TaskSchedulerContainer;
import org.quartz.SchedulerException;

/**
 * Created by taox on 2016/6/6.
 */
public class TestSchedulerContainer {

    public static void main(String[] args) throws BuildCronTriggerException, SchedulerException, JobAndTriggerExisting {

//        test();
        testStartLoadTaskIntoContainer();
    }
    public static void test() throws BuildCronTriggerException, SchedulerException, JobAndTriggerExisting {
        TaskSchedulerContainer container = TaskSchedulerContainer.getTaskSchedulerContainer();
        int count = 50;
        for(int i=0;i<count;i++){
            container.addTask(i,"0/2 * * * * ?",1,0);
        }

        Thread thread =  new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while (true){
                    System.out.println("^^^^^^^^^^^^" + i++);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public static void testStartLoadTaskIntoContainer() throws SchedulerException {
        TaskSchedulerContainer taskSchedulerContainer = TaskSchedulerContainer.getTaskSchedulerContainer();
        int successCycleAndRegularNumber = 0;
        int expiredOrCronErrorNumber = 0;
        int jobAndTriggerExistingNumber = 0;

        try {
            int taskId = 1;
            taskSchedulerContainer.updateTask(1, "0/2 * * * * ?", 1, 0);
            taskSchedulerContainer.updateTask(2, "0/4 * * * * ?", 1, 0);
            successCycleAndRegularNumber++;
        } catch (BuildCronTriggerException e) {
            expiredOrCronErrorNumber++;
        } catch (JobAndTriggerExisting e) {
            jobAndTriggerExistingNumber++;
        } catch (SchedulerException e) {
            expiredOrCronErrorNumber++;
        }

        // 打印调度器内的job
        taskSchedulerContainer.printJob();
        // 统计信息
        System.out.println("Successful number of cycle and regular is "
                + successCycleAndRegularNumber);
        System.out.println("Number of expired or cron error is "
                + expiredOrCronErrorNumber);
        System.out.println("Number of job and trigger existing is "
                + jobAndTriggerExistingNumber);

        Thread thread =  new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while (true){
                    System.out.println("^^^^^^^^^^^^" + i++);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }
}
