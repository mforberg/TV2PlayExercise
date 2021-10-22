package dk.tv2play.exercise.models;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * A Data Model used to store information regarding which shows are aired on a specific day of the week
 */
public class EPGModel {

    private Map<String, ArrayList<ProgramModel>> days;

    /**
     * The Electronic Program Guide model, stored in a HashMap "day" with weekday as key, and ProgramModel as value
     */
    public EPGModel() {
        days = new HashMap<>();
    }

    /**
     * Returns the Electronic Program Guide as a string, based on input JSON data from API request
     * @return days String containing a full week of program airtime data
     */
    @Override
    public String toString() {
        List<String> weekdays = Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
        StringBuilder sb = new StringBuilder();
        for(String day : weekdays) {

            sb.append(StringUtils.capitalize(day)).append(": ");

            if(!days.containsKey(day)) {
                sb.append("Nothing aired today");
            }
            if (days.containsKey(day)) {
                for(ProgramModel program : days.get(day)) {
                    sb.append(program.toString());
                }
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    /**
     * Get method for the EPG, created from JSON data
     * @return HashMap containing weekdays as key and ProgramModel as value
     */
    public Map<String, ArrayList<ProgramModel>> getDays() {
        return days;
    }
}
