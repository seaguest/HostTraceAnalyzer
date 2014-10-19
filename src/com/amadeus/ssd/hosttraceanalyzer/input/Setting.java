package com.amadeus.ssd.hosttraceanalyzer.input;

public class Setting {

	String inputPath;
	
	String outputPath;

	public Setting(String inputPath, String outputPath) {
		super();
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}	
	
	
	public Setting(){
		super();

	}


	public String getInputPath() {
		return inputPath;
	}


	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}


	public String getOutputPath() {
		return outputPath;
	}


	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}


	@Override
	public String toString() {
		return "Setting [inputPath=" + inputPath + ", outputPath=" + outputPath
				+ "]";
	}
	
	
	
	
	
}
