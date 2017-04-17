package silver.bittiger.cs504.rias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import silver.bittiger.cs504.rias.dao.RunningInformationRepository;
import silver.bittiger.cs504.rias.domain.RunningInformation;
import silver.bittiger.cs504.rias.service.InformationService;

/**
 * Created by vagrant on 4/12/17.
 */
@Service
public class InformationServiceImpl implements InformationService {

    private RunningInformationRepository runningInformationRepository;

    @Autowired
    public InformationServiceImpl(RunningInformationRepository runningInformationRepository){
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
    public void deleteRunningInformationByRunningId(String runningId) {
        runningInformationRepository.delete(new RunningInformation(runningId));
    }

    @Override
    public Page<RunningInformation> findByHealthWarningLevel(String healthWarningLevel, Pageable pageable) {
        return runningInformationRepository.findByHealthWarningLevel(RunningInformation.HealthWarningLevel.valueOf(healthWarningLevel), pageable);
    }
}
