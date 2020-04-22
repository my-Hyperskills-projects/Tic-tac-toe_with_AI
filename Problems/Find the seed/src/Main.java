import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int minOfMax = k;
        int num = -1;

        for (int i = a; i <= b; i++) {
            int max = 0;
            Random random = new Random(i);
            for (int j = 0; j < n; j++) {
                int randomNum = random.nextInt(k);
                if (randomNum > max) max = randomNum;
            }
            if (max < minOfMax) {
                minOfMax = max;
                num = i;
            }
        }

        System.out.println(num);
        System.out.println(minOfMax);
    }
}