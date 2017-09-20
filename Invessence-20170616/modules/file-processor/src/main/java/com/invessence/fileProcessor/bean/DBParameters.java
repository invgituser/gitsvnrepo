package com.invessence.fileProcessor.bean;

public class DBParameters
{

	private String name;
	private Object value;
	private String format;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "DBParameters{" +
			"name='" + name + '\'' +
			", value=" + value +
			", format='" + format + '\'' +
			", description='" + description + '\'' +
			'}';
	}
}
