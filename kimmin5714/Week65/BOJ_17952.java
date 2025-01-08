import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
class Work {
    int score, time;

    public Work(int score, int time) {
        this.score = score;
        this.time = time;
    }
}
public class BOJ_17952 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int res = 0;
        Stack<Work> stack = new Stack<>();
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int isWork = Integer.parseInt(st.nextToken());
            switch (isWork) {
                case 1:
                    int A = Integer.parseInt(st.nextToken());
                    int T = Integer.parseInt(st.nextToken());

                    if(--T == 0) { // 1분 걸리는 과제인 경우
                        res += A; // 스택에 넣지 않고 점수 획득
                        break;
                    } else {
                        stack.push(new Work(A,T)); // 1분 이상 걸리는 과제인 경우 스택에 넣기
                    }
                    break;
                case 0:
                    if(!stack.isEmpty() && --stack.peek().time == 0) { // 과제가 없을 경우
                        res += stack.pop().score; // 가장 마지막에 받은 과제 수행
                    }
                    break;
            }
        }
        System.out.print(res);
    }
}
