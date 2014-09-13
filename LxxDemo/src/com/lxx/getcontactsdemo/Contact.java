package com.lxx.getcontactsdemo;

import android.graphics.Bitmap;

public class Contact {

	private String name;
	private String number;
	private String letter;
	private Bitmap head;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public Bitmap getHead() {
		return head;
	}
	public void setHead(Bitmap head) {
		this.head = head;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
