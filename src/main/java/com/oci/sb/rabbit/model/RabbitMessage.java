package com.oci.sb.rabbit.model;

public class RabbitMessage {
	private int id;
	private String content;

	public RabbitMessage(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "RabbitMessage [id=" + id + ", content=" + content + "]";
	}

}
