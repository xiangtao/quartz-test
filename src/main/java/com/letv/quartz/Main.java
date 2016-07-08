package com.letv.quartz;

import com.letv.quartz.container.TaskSchedulerContainer;

/**
 * Created by taox on 16-7-6.
 */
public class Main {


    public static void main(String[] args) throws Exception {

        TaskSchedulerContainer taskSchedulerContainer = TaskSchedulerContainer.getTaskSchedulerContainer();

        //taskSchedulerContainer.addTask(1,"0/20 * * * * ?",5,1);
        taskSchedulerContainer.deleteJob(1);
    }


}
