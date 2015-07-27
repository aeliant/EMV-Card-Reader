package ma.querdos.model;

import static ma.querdos.util.ByteManipulation.*;
/**
 * @author : Querdos
 * @date: 27/07/15
 *
 * Copyright to Hamza ESSAYEGh
 */
@SuppressWarnings("unused")
public class Apdu {
    private String CLA;
    private String INS;
    private String P1;
    private String P2;
    private String LC;
    private String data;
    private String LE;

    /**
     * Constructor with no arguments
     */
    public Apdu() {
        this("", "", "", "", "", "", "");
    }

    /**
     * Default Constructor
     * @param CLA
     * @param INS
     * @param p1
     * @param p2
     * @param LC
     * @param data
     * @param LE
     */
    public Apdu(String CLA, String INS, String p1, String p2, String LC, String data, String LE) {
        this.CLA = CLA;
        this.INS = INS;
        this.P1 = p1;
        this.P2 = p2;
        this.LC = LC;
        this.data = data;
        this.LE = LE;
    }

    /**
     * Constructor with a predefined command type
     * @param commandType
     */
    public Apdu(CommandType commandType) {
        switch (commandType) {
            case SELECT_PPSE:
                CLA     = "00";
                INS     = "A4";
                P1      = "04";
                P2      = "00";
                LC      = "0E";
                data    = "32 50 41 59 2E 53 59 53 2E 44 44 46 30 31".replace(" ", "");
                LE      = "00";
                break;
            case SELECT_AID:
                CLA = "00";
                INS = "A4";
                P1  = "04";
                P2  = "80";
                LC  = "00"; // TODO : Check for first or second occurence
                // Data is AID
                LE  = "00";
                break;
            case READ_RECORD:
                CLA     = "00";
                INS     = "B2";
                // P1 is record number
                // P2 : depend on P1
                LC      = "";
                data    = "";
                LE      = "00";
                break;
            case GET_PROCESSING_OPTIONS:
                CLA = "80";
                INS = "A8";
                P1  = "00";
                P2  = "00";
                // LC is var
                // Data is PDOL Related data
                LE  = "00";
                break;
            case GENERATE_AC:
                CLA = "80";
                INS = "AE";
                // P1 is Reference Control Parameter
                P2  = "00";
                // LC is var
                // Data is CDOL related data
                LE  = "00";
                break;
            case COMPUTE_CRYPTOGRAM_CHECKSUM:
                CLA = "80";
                INS = "2A";
                P1  = "8E";
                P2  = "80";
                // LC is var
                // Data is UDOL Related Data
                LE  = "00";
                break;
        }
    }

    public byte[] getCommand() {
        return hexStringToByteArray(CLA + INS + P1 + P2 + LC + data + LE);
    }

    public String getCLA() {
        return CLA;
    }

    public void setCLA(String CLA) {
        this.CLA = CLA;
    }

    public String getINS() {
        return INS;
    }

    public void setINS(String INS) {
        this.INS = INS;
    }

    public String getP1() {
        return P1;
    }

    public void setP1(String p1) {
        P1 = p1;
    }

    public String getP2() {
        return P2;
    }

    public void setP2(String p2) {
        P2 = p2;
    }

    public String getLC() {
        return LC;
    }

    public void setLC(String LC) {
        this.LC = LC;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLE() {
        return LE;
    }

    public void setLE(String LE) {
        this.LE = LE;
    }
}
