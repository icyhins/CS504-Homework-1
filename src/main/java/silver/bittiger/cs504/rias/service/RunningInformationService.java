package silver.bittiger.cs504.rias.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import silver.bittiger.cs504.rias.domain.RunningInformation;

import java.util.List;

/**
 * Created by vagrant on 4/12/17.
 */

public interface RunningInformationService {

    List<RunningInformation> saveRunningInformations(List<RunningInformation> runningInformationList);

    void deleteAll();

    Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable);

    Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable);

    Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(Pageable pageable);


}
