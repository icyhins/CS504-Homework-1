package silver.bittiger.cs504.rias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import silver.bittiger.cs504.rias.domain.RunningInformation;

public interface RunningInformationAnalysisRepository extends JpaRepository<RunningInformation, Long> {

    Page<RunningInformation> findByHeartRate(
            @Param("heartRate") int heartRate,
            Pageable pageable);

    Page<RunningInformation> findByHeartRateGreaterThan(
            @Param("heartRate") int heartRate,
            Pageable pageable);

    RunningInformation findFirstByRunningId(
            @Param("runningId") String runningId);

    Page<RunningInformation> findOrderByHeartRateDesc(Pageable pageable);
}
