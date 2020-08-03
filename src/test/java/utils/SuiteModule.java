package utils;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

public class SuiteModule extends AbstractModule {
	@Override
	protected void configure() {

	}

	@Singleton
	@Provides
	Map<String, Object> provideMap() {
		return new HashMap<String, Object>();
	}

	@Singleton
	@Provides
	public Suite provideSuite() {
		return Suite.getSuite("Template", "./suites/template.xml");
	}

}
