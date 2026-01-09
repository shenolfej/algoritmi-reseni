import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TretaZadaca {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        int MinTotal = 0, MaxTotal = 0;
        if(M > N) {
            int childrenPer = M / N, childrenExtra = M % N;
            boolean flag = true;
            for (i = 0; i < N; i++) {
                if (flag) {
                    MinTotal += (childrenPer + childrenExtra) * 100;
                    flag = false;
                } else MinTotal += (childrenPer) * 100;
            }
        }
        else{
            MinTotal = N * 100;
        }
        if(M>0) {
            MaxTotal = ((M - 1) * 100) + N * 100;
        }
        else MaxTotal = N*100;
        System.out.println(MinTotal);
        System.out.println(MaxTotal);
    }

}
