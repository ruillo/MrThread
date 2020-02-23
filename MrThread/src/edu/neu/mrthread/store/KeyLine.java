package edu.neu.mrthread.store;

public class KeyLine{

	private String key;
	private String line;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getKey() {
		return key;
	}

	public KeyLine(String key) {
		this.key = key;
	}

	public KeyLine(String key, String line) {
		this(key);
		this.line = line;
	}
}
