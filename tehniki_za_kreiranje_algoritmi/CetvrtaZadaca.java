import java.util.Scanner;
import java.util.Arrays;



public class CetvrtaZadaca {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arrival = new int[n];
        int[] departure = new int[n];

        for (int i = 0; i < n; i++) {
            arrival[i] = scan.nextInt();
            departure[i] = scan.nextInt();
        }

        Arrays.sort(arrival);
        Arrays.sort(departure);

        int platforms = 0, maxPlatforms = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (arrival[i] <= departure[j]) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }

        System.out.println(maxPlatforms);
    }
}
