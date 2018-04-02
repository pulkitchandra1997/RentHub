package appp.renthub;

import java.security.SecureRandom;

/**
 * Created by PULKITCHANDRA on 02-Apr-18.
 */

public class OTP_GENERATION {

    public static int generateRandomNumber() {
        int randomNumber;
        int length=4;
        int range=9;
        SecureRandom secureRandom = new SecureRandom();
        String s = "";
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0) { // to prevent the Zero to be the first number as then it will reduce the length of generated pin to three or even more if the second or third number came as zeros
                i = -1;
                continue;
            }
            s = s + number;
        }

        randomNumber = Integer.parseInt(s);

        return randomNumber;
    }
}
