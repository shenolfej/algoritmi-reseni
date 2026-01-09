

import java.util.Arrays;
import java.util.Scanner;

public class ShestaZadaca {
    public static int Substrings(String s){
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '1' ? 1 : -1;
        for(int i = 1; i<s.length(); i++){
            int value = (s.charAt(i) == '1' ? 1 : -1);
            dp[i] = dp[i-1] + value;
        }
        int totalCount = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int currentSum = dp[j] - (i > 0 ? dp[i - 1] : 0);
                if (currentSum > 0) {
                    totalCount++;
                }
            }
        }
        return totalCount;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        String s = scan.nextLine();
        int m = Substrings(s);
        System.out.println(m);
    }
}