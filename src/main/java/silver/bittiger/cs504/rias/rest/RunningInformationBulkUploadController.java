package silver.bittiger.cs504.rias.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import silver.bittiger.cs504.rias.domain.RunningInformation;
import silver.bittiger.cs504.rias.service.InformationService;

import java.util.List;

/**
 * Created by vagrant on 4/12/17.
 */
@RestController
public class RunningInformationBulkUploadController {

    @Autowired
    InformationService informationService;

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformations){

        informationService.saveRunningInformations(runningInformations);
    }

    @RequestMapping(value="/delete/{runningId}",method = RequestMethod.DELETE)
    public void delete(@PathVariable String runningId){
        informationService.deleteRunningInformationByRunningId(runningId);
    }

    @RequestMapping(value = "/purge",method = RequestMethod.DELETE)
    public void purge(){
        informationService.deleteAll();
    }

    @RequestMapping(value="/running/{healthWarningLevel}",method = RequestMethod.GET)
    public Page<RunningInformation> findByHealthWarningLevel(@PathVariable String healthWarningLevel,
                                                             @RequestParam(name = "page", required = false) Integer page,
                                                             @RequestParam(name = "size", required = false) Integer  size){
        return  this.informationService.findByHealthWarningLevel(healthWarningLevel,new PageRequest(page,size));
    }



}
