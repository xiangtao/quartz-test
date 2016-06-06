package com.letv.quartz.container;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhongdegen
 *
 */
public final class TaskSchedulerContainer {
	protected static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedulerContainer.class);
	
	private Scheduler scheduler; //scheduler
	
	private JobBuilder jobBuilder;
	private TriggerBuilder triggerBuilder;
	private static TaskSchedulerContainer taskSchedulerContainer;
  
	private TaskSchedulerContainer() {
		this.jobBuilder = new JobBuilder();
		this.triggerBuilder = new TriggerBuilder();
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			LOGGER.info("Start scheduler container....");
		} catch (SchedulerException e) {
			LOGGER.error("Start scheduler container failed, ", e);
		}
	}
	
	public static TaskSchedulerContainer getTaskSchedulerContainer() {
		if (null == taskSchedulerContainer) {
			synchronized (TaskSchedulerContainer.class) {
				if (null == taskSchedulerContainer) {
					taskSchedulerContainer = new TaskSchedulerContainer();
				}
			}
		}
		return taskSchedulerContainer;
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	
	public void addTask(Integer taskID, String cron, Integer priotity, Integer selfDenpendence) throws BuildCronTriggerException, JobAndTriggerExisting, SchedulerException {
		
		JobDetail jobDetail = this.jobBuilder.buildJob(taskID, selfDenpendence);
		
		jobDetail.getJobDataMap().put("data_key", taskID);
		
		Trigger trigger = this.triggerBuilder.buildTrigger(cron, priotity);
		
		addToSchedulerContianer(jobDetail, trigger);
	}

	/**
	 * @param jobDetail
	 * @param trigger
	 * @throws SchedulerException
	 * @throws JobAndTriggerExisting
	 * @throws JobAndTriggerExisting
	 */
	public void addToSchedulerContianer(JobDetail jobDetail, Trigger trigger) throws SchedulerException, JobAndTriggerExisting {
		boolean isJobExisting = isJobExisting(jobDetail.getKey());
		boolean isTriggerExisting = this.scheduler.checkExists(trigger.getKey());
		if(isJobExisting && isTriggerExisting) {
			LOGGER.warn("Job and trigger has already in scheduler container.");
			throw new JobAndTriggerExisting("JobName="+jobDetail.getKey().getName()+", job groupName="+jobDetail.getKey().getGroup()); 
		}
		
		if(isTriggerExisting) {
			LOGGER.warn("Trigger has already in scheduler container, please reselect trigger ID");
			this.scheduler.resumeTrigger(trigger.getTriggerBuilder().forJob(jobDetail.getKey().getName(), jobDetail.getKey().getGroup()).build().getKey());
			return;
		}

		if(isJobExisting) {
			trigger = trigger.getTriggerBuilder().forJob(jobDetail.getKey().getName(), jobDetail.getKey().getGroup()).build();
			this.scheduler.scheduleJob(trigger);
			return;
		}
		
		
		this.scheduler.scheduleJob(jobDetail, trigger);
	}
	
	/**
	 * @throws SchedulerException
	 * @throws JobAndTriggerExisting
	 * @throws BuildCronTriggerException 
	 */
	public void updateTask(Integer taskID,String cron, Integer priotity, Integer selfDenpendence) throws SchedulerException, BuildCronTriggerException, JobAndTriggerExisting {
		
		if(isJobExisting(getJobKey(taskID))) {
			
			try {
				deleteJob(taskID);
			} catch (SchedulerException e) {
				LOGGER.warn("Not found task in scheduler container: ["+taskID+"]");
			}
		}
		addTask(taskID, cron, priotity, selfDenpendence);
	}

	/**
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deleteJob(Integer taskID) throws SchedulerException {
		JobKey jobKey = getJobKey(taskID);
		if(!isJobExisting(jobKey)) {
			LOGGER.warn("Job not found in container, taskID="+taskID);
		}
		return this.scheduler.deleteJob(jobKey);
	}
	
	public boolean isJobExisting(JobKey jobKey) throws SchedulerException {
		return this.scheduler.checkExists(jobKey);
	}
	
	public JobKey getJobKey(Integer taskID){
		return new JobKey(taskID.toString(), taskID.toString());
	}

	public void printJob() throws SchedulerException {
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher
					.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				String jobGroup = jobKey.getGroup();
				LOGGER.info("[jobName] : " + jobName + " [groupName] : "
						+ jobGroup + ".");
			}
		}
	}
}   
