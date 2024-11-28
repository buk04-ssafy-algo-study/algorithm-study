import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_22866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] h = new int[N + 1]; // 건물 높이
        int[] cnt = new int[N + 1]; // 볼 수 있는 건물 갯수
        int[] near = new int[N + 1]; // 가장 가까운 건물
        Stack<Integer> stack = new Stack<>();

        for(int i=1; i<=N; i++){
            h[i] = Integer.parseInt(st.nextToken());
            near[i] = -100000;
        }

        // 왼쪽 건물들 중 볼 수 있는 건물
        for(int i=1; i<=N; i++){
            while(!stack.isEmpty() && h[stack.peek()] <= h[i]) // 현재 건물보다 높은 건물이 나올 때까지 pop
                stack.pop();

            cnt[i] = stack.size(); // 현재 건물보다 큰 건물의 수
            if(cnt[i] > 0) near[i] = stack.peek(); // 가장 마지막에 넣은 것이 가장 가까운 건물
            stack.push(i);
        }

        // 오른쪽 건물들 중 볼 수 있는 건물
        stack = new Stack<>();
        for(int i=N; i>0; i--){
            while(!stack.isEmpty() && h[stack.peek()] <= h[i]) // i보다 높은 건물이 나올 때까지 pop
                stack.pop();

            int s = stack.size(); // 현재 건물보다 큰 건물의 수
            cnt[i] += s;
            if(s > 0 && stack.peek()-i < i-near[i]) // 오른쪽에 가장 가까운 건물이 있다면 갱신
                near[i] = stack.peek();
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(cnt[i]);
            if(cnt[i] > 0)
                sb.append(" ").append(near[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}