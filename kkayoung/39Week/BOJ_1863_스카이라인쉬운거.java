// https://www.acmicpc.net/problem/1863
import java.io.*;
import java.util.*;

public class Main {

    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int answer = 0;
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            if(y==0) {
                answer += stack.size();
                stack.clear();
                continue;
            }
            if(stack.size()==0) {
                stack.push(y);
            } else {
                while(!stack.isEmpty() && stack.peek()>y) {
                    stack.pop();
                    answer++;
                }
                if(stack.isEmpty() || stack.peek() != y) {
                    stack.push(y);
                }
            }
        }

        while(!stack.isEmpty()) {
            stack.pop();
            answer++;
        }
     
        System.out.println(answer);
    }
    
}
