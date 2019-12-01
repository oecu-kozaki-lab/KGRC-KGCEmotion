package com.github.oecu_kozaki_lab.service;

import java.util.ArrayList;

import com.github.oecu_kozaki_lab.entity.Character;
import com.github.oecu_kozaki_lab.entity.SceneEmotion;

public final class FormatService {
	public String format(Character character, ArrayList<SceneEmotion> sceneEmotions) {
		final StringBuilder builder = new StringBuilder();
		builder.append(character.name + "\t");
		builder.append(character.uri.toString() + "\n");
		for (SceneEmotion sceneEmotion : sceneEmotions) {
			builder.append("\t" + sceneEmotion.scene.uri + "\n");
			builder.append("\t" + sceneEmotion.scene.sentence + "\n");
			builder.append("\t\t"
					+ sceneEmotion.term + " "
					+ sceneEmotion.emotion + " "
					+ sceneEmotion.score + "\n");
		}
		return builder.toString();
	}
}
