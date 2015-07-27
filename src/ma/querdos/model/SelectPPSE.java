package ma.querdos.model;

import android.nfc.tech.IsoDep;

import java.io.IOException;
import java.util.ArrayList;

import static ma.querdos.util.ByteManipulation.byteToHex;
import static ma.querdos.util.Tag.*;

/**
 * @author : Querdos
 * @date: 27/07/15
 *
 * Copyright to Hamza ESSAYEGH
 */
@SuppressWarnings("unused")
public class SelectPPSE {
    private Apdu apdu;

    private IsoDep isoDep;
    private String responseMessage;

    private ArrayList<String> aidList;

    private static final String SELECTED_FILE_INVALIDATED   = "6283";
    private static final String WRONG_LENGTH                = "6700";
    private static final String FUNCTION_NOT_SUPPORTED      = "6A81";
    private static final String FILE_NOT_FOUND              = "6A82";
    private static final String INCORRECT_PARAMETERS_P1_P2  = "6A86";
    private static final String NORMAL_PROCESSING           = "9000";

    public SelectPPSE(IsoDep isoDep) {
        this.apdu = new Apdu(CommandType.SELECT_PPSE);

        this.aidList = new ArrayList<>();

        this.isoDep = isoDep;
    }

    public String sendCommand() {
        try {
            isoDep.connect();
            responseMessage = byteToHex(isoDep.transceive(apdu.getCommand()));

            if (responseMessage.equals("NULL"))
                return null;

            isoDep.close();

            return getStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getStatus() {
        switch (responseMessage) {
            case SELECTED_FILE_INVALIDATED:
                return "SELECTED FILE INVALITED";
            case WRONG_LENGTH:
                return "WRONG LENGTH";
            case FUNCTION_NOT_SUPPORTED:
                return "FUNCTION NOT SUPPORTED";
            case FILE_NOT_FOUND:
                return "FILE NOT FOUND";
            case INCORRECT_PARAMETERS_P1_P2:
                return "INCORRECT PARAMETERS P1-P2";
            case NORMAL_PROCESSING:
                return "NORMAL PROCESSING";
        }

        return "";
    }

    public int getNumberDirectoryEntry() {
        int toReturn = 0;

        for (int i=0; i<responseMessage.length(); i++)
            if (responseMessage.charAt(i) == '6' && responseMessage.charAt(i) == '1')
                toReturn++;

        return toReturn;
    }

    private String getTagValue(String tag) {
        if (!responseMessage.contains(tag))
            return null;

        int index = responseMessage.lastIndexOf(tag) + tag.length(),
            length = Integer.parseInt(responseMessage.substring(index, index + 2), 16) * 2;


        return responseMessage.substring(index + 2, index+2+length);
    }

    public Apdu getApdu() {
        return apdu;
    }
}
