package com.sdi.infrastructure;

import impl.sdi.business.SimpleServicesFactory;
import impl.sdi.persistence.SimplePersistenceFactory;

import com.sdi.business.ServicesFactory;
import com.sdi.persistence.PersistenceFactory;


public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();

}
