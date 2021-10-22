package dk.tv2play.exercise.utility;

import dk.tv2play.exercise.models.EPGModel;
import dk.tv2play.exercise.models.ProgramModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for handling data conversion from API
 */
public class APIUtility {

    /**
     * The following method takes the provided JSON data from the API request,
     * and then  assigns it to an ArrayList chronologically, therefore,
     * the first specified program will be first, and the last specified program the last (FIFO)
     * @param data the JSON data provided in the API request
     * @return a "sorted" ArrayList with all programs ("begin" and "end" are separate entries)
     */
    private ArrayList<ProgramModel> jsonToList(Map<String, Object> data) {

        ArrayList<ProgramModel> programModelArrayList = new ArrayList<>();

        // For each day specified in the JSON data
        for (Map.Entry<String, Object> entry : data.entrySet()) {

            // For each program entry on a given day
            for(Object ob : (ArrayList) entry.getValue()) {

                // Cast "Object" to correct HashMap
                HashMap<String, Object> mob = (HashMap<String, Object>) ob;

                // Assign attributes to a data model for later use
                ProgramModel pgm = new ProgramModel((String) mob.get("title"));
                pgm.setDay(entry.getKey());

                // A program is either in state "begin" or "end", set the correct attribute in data model
                if(mob.get("state").equals("begin")) {
                    pgm.setStart((int) mob.get("time"));
                } else {
                    pgm.setEnd((int) mob.get("time"));
                }
                programModelArrayList.add(pgm);
            }
        }
        return programModelArrayList;
    }

    /**
     * The following method will convert a list of all programs (provided by the "jsonToList()" method)
     * to an "EPGModel", which contains a HashMap with each day of the week, and their corresponding programs
     * @param data JSON data provided in the API request
     * @return an EPGModel which contains HashMap<String, ArrayList<ProgramModel>> key = weekday, value = full program data
     */
    public EPGModel createEPGModel(Map<String, Object> data) {
        EPGModel epgModel = new EPGModel();

        // Use a utility method to convert JSON data to a list that is easier to work with
        ArrayList<ProgramModel> programModelArrayList = jsonToList(data);

        // Since Program information is always even (each program has a beginning and an end), a regular for loop
        // can iterate in steps of 2. This is needed due to each program's beginning and end being separate entries in JSON data
        for(int i = 0; i < programModelArrayList.size(); i += 2) {
            //programModelArrayList[i] = always a beginning state, programModelArrayList[i+1] = corresponding end
            programModelArrayList.get(i).setEnd(programModelArrayList.get(i+1).getEnd());

            // Identify day, so it can assigned to corresponding key in HashMap
            String day = programModelArrayList.get(i).getDay();

            // Add additional entry to ArrayList<ProgramModel> if the weekday exists as key
            if(epgModel.getDays().containsKey(day)) {
                epgModel.getDays().get(day).add(programModelArrayList.get(i));
            // the key does not exist, create a new ArrayList with the ProgramModel as an entry, and insert to HashMap
            } else {
                ArrayList<ProgramModel> newDayProgramModelList = new ArrayList<>();
                newDayProgramModelList.add(programModelArrayList.get(i));
                epgModel.getDays().putIfAbsent(day, newDayProgramModelList);
            }
        }
        return epgModel;
    }


}
