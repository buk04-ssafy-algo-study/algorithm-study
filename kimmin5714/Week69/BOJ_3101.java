import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3101 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Integer.parseInt(st.nextToken());
        long K = Integer.parseInt(st.nextToken());

        String path = br.readLine();

        long[] delr = {-1, 1, 0, 0};  // U, D, L, R
        long[] delc = {0, 0, -1, 1};

        long[] cur = {1, 1};
        long res = 1;

        for (char move : path.toCharArray()) {
            int dir = -1;
            switch (move) {
                case 'U': dir = 0; break;
                case 'D': dir = 1; break;
                case 'L': dir = 2; break;
                case 'R': dir = 3; break;
            }

            cur[0] += delr[dir];
            cur[1] += delc[dir];

            // 현재 대각선 번호 계산
            long lineNum = (cur[0] + cur[1] - 1);

            // 대각선 번호가 행렬의 크기 이하인 경우
            if (lineNum <= N) {
                long lineStart = (lineNum * (lineNum - 1)) / 2; // 대각선 시작 숫자
                if (lineNum % 2 == 0) { // 짝수 대각선
                    res += (lineStart + cur[0]);
                } else { // 홀수 대각선
                    res += (lineStart + (lineNum - (cur[0] - 1)));
                }
            } else {
                // 대각선 번호가 행렬 크기를 넘어선 경우
                long thisNum = (2 * N - lineNum);
                long lineStart = ((N * (N + 1)) / 2) + ((N * (N - 1)) / 2 - (thisNum + 1) * thisNum / 2); // 시작 숫자
                long subLocation = (cur[0] - (lineNum - N));

                if (lineNum % 2 == 0) { // 짝수 대각선
                    res += (lineStart + subLocation);
                } else { // 홀수 대각선
                    res += (lineStart + (thisNum - (subLocation - 1)));
                }
            }
        }

        System.out.print(res);
    }
}