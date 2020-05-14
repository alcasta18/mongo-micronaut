package prueba.mongo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.mongodb.reactivestreams.client.MongoClient;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.annotation.MicronautTest;
import prueba.mongo.data.mongo.FixtureRepositoryMongo;

@MicronautTest(transactional = true)
@Testcontainers
public class FixtureRepositoryMongoTestContainer {
	
	@Container
	GenericContainer mongo = new GenericContainer("mongo:4.0").withExposedPorts(27017);

	@BeforeEach
	public void setupSpec() {
		mongo.start();
	}
	
	@Test
	public void mongoConnectionTest() {
		ApplicationContext applicationContext = ApplicationContext.run(
                "mongodb://${mongo.getContainerIpAddress()}:${mongo.getMappedPort(27017)}"
        );
        MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
        applicationContext.registerSingleton(FixtureRepositoryMongo.class);
        FixtureRepositoryMongo repo = new FixtureRepositoryMongo(mongoClient);
        assertEquals(0, repo.count().blockingGet().longValue());
        mongoClient.close();
        System.out.println("termino el assert");
        applicationContext.stop();
	}

}
