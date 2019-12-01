package com.github.oecu_kozaki_lab.entity;

import org.apache.jena.ext.xerces.util.URI;

public final class Character {
	final public String name;
	final public URI uri;

	public Character(String name, URI uri) {
		this.name = name;
		this.uri = uri;
	}
}
