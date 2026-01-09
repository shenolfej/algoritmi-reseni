import java.util.Scanner;

public class TretaZadaca{
    public static int findIndexOfLargestAverage(double[] arr, int n){
        double max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for(int i = 0; i<n; i++){
            if(arr[i] > max){
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static void deleteD(double[] arr, int n, int i){
        for(int j = i; j<n-1; j++){
            arr[j] = arr[j+1];
        }
    }
    public static void deleteI(int[] arr, int n, int i){
        for(int j = i; j<n-1; j++){
            arr[j] = arr[j+1];
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] times = new int[n];
        int[] earnings = new int[n];
        double[] average = new double[n];
        for(int i = 0; i<n;i++){
            times[i] = scan.nextInt();
            earnings[i] = scan.nextInt();
            average[i] = (double)earnings[i]/times[i];
        }
        int maxTime = 40, totalTime = 0, totalEarnings = 0;
        while(totalTime < maxTime && n>0){
            int index = findIndexOfLargestAverage(average, n);
            if(totalTime + times[index] < 40){
                totalEarnings += earnings[index];
                totalTime += times[index];
            }
            else{
                int HoursToWork = maxTime - totalTime;
                totalEarnings += (int) (average[index] * HoursToWork);
                totalTime += HoursToWork;
            }
            deleteD(average, n, index);
            deleteI(earnings, n, index);
            deleteI(times, n, index);
            n--;
        }
        System.out.println(totalEarnings);
    }
}