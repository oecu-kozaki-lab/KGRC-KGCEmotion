package com.github.oecu_kozaki_lab.entity;

import org.apache.jena.ext.xerces.util.URI;

public final class Scene {
	final public Character character;
	final public String sentence;
	final public URI uri;

	public Scene(URI uri, Character chara, String sentence) {
		this.character = chara;
		this.sentence = sentence;
		this.uri = uri;
	}
}
