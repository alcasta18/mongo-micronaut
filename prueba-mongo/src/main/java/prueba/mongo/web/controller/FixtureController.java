package prueba.mongo.web.controller;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;
import prueba.mongo.data.repository.FixtureRepository;
import prueba.mongo.domain.Fixture;

@Controller("/fixture")
public class FixtureController {

	protected final FixtureRepository fixtureRepository;

	public FixtureController(FixtureRepository fixtureRepository) {
		this.fixtureRepository = fixtureRepository;
	}

	@Post("/")
	public HttpResponse<Fixture> save(@Body @Valid Fixture fixture) {
		Single<Fixture> s = fixtureRepository.save(fixture);
		s.subscribe();
		return HttpResponse.created(fixture);
	}
	
	@Post("/saveReactive")
	public Single<HttpResponse<Fixture>> saveReactive(@Body @Valid Fixture fixture) {
		return fixtureRepository.save(fixture).map(g -> {
			return HttpResponse.created(fixture);
		});
	}
	
	@Get("/")
	public Flowable<Fixture> list() {
	    return fixtureRepository.findAll();
	}

}
