import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ShestaZadaca {
    public static int count(int N){
        Queue<Integer> iterating = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        int totalShuffles = 0;
        for(int i = 1; i<=51; i++) iterating.offer(i);

        while(iterating.peek() != N){
            for(int i = 0; i<7 && !iterating.isEmpty(); i++){
                stack.push(iterating.poll());
            }
            while(!stack.isEmpty()){
                iterating.offer(stack.pop());
                int toBack = iterating.poll();
                iterating.offer(toBack);
            }
            totalShuffles++;
        }
        return totalShuffles;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
