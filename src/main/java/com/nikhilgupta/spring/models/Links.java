package com.nikhilgupta.spring.models;

import java.io.Serializable;

public class Links implements Serializable{

	private Self self;

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}
	
	
}
