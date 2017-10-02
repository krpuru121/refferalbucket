package com.vassarlabs.common.init.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vassarlabs.common.init.component.api.IAppComponentInitializer;
import com.vassarlabs.common.init.err.AppInitializationException;

//@Component
public class StormInitServiceImpl extends ApplicationInitServiceImpl{
	
    protected static volatile AtomicBoolean INITIALIZED_QUARTZ = new AtomicBoolean(false);
	

	
	@Override
	public boolean initialize()
		throws AppInitializationException {
		
		
 
		super.initialize();
		
		if (INITIALIZED_QUARTZ.get() == true) {
			return true;
		}
		
		synchronized (ApplicationInitServiceImpl.class) {
			if (INITIALIZED_QUARTZ.get() == true) {
				return true;
			}
			
			System.out.println("PGIII: Calling application initialization service - 3");
		
			List<IAppComponentInitializer> appComponentInitializerList = getQuartzComponentInitializerList();
			for (IAppComponentInitializer appComponentInitializer : appComponentInitializerList) {
				if (!appComponentInitializer.initialize()) {
					// TODO: Log error
					System.out.println("Error initializing AppComponentInitializer : " + appComponentInitializer.getName());
					throw new AppInitializationException("Error initializing AppComponentInitializer : " + appComponentInitializer.getName());
				}
			}

			INITIALIZED_QUARTZ.set(true);
			return true;
		}
	}

	
	
	@Autowired
	@Qualifier("quartz_scheduler_init")
	protected IAppComponentInitializer quartzSchedulerInitializer;
	

	protected List<IAppComponentInitializer> getQuartzComponentInitializerList() {
		List<IAppComponentInitializer> appComponentInitializerList = new ArrayList<IAppComponentInitializer>();
		appComponentInitializerList.add(quartzSchedulerInitializer);
		
		return appComponentInitializerList;
	}
}
