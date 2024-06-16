import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        int n = Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //System.out.println("Y : "+y);

            while (!stack.empty() && stack.peek()>y) {
                stack.pop();
                cnt++;
            }
            if(!stack.empty() && stack.peek()==y){
                continue;
            }
            stack.push(y);
        }
        //System.out.println(stack);
        while(!stack.empty()){
            if(stack.peek()>0) cnt++;
            stack.pop();
        }
        System.out.println(cnt);
    }
}
