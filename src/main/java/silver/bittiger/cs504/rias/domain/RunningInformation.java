package silver.bittiger.cs504.rias.domain;

import java.util.Random;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by vagrant on 4/12/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Embeddable
@RequiredArgsConstructor
public class RunningInformation {

    public enum HealthWarningLevel{
        HIGH, NORMAL,LOW;

        public boolean isHealth(){return this!=HIGH;}
    }

    @Id
    @GeneratedValue
    private Long id;

    private String runningId;

    private double latitude;

    private double longitude;

    private double runningDistance;

    private long totalRunningTime;

    @JsonIgnore
    private int heartRate;

    @Embedded
    private final UserInfo userInfo;

    public RunningInformation(){
    	this.userInfo = null;
    }

    @JsonCreator
	public RunningInformation(@JsonProperty("username") String username) {
        this.userInfo = new UserInfo(username);
    }

    @JsonCreator
    public RunningInformation(
    		@JsonProperty("runningId") String runningId,
    		@JsonProperty("latitude") String latitude,
    		@JsonProperty("longitude") String longitude,
    		@JsonProperty("runningDistance") String runningDistance,
    		@JsonProperty("totalRunningTime") String totalRunningTime,
    		@JsonProperty("userInfo") UserInfo userInfo
    		){
    	this.runningId = runningId;
    	this.latitude = Double.parseDouble(latitude);
    	this.longitude = Double.parseDouble(longitude);
    	this.runningDistance = Double.parseDouble(runningDistance);
    	this.totalRunningTime = Long.parseLong(totalRunningTime);
    	this.heartRate = getHeartRate(60, 120);
    	this.userInfo = userInfo;
    }

    private int getHeartRate(int min, int max){
        return new Random().nextInt(max-min+1)+min;
    }



	



}
