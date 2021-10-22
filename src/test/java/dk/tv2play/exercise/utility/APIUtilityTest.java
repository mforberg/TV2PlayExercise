package dk.tv2play.exercise.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.tv2play.exercise.models.EPGModel;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class APIUtilityTest {

    private final String jsonInput = "{\n" +
            "  \"monday\": [\n" +
            "    {\n" +
            "      \"title\": \"Nyhederne\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 21600\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Nyhederne\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 36000\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Dybvaaaaad\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 36000\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Dybvaaaaad\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 38100\n" +
            "    }\n" +
            "  ],\n" +
            "  \"tuesday\": [],\n" +
            "  \"wednesday\": [\n" +
            "    {\n" +
            "      \"title\": \"Nyhederne\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 21600\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Nyhederne\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 43200\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Fodbold\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 50400\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Fodbold\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 55800\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Nyhederne\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 75600\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Nyhederne\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 77400\n" +
            "    }\n" +
            "  ],\n" +
            "  \"thursday\": [\n" +
            "    {\n" +
            "      \"title\": \"ESL\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 43200\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"ESL\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 46800\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"ESLPro\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 82800\n" +
            "    }\n" +
            "  ],\n" +
            "  \"friday\": [\n" +
            "    {\n" +
            "      \"title\": \"ESLPro\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 3600\n" +
            "    }\n" +
            "  ],\n" +
            "  \"saturday\": [\n" +
            "    {\n" +
            "      \"title\": \"Comedy\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 52200\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Comedy\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 59400\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Nybyggerne\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 81600\n" +
            "    }\n" +
            "  ],\n" +
            "  \"sunday\": [\n" +
            "    {\n" +
            "      \"title\": \"Nybyggerne\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 5400\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Dybvvvvvad\",\n" +
            "      \"state\": \"begin\",\n" +
            "      \"time\": 41400\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Dybvvvvvad\",\n" +
            "      \"state\": \"end\",\n" +
            "      \"time\": 43200\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    /**
     * Tests that a predefined JSON input is correctly parsed into the EPGModel data model
     * @throws JsonProcessingException if the input cannot be parsed
     */
    @Test
    void createEPGModel() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        APIUtility apiUtility = new APIUtility();


        // convert JSON string to `JsonNode` to match input received Spring
        JsonNode node = mapper.readTree(jsonInput);

        Map<String, Object> result = mapper.convertValue(node, new TypeReference<Map<String, Object>>(){});

        EPGModel epgModel = apiUtility.createEPGModel(result);

        // The amount of weekdays present in the data model should be 5, since no program begins airing on friday and tuesday
        assertEquals(5, epgModel.getDays().size());
        assertFalse(epgModel.getDays().containsKey("tuesday"));
        assertFalse(epgModel.getDays().containsKey("friday"));

        // Assert each remaining weekday has the correct amount of airing program
        assertEquals(2, epgModel.getDays().get("monday").size());
        assertEquals(3, epgModel.getDays().get("wednesday").size());
        assertEquals(2, epgModel.getDays().get("thursday").size());
        assertEquals(2, epgModel.getDays().get("saturday").size());
        assertEquals(1, epgModel.getDays().get("sunday").size());

    }
}