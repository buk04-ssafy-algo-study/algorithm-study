package SSAFY.study.algo.week60s.week69;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_3101_토끼의이동 {

    static int N, K;
    static String jumps;

    public static void main(String[] args) throws IOException {
        init();
        if (N == 1) System.out.println(1);
        else doJump();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jumps = br.readLine();
    }

    static void doJump() {
        long sum = 1L;   // 토끼가 방문한 칸의 값 합, 첫 번째 칸이 1이므로 1이 초기값임
        Map<Character, int[]> jumpMap = makeJumpMap();
        long[] startNumbers = makeStartNumbers();

        int[] now = new int[] {0, 0};    // 처음 토끼의 위치로 초기화
        int[] dirArr;
        int nextRow, nextCol;
        for (int i = 0; i < K; i++) {   // 토끼의 점프마다 수행
            dirArr = jumpMap.get(jumps.charAt(i));
            nextRow = now[0] + dirArr[0];
            nextCol = now[1] + dirArr[1];
            sum += calcAxisValue(startNumbers, nextRow, nextCol);

            now[0] = nextRow;   // 좌표 갱신
            now[1] = nextCol;
        }

        System.out.println(sum);
    }

    static Map<Character, int[]> makeJumpMap() {
        Map<Character, int[]> jumpMap = new HashMap<>();
        jumpMap.put('U', new int[] {-1, 0});    // 상 우 하 좌
        jumpMap.put('R', new int[] {0, 1});
        jumpMap.put('D', new int[] {1, 0});
        jumpMap.put('L', new int[] {0, -1});

        return jumpMap;
    }

    static long[] makeStartNumbers() {  // 각 대각선 별 시작 번호
        long[] startNumbers = new long[2*N-1];
        startNumbers[0] = 1L;

        long nowNumber = 1L;
        long diff = 1L;
        for (int i = 1; i < 2*N-1; i++) {
            nowNumber += diff;
            startNumbers[i] = nowNumber;
            if (i < N) diff++;
            else diff--;
        }

        return startNumbers;
    }

    static long calcAxisValue(long[] startNumbers, int row, int col) {   // 해당 좌표의 값 계산
        // row + col이 몇이냐에 따라 정해짐.
        // row+col이 N 이하일 때는 각 대각선의 시작 숫자가 계차 수열 일반항이 (row+col)인 수열을 이룸. ( 수열 일반항 : ( ((row+col)+1)(row+col) + 2 )/2 )
        // row+col이 N 초과일 떄는 각 대각선의 시작 숫자가 계차 수열 일반항이 2N-(row+col)인 수열을 이룸. (수열 일반항 :
        // 수열 일반항은 머리 터질 거 같아서 걍 대각선 개수만큼 대각선 시작숫자 만들어줌 => startNumbers 배열 만들어 주는 걸로 대체
        long axisValue = startNumbers[row+col]; // 일단 해당 대각선의 시작 숫자 가져옴
        axisValue += ((row+col)%2 == 1)? (long)row:(long)col;      // 그 후 홀수 번째 대각선인 지 짝수 번째 대각선인 지에 따라 row 또는 col값 더해줌
        axisValue -= Math.max(0L, (long) (row+col)-N+1);    // 마지막으로 만약 N번째 대각선 이후의 값인 경우, 실제로는 row나 col 값보다 적게 더해져야 하므로 줄어든 대각선 크기만큼 값을 빼줌

        return axisValue;
    }

}
