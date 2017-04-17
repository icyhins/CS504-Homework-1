package silver.bittiger.cs504.rias.domain;

import java.sql.Timestamp;
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

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Embeddable
public class RunningInformation {

    public enum HealthWarningLevel{
        HIGH, NORMAL,LOW,UNKNOWN;

        public static HealthWarningLevel getHealthWarningLevel(int heartRate){

        	// heartRate > 120 -- HIGH
        	if(heartRate > 120){
        		return HealthWarningLevel.HIGH;
        	}
        	// 120 >= heartRate > 75 -- NORMAL
        	else if(120 >= heartRate && heartRate > 75){
        		return HealthWarningLevel.NORMAL;
        	}
        	// 75 >= heartRate >= 60 -- LOW
        	else if(75 >= heartRate && heartRate >= 60){
        		return HealthWarningLevel.LOW;
        	}else{
        		return HealthWarningLevel.UNKNOWN;// other, return null
        	}

        }
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
    private Timestamp createTime;

    @JsonIgnore
    private int heartRate;

    @Embedded
    private final UserInfo userInfo;

    private String healthWarningLevel;

    public RunningInformation(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    @JsonCreator
    public RunningInformation(
             @JsonProperty("runningId") String runningId,
             @JsonProperty("latitude")String latitude,
             @JsonProperty("longitude")String longitude,
             @JsonProperty("runningDistance")String runningDistance,
             @JsonProperty("totalRunningTime")String totalRunningTime,
             //@JsonProperty("heartRate")String heartRate,
             @JsonProperty("userInfo") UserInfo userInfo
    ){
        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Long.parseLong(totalRunningTime);
        this.heartRate = getHeartRate(60,120);
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.userInfo = userInfo;
        this.healthWarningLevel = HealthWarningLevel.getHealthWarningLevel(this.heartRate).toString();
    }

    private int getHeartRate(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

}
