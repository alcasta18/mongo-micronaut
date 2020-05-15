package prueba.mongo.data.mongo;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.reactivex.Flowable;
import io.reactivex.Single;
import prueba.mongo.data.repository.FixtureRepository;
import prueba.mongo.domain.Fixture;

@Singleton
public class FixtureRepositoryMongo implements FixtureRepository{
	
	public static final String DB_NAME = "fixturesDb";
    public static final String COLLECTION_NAME = "fixtures";

    private MongoClient mongoClient;

    public FixtureRepositoryMongo(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Single<Fixture> save(@Valid Fixture fixture) {
        return Single.fromPublisher(getCollection().insertOne(fixture)).map(success -> fixture);
    }

    @Override
    public Flowable<Fixture> findAll() {
        return Flowable.fromPublisher(getCollection().find());
    }

    @Override
    public Single<Long> count() {
        return Single.fromPublisher(getCollection().countDocuments());
    }

    private MongoCollection<Fixture> getCollection() {
        return mongoClient.getDatabase(DB_NAME).getCollection(COLLECTION_NAME, Fixture.class);
    }

	@Override
	public Single<Fixture> find(@NotNull Long id) {
		return Single.fromPublisher(getCollection().find(new Document("homeClubId", id)));
	}
    
}
