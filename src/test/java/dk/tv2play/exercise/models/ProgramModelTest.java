package dk.tv2play.exercise.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramModelTest {

    private final ProgramModel pgm = new ProgramModel("Nyhederne");

    /**
     * Assert that time conversion from unixtime to regular time is correct
     */
    @Test
    void testToString() {
        pgm.setStart(21600);
        pgm.setEnd(36000);

        assertEquals("Nyhederne 06:00 - 10:00 / ", pgm.toString());

        pgm.setStart(36000);
        pgm.setEnd(38100);

        assertEquals("Nyhederne 10:00 - 10:35 / ", pgm.toString());
    }
}