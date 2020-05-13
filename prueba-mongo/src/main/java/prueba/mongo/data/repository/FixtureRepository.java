package prueba.mongo.data.repository;

import javax.validation.Valid;

import io.reactivex.Flowable;
import io.reactivex.Single;
import prueba.mongo.domain.Fixture;

public interface FixtureRepository {
	
	Single<Fixture> save(@Valid Fixture fixture);
    Flowable<Fixture> findAll();
    Single<Long> count();
	
}
