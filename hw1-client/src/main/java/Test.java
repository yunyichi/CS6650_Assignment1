
import java.util.Random;

public class Test {
    private static final String[] leftOrRight = new String[]{"left", "right"};
    public static void main(String[] args) {

            Random rand = new Random();
            System.out.println(leftOrRight[rand.nextInt(2)]);

    }
}
