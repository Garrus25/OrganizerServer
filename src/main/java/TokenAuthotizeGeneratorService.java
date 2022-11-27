import java.util.Random;

public class TokenAuthotizeGeneratorService {
    private static final Random random = new Random();

    private static final int MIN_DIGIT = 0;
    private static final int MAX_DIGIT = 9;


    public static int getRandomNumberFromTo(int from,int to){
        return random.nextInt(to - from + 1) + from;
    }

    public static String getRandomNumberSequence(int sequenceLength) {
        StringBuilder stringBuilder = new StringBuilder();
        int min = MIN_DIGIT;
        int max = MAX_DIGIT;

        for (int i = 0; i < sequenceLength; i++) {
            int randomDigit=getRandomNumberFromTo(min,max);
            stringBuilder.append(randomDigit);
        }
        return stringBuilder.toString();
    }

    public static String createTokenAuthorizeUser(){
        String token = TokenAuthotizeGeneratorService.getRandomNumberSequence(6);
        return token;
    }
}
