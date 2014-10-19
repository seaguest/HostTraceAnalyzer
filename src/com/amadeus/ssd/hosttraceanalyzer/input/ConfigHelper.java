package com.amadeus.ssd.hosttraceanalyzer.input;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigHelper {

	public static final String config = "config.properties";

	public static Setting readConfig() throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(config);
		// load a properties file
		prop.load(input);
		return new Setting(prop.getProperty("inputPath"),
				prop.getProperty("outputPath"));
	}

	public static void saveConfig(Setting setting) throws IOException {
		Properties prop = new Properties();
		OutputStream output = null;
		output = new FileOutputStream(config);
		// set the properties value
		prop.setProperty("inputPath", setting.getInputPath());
		prop.setProperty("outputPath", setting.getOutputPath());
		// save properties to project root folder
		prop.store(output, null);
	}

	public static void main(String[] args) throws IOException {
		Setting set = ConfigHelper.readConfig();
		System.out.println(set);
		
	}

}
