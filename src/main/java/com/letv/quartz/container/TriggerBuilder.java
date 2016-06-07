/**
 * 
 */
package com.letv.quartz.container;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @author zhongdegen
 * 
 */
public class TriggerBuilder {
	protected static final Logger LOGGER = LoggerFactory.getLogger(TriggerBuilder.class);
	
	public Trigger buildTrigger(String cron, int priotity) throws BuildCronTriggerException {
		Trigger trigger = null;
		try {
			LOGGER.info("Cron: "+cron);
			trigger = newTrigger()
					.withIdentity(
							UUID.randomUUID().toString(),
							UUID.randomUUID().toString())
							.withSchedule(cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed())
							.withPriority(priotity).build();
		} catch (Exception e) {
			LOGGER.error("Cron="+cron+", priotity", e);
			throw new BuildCronTriggerException("Invalid cron: ["+cron+"]");
		}
		return trigger;
	}
}
