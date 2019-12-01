package com.github.oecu_kozaki_lab.entity;

public final class EmotionRate {
	final public double score;
	final public String emotion;

	public EmotionRate(String emotion, double score) {
		//メンバ変数に各値を格納
		this.score = score;
		this.emotion = emotion;
	}
}
