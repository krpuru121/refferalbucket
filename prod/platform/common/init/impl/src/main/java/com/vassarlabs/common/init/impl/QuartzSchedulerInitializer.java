/*package com.vassarlabs.common.init.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.init.component.api.IAppComponentInitializer;
import com.vassarlabs.config.err.InvalidPropertyRequestException;
import com.vassarlabs.config.err.PropertyNotFoundException;
import com.vassarlabs.config.service.api.IConfigPropertyRepoService;
import com.vassarlabs.monitorsystem.scheduler.api.IMonitorScheduler;

@Component
@Qualifier("quartz_scheduler_init")
public class QuartzSchedulerInitializer implements IAppComponentInitializer {

	private Scheduler scheduler;
	
	@Autowired
	protected IConfigPropertyRepoService configPropertyRepoService;
	@Autowired
	protected IMonitorScheduler monitorScheduler;
	
	@Override
	public boolean initialize() {
		String hostName;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
			if(hostName == null || !hostName.equals(configPropertyRepoService.getProperty("CACHE_CONFIG", "default_cache", "QUARTZ_MACHINE_NAME"))) {
				System.out.println("QSI:not intializing for host - " + hostName);
				return false;
			}
		} catch (UnknownHostException | InvalidPropertyRequestException | PropertyNotFoundException e1) {
			System.out.println("QSI:unable to get hostName");
			e1.printStackTrace();
			return false;
		}
		
		try {
			System.out.println("QSI:initializing on host " + hostName);
			SchedulerFactory factory = new StdSchedulerFactory("quartz/quartz.properties");
			scheduler = factory.getScheduler();
			scheduler.start();
			System.out.println(("QSI:initialized on host " + hostName));
			
			System.out.println("Starting monitoring scheduler job");
			monitorScheduler.initialize();
		} catch (SchedulerException e) {
			System.out.println("QSI:initialize:" + e);
			e.printStackTrace();
			return false;
		} catch (DSPException e) {
			System.out.println("Error while intializing monitoring scheduler:" + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		return "QUARTZ_SCHEDULER_INIT";
	}

	@Override
	public boolean reInitialize() {
		if (!shutdown()) {
			return false;
		}
		return initialize();
	}

	@Override
	public boolean shutdown() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			System.out.println("QSI:shutdown:" + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
*/