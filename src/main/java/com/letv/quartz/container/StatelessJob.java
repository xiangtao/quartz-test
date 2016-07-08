package com.letv.quartz.container;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@PersistJobDataAfterExecution
public class StatelessJob extends AbstactJob implements Job{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(StatefulJob.class);
	@Resource
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String taskInstanceId = context.getJobDetail().getJobDataMap().get("data_key").toString();
		LOGGER.debug("Scheduler container begin to schedule task or workflow: "+taskInstanceId);
		super.doTask(taskInstanceId, context.getNextFireTime());
	}
	
	/* ( Javadoc) 
	* <p>Title: call</p> 
	* <p>Description: </p> 
	* @param taskOrworkflowId 
	* @see com.jd.jedp.jobscheduler.AbstactJob#call(java.lang.String) 
	*/ 
	
	@Override
	public void call(String taskID) throws Exception {
		/*String node = new Configuration("common-config.xml").getDefault("scheduler_node", "");
		SchedulerCenterClient.repeatTask(node, Integer.valueOf(taskID));*/
		System.out.println("start =======" + taskID);
		Thread.sleep(1000 * 1);
		System.out.println("end ======="  + taskID);
	}
}
