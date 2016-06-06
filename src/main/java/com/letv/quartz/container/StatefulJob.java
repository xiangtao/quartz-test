/**
 * 
 */
package com.letv.quartz.container;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhongdegen
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulJob extends AbstactJob implements Job{
	protected static final Logger LOGGER = LoggerFactory.getLogger(StatefulJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String taskInstanceId = context.getJobDetail().getJobDataMap().get("data_key").toString();
		LOGGER.info("taskInstanceId="+taskInstanceId);
		LOGGER.info("Previous fire time: ["+context.getPreviousFireTime()+"], next Fire Time: ["+context.getNextFireTime()+"]");
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
//		this.schedulerTaskManager.startRepeatTask(Integer.getInteger(taskID));
		/*String node = new Configuration("common-config.xml").getDefault("scheduler_node", "");
		SchedulerCenterClient.repeatTask(node, Integer.valueOf(taskID));*/
		System.out.println("start =======" + taskID);
		Thread.sleep(1000 * 60);
		System.out.println("end ======="  + taskID);

	}
}
