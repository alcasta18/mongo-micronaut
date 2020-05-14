package prueba.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import io.micronaut.test.annotation.MicronautTest;
import prueba.mongo.data.repository.FixtureRepository;
import prueba.mongo.domain.Fixture;

@MicronautTest
public class FixtureRepositoryMongoTest {
	
	@Inject
	FixtureRepository repository;
	
	private MongodExecutable mongodExe;
	private MongodProcess mongod;
	private MongoClient mongo;

	@BeforeEach
	public void beforeEach() throws Exception {
		System.out.println("entro");
		MongodStarter starter = MongodStarter.getDefaultInstance();
		String bindIp = "localhost";
		int port = 12345;
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(bindIp, port, Network.localhostIsIPv6())).build();
		this.mongodExe = starter.prepare(mongodConfig);
		this.mongod = mongodExe.start();
		this.mongo = new MongoClient(bindIp, port);
	}

	@AfterEach
	public void afterEach() throws Exception {
		System.out.println("entro al after");
		this.mongod.stop();
		this.mongodExe.stop();
		this.mongo.close();
	}

	@Test
	public void testCrud() {
		System.out.println("entro al test");
		Long totalFixtures = repository.count().blockingGet().longValue();
		System.out.println(totalFixtures);
		repository.save(new Fixture(1L, 2L, (short) 5, (short) 0, new Date())).subscribe();
		System.out.println("guardo 1");
		repository.save(new Fixture(3L, 4L, (short) 5, (short) 0, new Date())).subscribe();
		System.out.println("guardo 2");
		assertEquals(totalFixtures + 2, repository.count().blockingGet().longValue());
		System.out.println(repository.count().blockingGet().longValue());
		assertEquals(totalFixtures + 2, repository.findAll().toList().blockingGet().size());
		System.out.println(repository.findAll().toList().blockingGet().size());
	}
}
