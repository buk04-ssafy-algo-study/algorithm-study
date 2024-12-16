import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1913 {
    private static int N, dir, resR, resC, find, pr, pc, num = 2, repeat = 1;
    private static int[] delr = {-1, 0, 1, 0}, delc = {0, 1, 0, -1}; // 상우하좌 순서로 진행됨
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        st = new StringTokenizer(br.readLine());
        find = Integer.parseInt(st.nextToken());
        pr = N / 2;
        pc = N / 2;

        arr[pr][pc] = 1; // 시작 좌표 지정
        if (find == 1) { // 1을 찾는 경우
            resR = pr + 1;
            resC = pc + 1;
        }

        // 달팽이 모양으로 숫자 삽입
        while (num < N * N) {
            insertNum();
            if (num > N * N) break;
            insertNum();
            repeat++; // 반복 횟수 증가
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(arr[i][j] + " ");
            sb.append("\n");
        }

        sb.append(resR + " " + resC);
        System.out.print(sb);
    }

    private static void insertNum() {
        for (int i = 0; i < repeat; i++) { // 반복 횟수만큼 같은 방향으로 숫자 넣어주기
            pr += delr[dir];
            pc += delc[dir];
            if (num == find) { // 찾는 수인 경우 저장해두기
                resR = pr + 1;
                resC = pc + 1;
            }
            arr[pr][pc] = num++;
            if (num > N * N) break;
        }
        dir = (dir + 1) % 4; // 방향 바꾸기
    }
}
