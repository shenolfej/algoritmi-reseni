import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrvaZadaca {

    static void oddEvenSort(int a[], int n)
    {
        int[] arrOdd = new int[n];
        int[] arrEven = new int[n];
        int j = 0, k = 0;
        for(int i = 0; i<n; i++){
            if(a[i] % 2 == 0){
                arrEven[j++] = a[i];
            }
            else {
                arrOdd[k++] = a[i];
            }
        }
        for(int i = 0; i<k; i++){
            for(int l = 0; l<k-1; l++){
                if(arrOdd[l] > arrOdd[l+1]){
                    int tmp = arrOdd[l];
                    arrOdd[l] = arrOdd[l+1];
                    arrOdd[l+1] = tmp;
                }
            }

        }
        for(int i = 0; i<j; i++){
            for(int l = 0; l<j-1; l++){
                if(arrEven[l] < arrEven[l+1]){
                    int tmp = arrEven[l];
                    arrEven[l] = arrEven[l+1];
                    arrEven[l+1] = tmp;
                }
            }
        }
        for(int i = 0, m = 0, l = 0; i<n; i++){
            if(i<k){
                a[i] = arrOdd[m++];
            }
            else a[i] = arrEven[l++];
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}
