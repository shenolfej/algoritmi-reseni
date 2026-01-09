import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PettaZadaca {

    public static int evaluateExpression(String expression){
        Stack<Character> operations = new Stack<>();
        Stack<Integer> numbers = new Stack<>();
        int num = 0, length = expression.length();
        for(int i = 0; i < length; i++){
            char character = expression.charAt(i);
            if(character >= '0' && character <='9') {
                num = num * 10 + (character - '0');
                if(i == length - 1){
                    if(!operations.isEmpty() && operations.peek() == '*'){
                        num = num * numbers.pop();
                        numbers.push(num);
                        operations.pop();
                    }
                    else{
                        numbers.push(num);
                    }
                }
                else if(expression.charAt(i+1) == '*' || expression.charAt(i+1) == '+'){
                    numbers.push(num);
                    num = 0;
                }
            }
            else{
                if(!operations.isEmpty() && operations.peek() == '*'){
                    int num1 = numbers.pop();
                    int pushNum = num1 * numbers.pop();
                    numbers.push(pushNum);
                    operations.pop();
                }
                operations.push(character);
            }
        }
        int total = numbers.pop();
        while(!numbers.isEmpty()){
            total += numbers.pop();
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}