package silver.bittiger.cs504.rias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import silver.bittiger.cs504.rias.domain.RunningInformation;

/**
 * Created by vagrant on 4/12/17.
 */
public interface RunningInformationRepository extends JpaRepository<RunningInformation, Long> {

    Page<RunningInformation> findByHealthWarningLevel(
            @Param("healthWarningLevel") RunningInformation.HealthWarningLevel healthWarningLevel,
            Pageable pageable);
}
