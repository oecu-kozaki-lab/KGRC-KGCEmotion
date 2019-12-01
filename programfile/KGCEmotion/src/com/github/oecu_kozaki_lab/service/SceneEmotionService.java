package com.github.oecu_kozaki_lab.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.oecu_kozaki_lab.entity.EmotionRate;
import com.github.oecu_kozaki_lab.entity.EmotionWordDict;
import com.github.oecu_kozaki_lab.entity.Scene;
import com.github.oecu_kozaki_lab.entity.SceneEmotion;

public final class SceneEmotionService {
	final EmotionWordDict dict;

	public SceneEmotionService(EmotionWordDict dict) {
		this.dict = dict;
	}

	public ArrayList<SceneEmotion> extractEmotion(ArrayList<Scene> scenes) {
		final List<SceneEmotion> sceneEmotionList = scenes.stream()
				.flatMap(c -> this.extract(c).stream())
				.collect(Collectors.toList());
		return new ArrayList<SceneEmotion>(sceneEmotionList);
	}

	private ArrayList<SceneEmotion> extract(Scene scene) {
		final ArrayList<SceneEmotion> list = new ArrayList<SceneEmotion>();
		final ArrayList<String> words = this.splitSentence(scene.sentence);
		for (String word : words) {
			final Optional<EmotionRate> maybeEmoRate = this.dict.getEmotionRate(word);
			maybeEmoRate.ifPresent(emoRate -> {
				list.add(new SceneEmotion(scene, word, emoRate.emotion, emoRate.score));
			});
		}
		return list;
	}

	private ArrayList<String> splitSentence(String sentence) {
		final String[] words = sentence.split(" ");
		final List<String> wordList = Arrays.asList(words);
		return new ArrayList<String>(wordList);
	}
}
