package silver.bittiger.cs504.rias.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.h2.engine.User;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Random;

/**
 * Created by vagrant on 4/12/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RunningInformation {

    public enum HealthWarningLevel{
        HIGH, NORMAL,LOW;

        public boolean isHealth(){return this!=HIGH;}
    }

    @Id
    @GeneratedValue
    private Long id;

    private final String runningId;

    private double latitude;

    private double longitude;

    private double runningDistance;

    private long totalRunningTime;

    private int heartRate;

    @Embedded
    private UserInfo userInfo;

    public RunningInformation(){
        this.runningId = "";
        heartRate = 0;
    }

    @JsonCreator
    public RunningInformation(@JsonProperty ("runningId") String runningId){
        this.runningId = runningId;
        heartRate = 0;
    }

    public int getHeartRate(){
        return new Random().nextInt(120-60)+60;
    }











}
