package ma.querdos.model;

import static ma.querdos.util.ByteManipulation.*;

/**
 * @author : Querdos
 * @date: 27/07/15
 *
 * Copyright to Hamza ESSAYEGH - Querdos
 */
@SuppressWarnings("unused")
public class TransactionStatusInformation {
    private byte[] result;

    public TransactionStatusInformation() {
        this.result = hexStringToByteArray("00 00");
    }

    public void setOfflineTransactionWasPerformed(boolean offlineTransactionWasPerformed) {
        if (offlineTransactionWasPerformed) {
            // 0xxxxxxx
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = "1" + byte_1.substring(1);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = "0" + byte_1.substring(1);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setCardholderVerificationWasPerformed(boolean cardholderVerificationWasPerformed) {
        if (cardholderVerificationWasPerformed) {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.charAt(0) + "1" + byte_1.substring(2);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.charAt(0) + "0" + byte_1.substring(2);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setCardRiskManagementWasPerformed(boolean cardRiskManagementWasPerformed) {
        if (cardRiskManagementWasPerformed) { // 00100000
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 2) + "1" + byte_1.substring(4);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 2) + "0" + byte_1.substring(4);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setIssuerAuthentificationWasPerformed(boolean issuerAuthentificationWasPerformed) {
        if (issuerAuthentificationWasPerformed) { // 00100000
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 3) + "1" + byte_1.substring(5);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 3) + "0" + byte_1.substring(5);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setTerminalRiskManagementWasPerformed(boolean terminalRiskManagementWasPerformed) {
        if (terminalRiskManagementWasPerformed) { // 00100000
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 4) + "1" + byte_1.substring(6);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 4) + "0" + byte_1.substring(6);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setScriptProcessingWasPerformed(boolean scriptProcessingWasPerformed) {
        if (scriptProcessingWasPerformed) { // 00100000
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 5) + "1" + byte_1.substring(7);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 5) + "0" + byte_1.substring(7);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public byte[] getResult() {
        return result;
    }
}
