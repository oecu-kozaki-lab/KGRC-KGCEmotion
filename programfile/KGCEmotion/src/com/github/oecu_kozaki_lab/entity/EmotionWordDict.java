package com.github.oecu_kozaki_lab.entity;

import java.util.HashMap;
import java.util.Optional;

public final class EmotionWordDict {
	final private HashMap<String, EmotionRate> dict;

	public EmotionWordDict(HashMap<String, EmotionRate> dict) {
		this.dict = dict;
	}

	public Optional<EmotionRate> getEmotionRate(String word) {
		EmotionRate emoRate = this.dict.getOrDefault(word, null);
		if (emoRate != null) {
			return Optional.of(emoRate);
		} else {
			return Optional.empty();
		}
	}
}
