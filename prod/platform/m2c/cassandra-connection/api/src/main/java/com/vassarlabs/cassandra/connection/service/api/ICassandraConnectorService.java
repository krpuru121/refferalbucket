package com.vassarlabs.cassandra.connection.service.api;

import com.datastax.driver.core.Session;

public interface ICassandraConnectorService {

	public Session getSession();

}
