package ma.querdos.model;

import java.util.ArrayList;
import java.util.Arrays;

import static ma.querdos.util.ByteManipulation.*;
import static ma.querdos.util.Tag.*;

/**
 * @author : Querdos
 * @date: 27/07/15
 *
 * Copyright to Hamza ESSAYEGH
 */
public class Select_bak {
    public static final byte[] PPSE             = hexStringToByteArray("00 A4 04 00 0E 32 50 41 59 2E 53 59 53 2E 44 44 46 30 31 00");

    public static final byte[] CLA_INS_P1_P2    = hexStringToByteArray("00 A4 04 00");

    // Known AID for our application
    public static final byte[] AID_MASTERCARD  = hexStringToByteArray("A0 00 00 00 04 10 10");
    public static final byte[] AID_MAESTRO     = hexStringToByteArray("A0 00 00 00 04 30 60");
    // VISA not supported yet
    // public static final byte[] AID_VISA        = hexStringToByteArray("A0 00 00 00 03 10 10");

    /**
     * Check the status byte of a SELECT
     * answer
     * **************************************
     * 6283 : SELECTED FILE INVALIDATED     *
     * 6700 : WRONG LENGTH                  *
     * 6A81 : FUNCTION NOT SUPPORTED        *
     * 6A82 : FILE NOT FOUND                *
     * 6A86 : INCORRECT PARAMETERS P1-P2    *
     * 9000 : NORMAL PROCESSING             *
     ****************************************
     *               TESTED                 *
     ****************************************
     *
     * @param b1 the first byte
     * @param b2 the second byte
     * @return a string with the status (according to ISO 14443)
     */
    public static String status_byte(byte b1, byte b2) {
        if (b1 == (byte) 0x62 && b2 == (byte) 0x83)
            return "SELECTED FILE INVALITED";
        else if (b1 == (byte) 0x67 && b2 == (byte) 0x00)
            return "WRONG LENGTH";
        else if (b1 == (byte) 0x6A && b2 == (byte) 0x81)
            return "FUNCTION NOT SUPPORTED";
        else if (b1 == (byte) 0x6A && b2 == (byte) 0x82)
            return "FILE NOT FOUND";
        else if (b1 == (byte) 0x6A && b2 == (byte) 0x86)
            return "INCORRECT PARAMETERS P1-P2";
        else {
            return "NORMAL PROCESSING";
        }
    }

    /**
     * Get the number of Directory Entry for the given card
     *
     * TESTED
     *
     * @param ppse the answer
     * @return integer representing the number in question
     */
    public static int number_directory_entry(byte[] ppse) {
        int count = 0;

        for (byte aPpse : ppse)
            if (aPpse == (byte) 0x61)
                count++;

        return count;
    }

    /**
     * Get all AID from a PPSE response
     *
     * TESTED
     *
     * @param ppse the answer
     * @return an array of AIDs
     */
    public static ArrayList<byte[]> getAidList(byte[] ppse) {
        byte[] aid;

        ArrayList<byte[]> listeAid = new ArrayList<>();
        int length;

        for (int i=0; i<ppse.length; i++) {
            if (ppse[i] == APPLICATION_IDENTIFIER_CARD[0]) {
                length = Integer.parseInt(String.valueOf(ppse[i+1]));
                aid = new byte[length];

                for (int j=0; j<length; j++)
                    aid[j] = ppse[j + i + 2];

                listeAid.add(aid);
            }
        }

        return listeAid;
    }

    /**
     * Compare all aid got previously and select one that is known
     *
     * TODO : test getAID
     * @param aidList the list to provide with AID list
     * @return only one known AID
     */
    public static byte[] getAID(ArrayList<byte[]> aidList) {

        byte[] toReturn = null;

        for (int i=0; i<aidList.size(); i++) {
            if (Arrays.equals(aidList.get(i), AID_MASTERCARD)) {
                toReturn = aidList.get(i);
                break;
            }

//            else if (Arrays.equals(aidList.get(i), AID_VISA)) {
//                toReturn = aidList.get(i);
//            }
//                break;
//            }

//            else if (Arrays.equals(aidList.get(i), AID_MAESTRO)) {
//                toReturn = aidList.get(i);
//                break;
//            }
        }

        return toReturn;
    }

