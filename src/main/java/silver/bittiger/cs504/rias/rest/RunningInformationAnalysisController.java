package silver.bittiger.cs504.rias.rest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silver.bittiger.cs504.rias.domain.RunningInformation;
import silver.bittiger.cs504.rias.service.RunningInformationAnalysisService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInformationAnalysisController {

    @Autowired
    RunningInformationAnalysisService service;

    private final String kDefaultPage = "0";

    private final String kDefaultSize = "20";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformations){

        service.saveRunningInformations(runningInformations);
    }

    @RequestMapping(value = "/delete/all",method = RequestMethod.DELETE)
    public void delteAll(){
        service.deleteAll();
    }

    @RequestMapping(value = "/delete/id/{runningId}", method = RequestMethod.DELETE)
    public void deleteByRunningId(@PathVariable String runningId) {
        service.deleteByRunningId(runningId);

    }

    @RequestMapping(value="/query/heartRate/{heartRate}",method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRate(@PathVariable Integer heartRate,
                                                    @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
                                                    @RequestParam(name = "size", required = false, defaultValue = kDefaultSize) Integer  size){
        return service.findByHeartRate(heartRate, new PageRequest(page, size));
    }

    @RequestMapping(value="/query/heartRateGreaterThan/{heartRate}",method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRateGreaterThan(@PathVariable Integer heartRate,
                                                    @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
                                                    @RequestParam(name = "size", required = false, defaultValue = kDefaultSize) Integer  size){
        return service.findByHeartRateGreaterThan(heartRate, new PageRequest(page, size));
    }



    @RequestMapping(value = "/query/all", method = RequestMethod.GET)
    public ResponseEntity<?> findAllRunningInformationOrderByHealthLevel(
            @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = kDefaultSize) Integer size){
        //Ger RAW data
        Page<RunningInformation> rawResults = service.findAllRunningInformationOrderByHealthLevel(new PageRequest(page,size));
        List<RunningInformation> content = rawResults.getContent();

        //Transform RunningInformation to customzed JSON object
        List<JsonObject> results = new ArrayList<>();
        for(RunningInformation item :content){
            JsonObject obj = new JsonObject();
            obj.addProperty("runningId",item.getRunningId());
            obj.addProperty("totalRunningTime",item.getTotalRunningTime());
            obj.addProperty("heartRate",item.getHeartRate());
            obj.addProperty("userId",item.getId());
            obj.addProperty("userName",item.getUserInfo().getUsername());
            obj.addProperty("userAddress",item.getUserInfo().getAddress());
            obj.addProperty("healthWarningLevel",item.getHealthWarningLevel());
            results.add(obj);
        }

        return new ResponseEntity<List<JsonObject>>(results, HttpStatus.OK);
    }
}
