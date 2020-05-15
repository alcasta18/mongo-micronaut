package prueba.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import prueba.mongo.domain.Fixture;

@MicronautTest
public class FixtureControllerTest {
	
	@Inject
    EmbeddedServer server; 

    @Inject
    @Client("/")
    HttpClient client; 

    @Test
    public void saveFixtureTest() {
        HttpRequest request = HttpRequest.POST("/fixture", new Fixture(1L, 2L, (short) 5, (short) 0, new Date())); 
        HttpResponse response = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.CREATED, response.getStatus());
    }
    
}
