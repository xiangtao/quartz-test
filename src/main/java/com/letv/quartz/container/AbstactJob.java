package com.letv.quartz.container;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public abstract class AbstactJob {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstactJob.class);
	
	public void doTask(String taskID, Date nextFireTime) {
		try {
			if(!StringUtils.isBlank(taskID)) {
				call(taskID);
				//LOGGER.info(taskID+" next fire time is : "+nextFireTime);
			} else {
				LOGGER.error("taskInstanceId="+taskID);
				throw new IllegalArgumentException("Repeat task id is null.");
			}
		} catch (Exception e) {
			LOGGER.error(taskID+" start Repeat task failed, will try a again.", e);
			try {
				call(taskID);
			} catch (Exception e1) {
				LOGGER.error(taskID+" second time try to repeat task failed, will teminal task.", e1);
			}
		}
	}
	
	public abstract void call(String taskOrworkflowId) throws Exception;
}
