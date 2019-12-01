package com.github.oecu_kozaki_lab.entity;

import java.util.ArrayList;

import org.apache.jena.ext.xerces.util.URI;
import org.apache.jena.ext.xerces.util.URI.MalformedURIException;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public final class KGStory {
	final private Model storyModel;
	final public String name;

	public KGStory(String name, Model story) {
		this.name = name;
		this.storyModel = story;
	}

	public ArrayList<Character> getCharacterList() throws MalformedURIException {
		ArrayList<Character> list = new ArrayList<Character>();

		String queryStr = "select distinct ?person ?name"
				+ "{?person a <http://kgc.knowledge-graph.jp/ontology/kgc.owl#Person>;"
				+ "<http://www.w3.org/2000/01/rdf-schema#label> ?name."
				+ "filter(lang(?name) = \"en\") }";
		final Query query = QueryFactory.create(queryStr);
		final QueryExecution qexec = QueryExecutionFactory.create(query, this.storyModel);
		final ResultSet rs = qexec.execSelect();

		while (rs.hasNext()) {
			final QuerySolution qs = rs.next();
			final Resource person = qs.getResource("person");
			final Literal name = qs.getLiteral("name");
			if (person != null && name != null) {
				final String strUri = person.getURI();
				final URI uri = new URI(strUri);
				final String strName = name.toString();
				list.add(new Character(strName, uri));
			}
		}
		return list;
	}

	public ArrayList<Scene> getSceneByCharacter(Character character)
			throws MalformedURIException {
		ArrayList<Scene> list = new ArrayList<Scene>();
		String queryStr = "select distinct ?scene ?sentence"
				+ "{?scene <http://kgc.knowledge-graph.jp/ontology/kgc.owl#subject>"
				+ " <" + character.uri.toString() + "> ;"
				+ "<http://kgc.knowledge-graph.jp/ontology/kgc.owl#source> ?sentence;"
				+ "filter(lang(?sentence) = \"en\")}";

		final Query query = QueryFactory.create(queryStr);
		final QueryExecution qexec = QueryExecutionFactory.create(query, this.storyModel);
		final ResultSet rs = qexec.execSelect();

		while (rs.hasNext()) {
			QuerySolution qs = rs.next();
			final String sentence = qs.getLiteral("sentence").toString();
			final String sceneStrUri = qs.getResource("scene").getURI();
			list.add(new Scene(new URI(sceneStrUri), character, sentence));
		}
		return list;
	}
}
