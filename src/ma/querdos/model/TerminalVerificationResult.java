package ma.querdos.model;

import static ma.querdos.util.ByteManipulation.*;
/**
 * @author : Querdos
 * @date: 24/07/15
 *
 * Copyright to Hamza ESSAYEGH - Querdos
 *
 * This class represents the Terminal Verification Results
 * used while reading the EMV Card
 */
@SuppressWarnings("unused")
public class TerminalVerificationResult {
    private byte[] result;

    public TerminalVerificationResult() {
        result = hexStringToByteArray("00 00 00 00 00");
    }

    public void setOfflineDataAuthenticationWasNotPerformed(boolean offlineDataAuthentificationWasNotPerformed) {

        if (offlineDataAuthentificationWasNotPerformed) {
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

    public void setSdaFailed(boolean sdaFailed) {

        if (sdaFailed) {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.charAt(0) + "1" + byte_1.substring(2);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.charAt(0) + "0" + byte_1.substring(2);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setIccDataMissing(boolean iccDataMissing) {

        if (iccDataMissing) { // 00100000
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 2) + "1" + byte_1.substring(4);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 2) + "0" + byte_1.substring(4);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setCardAppearsOnTerminalExceptionFile(boolean cardAppearsOnTerminalExceptionFile) {

        if (cardAppearsOnTerminalExceptionFile) {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 3) + "1" + byte_1.substring(5);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 3) + "0" + byte_1.substring(5);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setDdaFailed(boolean ddaFailed) {

        if (ddaFailed) {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 4) + "1" + byte_1.substring(6);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 4) + "0" + byte_1.substring(6);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setCdaFailed(boolean cdaFailed) {

        if (cdaFailed) {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 5) + "1" + byte_1.substring(7);

            result[0] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[0]});
            byte_1 = byte_1.substring(0, 5) + "0" + byte_1.substring(7);

            result[0] = fromBinary(byte_1)[0];
        }
    }

    public void setIccAndTerminalHaveDifferentApplicationVersions(boolean iccAndTerminalHaveDifferentApplicationVersions) {
        if (iccAndTerminalHaveDifferentApplicationVersions) {
            // 0xxxxxxx
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = "1" + byte_1.substring(1);

            result[1] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = "0" + byte_1.substring(1);

            result[1] = fromBinary(byte_1)[0];
        }
    }

    public void setExpiredApplication(boolean expiredApplication) {
        if (expiredApplication) {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.charAt(0) + "1" + byte_1.substring(2);

            result[1] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.charAt(0) + "0" + byte_1.substring(2);

            result[1] = fromBinary(byte_1)[0];
        }
    }

    public void setApplicationNotYetEffective(boolean applicationNotYetEffective) {
        if (applicationNotYetEffective) {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 2) + "1" + byte_1.substring(4);

            result[1] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 2) + "0" + byte_1.substring(4);

            result[1] = fromBinary(byte_1)[0];
        }
    }

    public void setRequestedServiceNotAllowedForCardProduct(boolean requestedServiceNotAllowedForCardProduct) {
        if (requestedServiceNotAllowedForCardProduct) {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 3) + "1" + byte_1.substring(5);

            result[1] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 3) + "0" + byte_1.substring(5);

            result[1] = fromBinary(byte_1)[0];
        }
    }

    public void setNewCard(boolean newCard) {
        if (newCard) {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 4) + "1" + byte_1.substring(6);

            result[1] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 4) + "0" + byte_1.substring(6);

            result[1] = fromBinary(byte_1)[0];
        }
    }

    public void setCardholderVerificationWasNotSuccessful(boolean cardholderVerificationWasNotSuccessful) {
        if (cardholderVerificationWasNotSuccessful) {
            // 0xxxxxxx
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = "1" + byte_1.substring(1);

            result[2] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = "0" + byte_1.substring(1);

            result[2] = fromBinary(byte_1)[0];
        }
    }

    public void setUnrecognisedCVM(boolean unrecognisedCVM) {
        if (unrecognisedCVM) {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.charAt(0) + "1" + byte_1.substring(2);

            result[2] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.charAt(0) + "0" + byte_1.substring(2);

            result[2] = fromBinary(byte_1)[0];
        }
    }

    public void setPINTryLimitExceeded(boolean pinTryLimitExceeded) {
        if (pinTryLimitExceeded) {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.substring(0, 2) + "1" + byte_1.substring(4);

            result[2] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.substring(0, 2) + "0" + byte_1.substring(4);

            result[2] = fromBinary(byte_1)[2];
        }
    }

    public void setPINEntryRequiredAndPinPadNotPresentOrNotWorking(boolean pinEntryRequiredAndPinPadNotPresentOrNotWorking) {
        if (pinEntryRequiredAndPinPadNotPresentOrNotWorking) {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.substring(0, 3) + "1" + byte_1.substring(5);

            result[2] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 3) + "0" + byte_1.substring(5);

            result[2] = fromBinary(byte_1)[2];
        }
    }

    public void setPINEntryRequiredPINPadPresentButPinWasNotEntered(boolean pinEntryRequiredPINPadPresentButPinWasNotEntered) {
        if (pinEntryRequiredPINPadPresentButPinWasNotEntered) {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.substring(0, 4) + "1" + byte_1.substring(6);

            result[2] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 4) + "0" + byte_1.substring(6);

            result[2] = fromBinary(byte_1)[2];
        }
    }

    public void setOnlinePinEntered(boolean onlinePinEntered) {
        if (onlinePinEntered) {
            String byte_1 = toBinary(new byte[]{result[2]});
            byte_1 = byte_1.substring(0, 5) + "1" + byte_1.substring(7);

            result[2] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[1]});
            byte_1 = byte_1.substring(0, 5) + "0" + byte_1.substring(7);

            result[2] = fromBinary(byte_1)[2];
        }
    }

    public void setTransactionExceedsFloorLimit(boolean transactionExceedsFloorLimit) {
        if (transactionExceedsFloorLimit) {
            // 0xxxxxxx
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = "1" + byte_1.substring(1);

            result[3] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = "0" + byte_1.substring(1);

            result[3] = fromBinary(byte_1)[0];
        }
    }

    public void setLowerConsecutiveOfflineLimitExceed(boolean lowerConsecutiveOfflineLimitExceed) {
        if (lowerConsecutiveOfflineLimitExceed) {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.charAt(0) + "1" + byte_1.substring(2);

            result[3] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.charAt(0) + "0" + byte_1.substring(2);

            result[3] = fromBinary(byte_1)[0];
        }
    }

    public void setUpperConsecutiveOfflineLimitExceeded(boolean upperConsecutiveOfflineLimitExceeded) {
        if (upperConsecutiveOfflineLimitExceeded) {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.substring(0, 2) + "1" + byte_1.substring(4);

            result[3] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.substring(0, 2) + "0" + byte_1.substring(4);

            result[3] = fromBinary(byte_1)[2];
        }
    }

    public void setTransactionSelectedRandomyForOnlineProcessing(boolean transactionSelectedRandomyForOnlineProcessing) {
        if (transactionSelectedRandomyForOnlineProcessing) {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.substring(0, 3) + "1" + byte_1.substring(5);

            result[3] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.substring(0, 3) + "0" + byte_1.substring(5);

            result[3] = fromBinary(byte_1)[2];
        }
    }

    public void setMerchantForcedTransactionOnline(boolean merchantForcedTransactionOnline) {
        if (merchantForcedTransactionOnline) {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.substring(0, 4) + "1" + byte_1.substring(6);

            result[3] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[3]});
            byte_1 = byte_1.substring(0, 4) + "0" + byte_1.substring(6);

            result[3] = fromBinary(byte_1)[2];
        }
    }

    public void setDefaultTdolUsed(boolean defaultTdolUsed) {
        if (defaultTdolUsed) {
            // 0xxxxxxx
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = "1" + byte_1.substring(1);

            result[4] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = "0" + byte_1.substring(1);

            result[4] = fromBinary(byte_1)[0];
        }
    }

    public void setIssuerAuthentificationFailed(boolean issuerAuthentificationFailed) {
        if (issuerAuthentificationFailed) {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = byte_1.charAt(0) + "1" + byte_1.substring(2);

            result[4] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = byte_1.charAt(0) + "0" + byte_1.substring(2);

            result[4] = fromBinary(byte_1)[0];
        }
    }

    public void setScriptProcessingFailedBeforeFinalGenerateAC(boolean scriptProcessingFailedBeforeFinalGenerateAC) {
        if (scriptProcessingFailedBeforeFinalGenerateAC) {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = byte_1.substring(0, 2) + "1" + byte_1.substring(4);

            result[4] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = byte_1.substring(0, 2) + "0" + byte_1.substring(4);

            result[4] = fromBinary(byte_1)[2];
        }
    }

    public void setScriptProcessingFailedAfterFinalGenerateAC(boolean scriptProcessingFailedAfterFinalGenerateAC) {
        if (scriptProcessingFailedAfterFinalGenerateAC) {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = byte_1.substring(0, 3) + "1" + byte_1.substring(5);

            result[4] = fromBinary(byte_1)[0];
        } else {
            String byte_1 = toBinary(new byte[]{result[4]});
            byte_1 = byte_1.substring(0, 3) + "0" + byte_1.substring(5);

            result[4] = fromBinary(byte_1)[2];
        }
    }

    public byte[] getResult() {
        return result;
    }
}
