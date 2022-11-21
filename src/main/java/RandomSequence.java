import java.util.Random;

public class RandomSequence {
    private static final Random random = new Random();

    public static String getRandomNumberSequence(int sequenceLength) {
        StringBuilder stringBuilder = new StringBuilder();
        int min = 0;
        int max = 9;
        for (int i = 0; i < sequenceLength; i++) {
            stringBuilder.append(random.nextInt(max - min + 1) + min);
        }
        return stringBuilder.toString();
    }
}
