package silver.bittiger.cs504.rias.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import silver.bittiger.cs504.rias.domain.RunningInformation;

import java.util.List;

/**
 * Created by vagrant on 4/12/17.
 */

public interface InformationService {

    List<RunningInformation> saveRunningInformations(List<RunningInformation> runningInformations);

    void deleteAll();

    void deleteRunningInformationByRunningId(String runningId);

    Page<RunningInformation> findByHealthWarningLevel(String level, Pageable pageable);
}
