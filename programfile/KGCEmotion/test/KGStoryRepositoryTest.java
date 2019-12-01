import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.github.oecu_kozaki_lab.entity.KGStory;
import com.github.oecu_kozaki_lab.repository.KGStoryRepository;

public final class KGStoryRepositoryTest {
	@Test
	public void testLoad() {
		KGStoryRepository repo = new KGStoryRepository();
		KGStory story = repo.load("DancingMen", Paths.get("input/DancingMen.ttl"));
		assertEquals(story.name, "DancingMen");
	}
}
