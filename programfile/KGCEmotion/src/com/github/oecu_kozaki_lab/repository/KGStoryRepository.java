package com.github.oecu_kozaki_lab.repository;

import java.nio.file.Path;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import com.github.oecu_kozaki_lab.entity.KGStory;

public final class KGStoryRepository {
	public KGStory load(String name, Path path) {
		Model model = ModelFactory.createDefaultModel();
		model.read(path.toAbsolutePath().toString(), "TURTLE");
		return new KGStory(name, model);
	}
}
