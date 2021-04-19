package com.ucbos.utils.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.ucbos.utils.common.schema.Configurator;

public abstract class ConfigManager implements Configurator {

	Properties properties;
	private String templateDataFile;
	private int numberOfFilesToGenerate;

	public void loadProperties(Class<?> configClass, String path) {
		try {
			InputStream in = configClass.getResourceAsStream(path);
			properties = new Properties();
			properties.load(in);
			for (Field field : configClass.getDeclaredFields())
				if (Modifier.isStatic(field.getModifiers()))
					field.set(null, getValue(properties, field.getName(), field.getType()));
		} catch (Exception e) {
			throw new RuntimeException("Error loading configuration: " + e, e);
		}
	}

	private static Object getValue(Properties props, String name, Class<?> type) {
		String value = props.getProperty(name);

		if (value == null)
			throw new IllegalArgumentException("Missing configuration value: " + name);
		if (type == String.class)
			return value;
		if (type == boolean.class)
			return Boolean.parseBoolean(value);
		if (type == int.class)
			return Integer.parseInt(value);
		if (type == float.class)
			return Float.parseFloat(value);
		if (type == long.class)
			return Long.parseLong(value);
		throw new IllegalArgumentException("Unknown configuration value type: " + type.getName());
	}

	public int getNumberOfFilesToGenerate() {
		return numberOfFilesToGenerate;
	}

	public void setNumberOfFilesToGenerate(int numberOfFilesToGenerate) {
		this.numberOfFilesToGenerate = numberOfFilesToGenerate;
	}

	public String getTemplateDataFile() {
		return templateDataFile;
	}

	public void setTemplateDataFile(String templateDataFile) {
		this.templateDataFile = templateDataFile;
	}

	public Properties getProperties() {
		return properties;
	}

	public ConfigManager() throws IOException {
		loadProperties();
	}

	public ConfigManager(String path) throws IOException {
		loadProperties(path);
	}

	public void loadProperties() throws IOException {
		System.out.println("loadProperties with class");
		loadProperties("/config.properties");
		loadProperties(this.getClass(), "/config.properties");
	}

	public void loadProperties(String path) throws IOException {
		InputStream in = this.getClass().getResourceAsStream(path);
		properties = new Properties();
		properties.load(in);
		System.out.println(properties.stringPropertyNames());
		loadProperties(properties);
		loadProperties(this.getClass(), path);
	}

	public void loadProperties(Properties properties) {
		System.out.println("loadProperties ...ConfigManager");
		setTemplateDataFile(properties.getProperty("TEMPLATE_DATAFILE"));
		String tempNOFile = properties.getProperty("NUMBER_OF_FILES_TO_GENERATE");
		if (!StringUtils.isBlank(tempNOFile)) {
			setNumberOfFilesToGenerate(Integer.parseInt(tempNOFile));
		}

	}

}
