package prueba.mongo.data.repository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.reactivex.Flowable;
import io.reactivex.Single;
import prueba.mongo.domain.Fixture;

public interface FixtureRepository {
	
	Single<Fixture> save(@Valid Fixture fixture);
    Flowable<Fixture> findAll();
    Single<Long> count();
	Single<Fixture> find(@NotNull Long id);
}
