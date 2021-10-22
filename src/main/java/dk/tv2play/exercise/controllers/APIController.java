package dk.tv2play.exercise.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.tv2play.exercise.models.EPGModel;
import dk.tv2play.exercise.utility.APIUtility;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class APIController {

    @PostMapping("/api")
    public String api(@RequestBody JsonNode payload) {

        //System.out.println(payload.toPrettyString());
        APIUtility apiUtility = new APIUtility();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> result = mapper.convertValue(payload, new TypeReference<Map<String, Object>>(){});

        EPGModel epgModel = apiUtility.createEPGModel(result);

        System.out.println(epgModel);

        return epgModel.toString();
    }

}
