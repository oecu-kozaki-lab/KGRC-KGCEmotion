import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.github.oecu_kozaki_lab.repository.EmotionWordDictRepository;

public final class EmotionWordDictRepositoryTest {
	@Test
	public void testLoad() {
		EmotionWordDictRepository repo = new EmotionWordDictRepository();
		try {
			repo.load(Paths.get("./NRC-AILexicon.txt"));
		} catch (IOException err) {
			fail();
		}
		assertTrue(true);
	}
}
