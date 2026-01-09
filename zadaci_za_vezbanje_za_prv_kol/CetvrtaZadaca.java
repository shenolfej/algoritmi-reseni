import java.util.Arrays;
import java.util.Scanner;


public class CetvrtaZadaca {


    private static int najdolgaOpagackaSekvenca(int[] a) {
        int len_dp = a.length;
        int[] dp = new int[len_dp];
        Arrays.fill(dp, 1);
        for(int i = 1; i<len_dp; i++){
            for(int j = 0; j<i; j++){
                if(a[j] > a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = -1;
        for(int i = 0; i<len_dp; i++){
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
