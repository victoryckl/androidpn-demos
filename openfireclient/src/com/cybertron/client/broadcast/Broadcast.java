package com.cybertron.client.broadcast;

import org.jivesoftware.smack.packet.IQ;

public class Broadcast extends IQ {

	private String title;
	private String message;
	private String uri;

	// static final String ELEMENT = "query";
	static final String ELEMENT = "token";
	static final String NAMESPACE = "cybertron:iq:gettoken";

	public class BroadcastConstant {
		public static final String TITLE = "title";
		public static final String MESSAGE = "message";
		public static final String URI = "uri";
	}

	public Broadcast() {

	}

	public String getElementName() {
		return ELEMENT;
	}

	public String getNamespace() {
		return NAMESPACE;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {

		StringBuilder sb = new StringBuilder();
		if (null != this.title) {
			// sb.append("<title>").append(this.title).append("</title>");
			sb.append("<").append(BroadcastConstant.TITLE).append(">")
					.append(this.title).append("</")
					.append(BroadcastConstant.TITLE).append(">");
		}

		return sb.toString();
	}

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		if (null != this.title) {
			// sb.append("<message>").append(this.message).append("</message>");
			sb.append("<").append(BroadcastConstant.MESSAGE).append(">")
					.append(this.message).append("</")
					.append(BroadcastConstant.MESSAGE).append(">");
		}

		return sb.toString();
	}

	public String getUri() {
		StringBuilder sb = new StringBuilder();
		if (null != this.title) {
			// sb.append("<uri>").append(this.uri).append("</uri>");
			sb.append("<").append(BroadcastConstant.URI).append(">")
					.append(this.uri).append("</")
					.append(BroadcastConstant.URI).append(">");
		}

		return sb.toString();
	}

	public String getChildElementXML() {
		if (getMessage() == null) {
			throw new RuntimeException("--------> Broadcasts message is empty");
		}
		StringBuilder sb = new StringBuilder();
		sb.append(getTitle()).append(getMessage()).append(getUri()).append("<")
				.append(getElementName()).append(" xmlns=\"")
				.append(getNamespace()).append("\">").append("</")
				.append(getElementName()).append(">");
		return sb.toString();
	}

}
