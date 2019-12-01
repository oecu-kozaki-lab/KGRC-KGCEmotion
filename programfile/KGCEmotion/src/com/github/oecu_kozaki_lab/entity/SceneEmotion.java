package com.github.oecu_kozaki_lab.entity;

public final class SceneEmotion {
	final public Scene scene;
	final public String term;
	final public String emotion;
	final public double score;

	public SceneEmotion(Scene scene, String term, String emotion, double score) {
		this.scene = scene;
		this.term = term;
		this.emotion = emotion;
		this.score = score;
	}
}
