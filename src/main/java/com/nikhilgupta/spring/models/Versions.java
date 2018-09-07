package com.nikhilgupta.spring.models;

import java.security.KeyStore.PrivateKeyEntry;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.java.repository.annotation.Id;

public class Versions {

	@JsonProperty("_id")
	private String id;
	
	@JsonProperty("_ver")
	private String ver;
	
	@JsonProperty("_links")
	private Links links;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}
	
	
}
