package com.github.oecu_kozaki_lab.repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

import com.github.oecu_kozaki_lab.entity.EmotionRate;
import com.github.oecu_kozaki_lab.entity.EmotionWordDict;

public final class EmotionWordDictRepository {
	public EmotionWordDict load(Path path) throws IOException {
		final HashMap<String, EmotionRate> dict = new HashMap<String, EmotionRate>();
		final Scanner scanner = new Scanner(path);
		while (scanner.hasNextLine()) {
			final String term = scanner.next();
			final double score = scanner.nextDouble();
			final String emotion = scanner.next();
			dict.put(term, new EmotionRate(emotion, score));
		}
		scanner.close();
		return new EmotionWordDict(dict);
	}
}
