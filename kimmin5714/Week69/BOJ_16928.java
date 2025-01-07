import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[101];

        for(int i=1;i<=100;i++) arr[i] = i; // 1 ~ 100칸

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x] = y; // 사다리로 이동
        }
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u] = v; // 뱀 만나 이동
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] visited = new int[101]; // 주사위 몇 번 던져서 도달했는지
        q.offer(1); // 1번에서 시작
        visited[1] = 0;

        while(!q.isEmpty()) {
            int curNum = q.poll(); // 현재 번호
            int curCnt = visited[curNum]; // 현재 주사위 몇 번 던졌는지

            for(int i=1;i<=6;i++) { // 주사위 던져서 갈 수 있는 경우 +1 ~ +6
                int newNum = curNum + i;
                if(newNum > 100 || visited[arr[newNum]] != 0) continue; // 이미 갔거나 100 넘은 경우

                if(arr[newNum] == 100) { // 다음 칸이 100일 수 있으면 출력 후 종료
                    System.out.print(curCnt + 1);
                    return;
                }
                q.offer(arr[newNum]); // 다음 칸 큐에 넣기
                visited[arr[newNum]] = curCnt + 1; // 방문 처리 (주사위 던진 횟수)
            }
        }
    }
}