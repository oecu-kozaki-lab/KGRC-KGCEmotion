package com.github.oecu_kozaki_lab;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.jena.ext.xerces.util.URI.MalformedURIException;
import org.apache.jena.riot.RiotNotFoundException;

import com.github.oecu_kozaki_lab.entity.Character;
import com.github.oecu_kozaki_lab.entity.EmotionWordDict;
import com.github.oecu_kozaki_lab.entity.KGStory;
import com.github.oecu_kozaki_lab.entity.Scene;
import com.github.oecu_kozaki_lab.entity.SceneEmotion;
import com.github.oecu_kozaki_lab.repository.EmotionWordDictRepository;
import com.github.oecu_kozaki_lab.repository.KGStoryRepository;
import com.github.oecu_kozaki_lab.service.FormatService;
import com.github.oecu_kozaki_lab.service.SceneEmotionService;

public class App {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("ttlファイルのパスを指定してください");
			return;
		}

		final KGStoryRepository kgStoryRepo = new KGStoryRepository();
		final EmotionWordDictRepository emoWordDictRepo = new EmotionWordDictRepository();
		final FormatService formatService = new FormatService();

		final Path kgStoryPath = Paths.get(args[0]);
		final Path emoWordDictPath = Paths.get("./NRC-AILexicon.txt");
		try {
			KGStory story = kgStoryRepo.load(kgStoryPath.getFileName().toString(), kgStoryPath);
			EmotionWordDict dict = emoWordDictRepo.load(emoWordDictPath);
			SceneEmotionService sceneEmotionService = new SceneEmotionService(dict);

			for (Character character : story.getCharacterList()) {
				ArrayList<Scene> scenes = story.getSceneByCharacter(character);
				ArrayList<SceneEmotion> sceneEmotions = sceneEmotionService.extractEmotion(scenes);
				System.out.println(formatService.format(character, sceneEmotions));
			}
		} catch (MalformedURIException err) {
			System.err.println("ナレッジグラフが不正です");
			System.err.println(err.getMessage());
		} catch (IOException err) {
			System.err.println("NRC-AILexiconが見つかりません");
			System.err.println(err.getMessage());
		} catch (RiotNotFoundException err) {
			System.err.println("ttlファイルが見つかりません");
			System.err.println(err.getMessage());
		}
	}
}
