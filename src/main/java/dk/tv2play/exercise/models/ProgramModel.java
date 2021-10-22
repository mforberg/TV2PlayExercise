package dk.tv2play.exercise.models;

import java.time.Instant;

/**
 * A Data Model for a "Program", contains the title, starting and ending time, and the day of the week it is aired.
 */
public class ProgramModel {

    private String title;
    private int start;
    private int end;
    private String day;

    /**
     * Constructor for the ProgramModel, which contains information regarding a program
     * @param title, the title of the show
     */
    public ProgramModel(String title) {
        this.title = title;
    }

    /**
     * The starting time of the program
     * @param start, int representing the starting airtime of the program, specified in unixtime
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * The ending time of the program
     * @param end, int representing the ending airtime of the program, specified in unixtime
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Get method for the starting airtime of the program
     * @return int start, specified in unixtime
     */
    public int getStart() {
        return start;
    }

    /**
     * Get method for the ending airtime of the program
     * @return int end, specified in unixtime
     */
    public int getEnd() {
        return end;
    }

    /**
     * Get method for the day of the week the program is airing (String)
     * @return String day of week (e.g. monday)
     */
    public String getDay() {
        return day;
    }

    /**
     * Sets the day of the week for the given program
     * @param day of week, e.g. monday/tuesday/etc.
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * String representation of a programs title, start, and, end time
     * @return String representation of program
     */
    @Override
    public String toString() {

        // Converting Instant to LocalDateTime and formatting is a hassle
        String startTime = Instant.ofEpochSecond(this.start).toString().substring(11, 16);
        String endTime = Instant.ofEpochSecond(this.end).toString().substring(11, 16);

        return this.title + " " + startTime + " - " + endTime + " / ";
    }
}
