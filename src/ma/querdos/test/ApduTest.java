package ma.querdos.test;

import ma.querdos.model.Apdu;
import ma.querdos.model.CommandType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Querdos
 * @date: 27/07/15
 *
 * Copyright to Hamza ESSAYEGH
 */
public class ApduTest {

    @Test
    public void testGetCommand() throws Exception {
        // Testing constructor with predefined command type

        /* ****************************************************** */
        String valueExpected = "00 A4 04 00 0E 32 50 41 59 2E 53 59 53 2E 44 44 46 30 31 00".replace(" ", "");
        Apdu apdu = new Apdu(CommandType.SELECT_PPSE);

        assertTrue("Problem with SELECT PPSE", apdu.getCommand().equals(valueExpected));

        /* ****************************************************** */
        valueExpected = "00 A4 04 80 00 07 A0 00 00 00 04 10 10 00".replace(" ", "");
        apdu = new Apdu(CommandType.SELECT_AID);
        apdu.setData("07 A0 00 00 00 04 10 10".replace(" ", ""));

        assertTrue("Problem with SELECT AID", apdu.getCommand().equals(valueExpected));

        /* ****************************************************** */
        valueExpected = "00 B2 01 14 00".replace(" " , "");
        apdu = new Apdu(CommandType.READ_RECORD);
        apdu.setP1("01");
        apdu.setP2("14");

        assertTrue("Problem with READ RECORD", apdu.getCommand().equals(valueExpected));

        /* ****************************************************** */
        valueExpected = "80 A8 00 00 02 83 00 00".replace(" ", "");
        apdu = new Apdu(CommandType.GET_PROCESSING_OPTIONS);

        apdu.setLC("02");
        apdu.setData("83 00".replace(" ", ""));

        assertTrue("Problem with GPO", apdu.getCommand().equals(valueExpected));
    }
}