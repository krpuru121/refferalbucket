package com.vassarlabs.common.init.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vassarlabs.common.init.component.api.IAppComponentInitializer;
import com.vassarlabs.common.init.err.AppInitializationException;
import com.vassarlabs.common.init.service.api.IApplicationInitService;


public abstract class ApplicationInitServiceImpl
	implements IApplicationInitService {

	protected static volatile AtomicBoolean INITIALIZED = new AtomicBoolean(false);
	
	
	@Override
	public boolean initialize()
		throws AppInitializationException {
		
		
       
		if (INITIALIZED.get() == true) {
			return true;
		}
		
		synchronized (ApplicationInitServiceImpl.class) {
			if (INITIALIZED.get() == true) {
				return true;
			}
			
			System.out.println("PGIII: Calling application initialization service - 2");
		
			List<IAppComponentInitializer> appComponentInitializerList = getAppComponentInitializerList();
			for (IAppComponentInitializer appComponentInitializer : appComponentInitializerList) {
				if (!appComponentInitializer.initialize()) {
					// TODO: Log error
					System.out.println("Error initializing AppComponentInitializer : " + appComponentInitializer.getName());
					throw new AppInitializationException("Error initializing AppComponentInitializer : " + appComponentInitializer.getName());
				}
			}

			INITIALIZED.set(true);
			return true;
		}
	}

	@Override
	public boolean reInitialize()
		throws AppInitializationException {

		synchronized (ApplicationInitServiceImpl.class) {
			INITIALIZED.set(false);
			
			List<IAppComponentInitializer> appComponentInitializerList = getAppComponentInitializerList();
			for (IAppComponentInitializer appComponentInitializer : appComponentInitializerList) {
				if (!appComponentInitializer.reInitialize()) {
					// TODO: Log error
					System.out.println("Error initializing AppComponentInitializer : " + appComponentInitializer.getName());
					throw new AppInitializationException("Error re-initializing AppComponentInitializer : " + appComponentInitializer.getName());
				}
			}
			
			INITIALIZED.set(true);
			return true;
		}
	}

	@Override
	public boolean shutdown()
		throws AppInitializationException {
		
		synchronized (ApplicationInitServiceImpl.class) {
			INITIALIZED.set(false);
			List<IAppComponentInitializer> appComponentInitializerList = getAppComponentInitializerList();
			for (IAppComponentInitializer appComponentInitializer : appComponentInitializerList) {
				if (!appComponentInitializer.reInitialize()) {
					// TODO: Log error
					System.out.println("Error shutting down AppComponentInitializer : " + appComponentInitializer.getName());
					throw new AppInitializationException("Error re-initializing AppComponentInitializer : " + appComponentInitializer.getName());
				}
			}
			return true;
		}
	}
	
	
	@Autowired
	@Qualifier("ds_connection_pool")
	protected IAppComponentInitializer dbAppComponentInitializer;
	
	
	protected List<IAppComponentInitializer> getAppComponentInitializerList() {
		List<IAppComponentInitializer> appComponentInitializerList = new ArrayList<IAppComponentInitializer>();
		appComponentInitializerList.add(dbAppComponentInitializer);
		
		return appComponentInitializerList;
	}
}
