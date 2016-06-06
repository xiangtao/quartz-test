import com.letv.quartz.container.BuildCronTriggerException;
import com.letv.quartz.container.JobAndTriggerExisting;
import com.letv.quartz.container.TaskSchedulerContainer;
import org.quartz.SchedulerException;

/**
 * Created by taox on 2016/6/6.
 */
public class TestSchedulerContainer {

    public static void main(String[] args) throws BuildCronTriggerException, SchedulerException, JobAndTriggerExisting {

        test();

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
}
