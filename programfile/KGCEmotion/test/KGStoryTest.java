import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.apache.jena.ext.xerces.util.URI.MalformedURIException;
import org.junit.Test;

import com.github.oecu_kozaki_lab.entity.Character;
import com.github.oecu_kozaki_lab.entity.KGStory;
import com.github.oecu_kozaki_lab.repository.KGStoryRepository;

public final class KGStoryTest {

	@Test
	public void testGetCharacterList() {
		KGStoryRepository repo = new KGStoryRepository();
		KGStory story = repo.load("test", Paths.get("input/DancingMen.ttl"));
		try {
			story.getCharacterList();
		} catch (MalformedURIException err) {
			fail("変なURI");
		}
		assertTrue(true);
	}

	@Test
	public void testGetSentenceByCharacter() {
		KGStoryRepository repo = new KGStoryRepository();
		KGStory story = repo.load("test", Paths.get("input/SpeckledBand.ttl"));
		try {
			final Character roma = story.getCharacterList()
					.stream()
					.filter(c -> c.name.equals("Roma@en"))
					.findFirst()
					.get();
			story.getSceneByCharacter(roma);
		} catch (MalformedURIException err) {
			fail("変なURI");
		}
		assertTrue(true);
	}
}
