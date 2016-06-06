package com.letv.quartz.container;

import org.quartz.Job;
import org.quartz.JobDetail;

import static org.quartz.JobBuilder.newJob;

/**
 * 
 * @author zhongdegen
 *
 */
public class JobBuilder {
	protected JobDetail buildJob(Integer taskID, Integer selfDenpendence){
		Job job = null;
		if(1 == selfDenpendence) {
			job = new StatefulJob();
		} else {
			job = new StatelessJob();
		}
		return newJob(job.getClass()).withIdentity(taskID.toString(), taskID.toString()).build();
	}
}