    /**
     * Get the AID from a SELECT PPSE command answer
     * Shall be used only if the result has 1 AID
     *
     * TESTED
     *
     * @param result the SELECT PPSE command answer
     * @return AID supported by the card
     */
    public static byte[] getAID(byte[] result) {
        if (number_directory_entry(result) > 1)
            return null;

        byte[] toReturn = null;

        for (int i=0; i<result.length; i++) {
            if (result[i] == APPLICATION_IDENTIFIER_CARD[0]) {
                byte[] tmp = { result[i+1] };
                String length_hex = byteToHex(tmp);
                int length = Integer.parseInt(length_hex, 16);

                toReturn = new byte[length];

                System.arraycopy(result, i + 2, toReturn, 0, length);

                break;
            }
        }

        return toReturn;
    }

    /**
     * Check the PPSE validity
     *
     * TESTED
     *
     * @param ppse the answer
     * @return true if the SELECT COOMMAND is correct
     */
    public static boolean checkPPSEValidity(byte[] ppse) {
        // Checking if there is a Normal Processing
        if (Select_bak.status_byte(ppse[0], ppse[1]).equals("NORMAL PROCESSING")) {
            // Checking for tags 6F, 84
            if (ppse[0] == FCI_TEMPALTE[0] && ppse[2] == DEDICATED_FILE_NAME[0]) {
                // Getting length for 84
                int DF_Length = Integer.parseInt(String.valueOf(ppse[3]));
                // Checking presence of A5
                if (ppse[4 + DF_Length] == FCI_PROPRIETARY_TEMPLATE[0]) {
                    // Checking for BF OC Tag
                    if (ppse[5 + DF_Length + 1] == FCI_ISSUER_DISCRETIONARY_DATA[0] && ppse[5 + DF_Length + 2] == FCI_ISSUER_DISCRETIONARY_DATA[1]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Construct the APDU to send for SELECT AID
     *
     * TESTED
     *
     * @param AID to select
     * @return APDU SELECT AID Command
     */
    public static byte[] SELECT_AID(byte[] AID) {
        byte[] toReturn = new byte[AID.length + 6];

        System.arraycopy(CLA_INS_P1_P2, 0, toReturn, 0, CLA_INS_P1_P2.length);
        toReturn[4] = (byte) AID.length;
        System.arraycopy(AID, 0, toReturn, 5, AID.length);
        toReturn[toReturn.length - 1] = 0x00;

        return toReturn;
    }

    /**
     * Check if the SELECT AID answer is correct
     *
     * TESTED
     *
     * @param resultAID the answer to check
     * @return true if the answer is correct
     */
    public static boolean checkResultSelectAIDValidity(byte[] resultAID) {
        // Checking tag 6F and 84
        if (resultAID[0] == FCI_TEMPALTE[0] && resultAID[2] == DEDICATED_FILE_NAME[0]) {
            // Getting length of ADF
            int length = Integer.parseInt(String.valueOf(resultAID[3]));
            // Checking tag A5
            if (resultAID[4 + length] == FCI_PROPRIETARY_TEMPLATE[0])
                return true;
        }

        return false;
    }

    /**
     * Get the PDOL from the SELECT AID command
     *
     * TESTED
     *
     * @param resultAID SELECT AID result
     * @return the PDOL (null if there is no PDOL)
     */
    public static byte[] getPdol(byte[] resultAID) {
        byte[] toReturn = null;

        for (int i=0; i<resultAID.length; i++) {
            if (resultAID[i] == PROCESSING_OPTIONS_DATA_OBJECT_LIST[0] && resultAID[i+1] == PROCESSING_OPTIONS_DATA_OBJECT_LIST[1]) {
                int length = Integer.parseInt(byteToHex(new byte[] {resultAID[i+2] }), 16);
                toReturn = new byte[length];

                System.arraycopy(resultAID, i+3, toReturn, 0, length);
                break;
            }
        }

        return toReturn;
    }

    /**
     * Construct a PDOL to send, according to Morocco
     *
     * TODO : Construct correct PDOL
     *
     * @param pdol
     * @return
     */
    public static byte[] constructPDOL(byte[] pdol) {
        if (pdol == null)
            return null;

        // According to Morrocco
        final String TERMINAL_QUANTIFIER            = "B620C000";
        final String AMOUNT_AUTHORIZED              = "000000000100";
        final String AMOUNT_OTHER                   = "000000000000";
        final String TERMINAL_COUNTRY_CODE          = "0504";
        final String TERMINAL_VERIFICATION_RESULT   = "0000000000";
        final String TERMINAL_CURRENCY_CODE         = "0504";
        final String TRANSACTION_DATE               = "150713";
        final String TRANSACTION_TYPE               = "00";
        final String UNPREDICTABLE_NUMBER           = "823DDE7A";

        String concatenedData = TERMINAL_QUANTIFIER +
                AMOUNT_AUTHORIZED +
                AMOUNT_OTHER +
                TERMINAL_COUNTRY_CODE +
                TERMINAL_VERIFICATION_RESULT +
                TERMINAL_CURRENCY_CODE +
                TRANSACTION_DATE +
                TRANSACTION_TYPE +
                UNPREDICTABLE_NUMBER;

        return hexStringToByteArray(concatenedData);
    }

    /**
     * Get the Dedicated File Name from a SELECT AID answer
     *
     * TODO : test getDDF
     *
     * @param result SELECT AID result
     * @return the Dedicated File Name in byte array
     */
    public static byte[] getDDF(byte[] result) {
        byte[] toReturn = null;

        for (int i=0; i<result.length; i++) {
            if (result[i] == DEDICATED_FILE_NAME[0]) {
                byte[] tmp = { result[i+1] };
                String length_hex = byteToHex(tmp);
                int length = Integer.parseInt(length_hex, 16);

                toReturn = new byte[length];

                System.arraycopy(result, i+2, toReturn, 0, length);
                break;
            }
        }

        return toReturn;
    }

    /**
     * Get the Application Label from a SELECT AID result
     *
     * TESTED
     *
     * @param result SELECT AID result
     * @return the APPLICATION LABEL in a byte array
     */
    public static byte[] getApplicationLabel(byte[] result) {
        byte[] toReturn = null;
        byte[] ddf = getDDF(result);

        for (int i=0; i<ddf.length; i++) {
            if (ddf[i] == APPLICATION_LABEL[0]) {
                toReturn = new byte[ddf.length - i - 1];

                System.arraycopy(ddf, i + 1, toReturn, 0, (ddf.length - i - 1));

                break;
            }
        }

        return toReturn;
    }

    /**
     * Get the Issuer Code Table Index from a SELECT AID answer
     *
     * TODO : test getIssuerCodeTableIndex
     *
     * @param result SELECT AID result
     * @return ICTI
     */
    public static byte[] getIssuerCodeTableIndex(byte[] result) {
        byte[] toReturn = null;

        for (int i=0; i<result.length; i++) {
            if (result[i] == ISSUER_CODE_TABLE_INDEX[0] && result[i+1] == ISSUER_CODE_TABLE_INDEX[1]) {
                byte[] tmp = { result[i+2] };
                String length_hex = byteToHex(tmp);
                int length = Integer.parseInt(length_hex, 16);

                toReturn = new byte[length];

                System.arraycopy(result, i+3, toReturn, 0, length);
                break;
            }
        }

        return toReturn;
    }

    /**
     * Get the application Preferred Name from a SELECT AID answer
     *
     * TODO : test getApplicationPreferredName
     *
     * @param result SELECT AID result
     * @return the Application Preferred Name
     */
    public static byte[] getApplicationPreferredName(byte[] result) {
        byte[] toReturn = null;

        for (int i=0; i<result.length; i++) {
            if (result[i] == APPLICATION_PREFERRED_NAME[0] && result[i+1] == APPLICATION_PREFERRED_NAME[1]) {
                byte[] tmp = { result[i+2] };
                String length_hex = byteToHex(tmp);
                int length = Integer.parseInt(length_hex, 16);

                toReturn = new byte[length];

                System.arraycopy(result, i+3, toReturn, 0, length);
                break;
            }
        }

        return toReturn;
    }

    /**
     * Check the DFName
     * @return true if the DF Name's syntax is correct
     */
    public static boolean checkDfName() {
        // TODO : Check DF Name
        return true;
    }
}
