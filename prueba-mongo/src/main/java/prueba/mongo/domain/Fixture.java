package prueba.mongo.domain;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Fixture {
	@BsonId
	private ObjectId id;

	private Long homeClubId;
	private Long awayClubId;

	private Short homeScore;
	private Short awayScore;

	private Date date;
	
	@BsonCreator
	@JsonCreator
	public Fixture(@BsonProperty("homeClubId") @JsonProperty("homeClubId") Long homeClubId,
	               @BsonProperty("awayClubId") @JsonProperty("awayClubId") Long awayClubId,
	               @BsonProperty("homeScore") @JsonProperty("homeScore") Short homeScore,
	               @BsonProperty("awayScore") @JsonProperty("awayScore") Short awayScore,
	               @BsonProperty("date") @JsonProperty("date") Date date) {
	    this.homeClubId = homeClubId;
	    this.awayClubId = awayClubId;
	    this.homeScore = homeScore;
	    this.awayScore = awayScore;
	    this.date = date;
	}

	public ObjectId getId() {
		return id;
	}

	public Long getHomeClubId() {
		return homeClubId;
	}

	public Long getAwayClubId() {
		return awayClubId;
	}

	public Short getHomeScore() {
		return homeScore;
	}

	public Short getAwayScore() {
		return awayScore;
	}

	public Date getDate() {
		return date;
	}
	
	
	
}
