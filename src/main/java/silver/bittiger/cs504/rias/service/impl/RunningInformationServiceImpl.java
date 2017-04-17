package silver.bittiger.cs504.rias.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import silver.bittiger.cs504.rias.dao.RunningInformationRepository;
import silver.bittiger.cs504.rias.domain.RunningInformation;
import silver.bittiger.cs504.rias.service.RunningInformationService;

import java.util.List;

/**
 * Created by vagrant on 4/12/17.
 */
@Service
public class RunningInformationServiceImpl implements RunningInformationService {

    private RunningInformationRepository runningInformationRepository;

    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository){
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformations(List<RunningInformation> runningInformations) {
        return runningInformationRepository.save(runningInformations);
    }

    @Override
    public void deleteAll() {
        runningInformationRepository.deleteAll();
    }

    @Override
    public Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRate(heartRate,pageable);
    }

    @Override
    public Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRateGreaterThan(heartRate, pageable);
    }

    @Override
    public Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(Pageable pageable) {
        return null;
    }

}
