package ma.querdos;

/**
 * @author : Querdos
 * @date: 24/07/15
 *
 * Copyright to Hamza ESSAYEGH - Querdos
 *
 * This class represents the controller, linking the Model
 * with all classes (like sending SELECT commands etc...)
 * and the views of the Android Smartphone
 */
public class EMVProcess {
    public static void main(String[] args) {
        String tag = "4F";
        String responseMessage = "4F 08 A0 00 00 00 04 10 10 02".replace(" ", "");

        int index = responseMessage.lastIndexOf(tag) + tag.length();
        int length = Integer.parseInt(responseMessage.substring(index, index + 2), 16) * 2;

        System.out.println(responseMessage.substring(index + 2, index+2+length));
    }
}
