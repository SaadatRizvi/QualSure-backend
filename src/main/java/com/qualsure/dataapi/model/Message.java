package com.qualsure.dataapi.model;

public class Message {
	private String owner;
	private String timeStamp;
	private String publicAddress;
	
	public Message(String owner) {
		super();
		this.owner = owner;
	}
	public Message(String owner, String timeStamp) {
		super();
		this.owner = owner;
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Message [owner=" + owner + ", timeStamp=" + timeStamp + ", publicAddress=" + publicAddress + "]";
	}
	public String getPublicAddress() {
		return publicAddress;
	}
	public void setPublicAddress(String publicAddress) {
		this.publicAddress = publicAddress;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
