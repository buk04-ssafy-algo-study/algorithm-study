package SSAFY.study.algo.week60s.week64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_25565_딸기와토마토 {

    static int N, M, K;
    static int[][] seeds;
    static int seedCount;

    public static void main(String[] args) throws IOException {
        init();
        calcAnswer();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        seedCount = 0; // 뿌린 씨앗의 수
        seeds = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                seeds[i][j] = Integer.parseInt(st.nextToken());
                if (seeds[i][j] == 1) seedCount++;
            }
        }
    }

    static void calcAnswer() {
        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};

        if (seedCount == K) caseSame();  // 씨앗 개수가 K개와 같다면 모두 겹치게 뿌렸음을 의미, 이걸 먼저 처리해줘야 함. 안그럼 K가 1일 때 밑의 조건이랑 겹칠 수 있음.
        else if (seedCount == 2*K-1) caseCross(dirRow, dirCol);  // 씨앗 개수가 2 * K - 1이라면 겹치는 부분이 한군데임을 의미
        else if (seedCount == 2*K) System.out.println(0);    // 씨앗 개수가 K * 2라면 겹치는 부분이 없음을 의미
        else caseOther(dirRow, dirCol);    // 씨앗이 세로 혹은 가로의 같은 줄에 모두가 아니라 일부분 겹쳐져 있는 경우
    }

    static void caseSame() {    // 같은 장소에 씨앗 심었을 경우 출력
        StringBuilder sb = new StringBuilder();
        sb.append(K).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (seeds[i][j] == 1) {
                    sb.append(i+1).append(" ").append(j+1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static void caseCross(int[] dirRow, int[] dirCol) {   // 교차로 심은 경우 출력 함수
        // 0 1 1 1 1 1 0
        StringBuilder sb = new StringBuilder();
        sb.append(1).append("\n");

        int[] lastAxis = new int[2];    // 만약 교차가 아니라 한줄로 쭉 뿌렸는데 한 군데 겹치는 경우라면 이 좌표 이용해서 겹치는 부분 구해야함
        for (int i = 0; i < N; i++) {   // 모든 좌표 탐색해서 겹치는 부분 찾기
            for (int j = 0; j < M; j++) {
                if (seeds[i][j] == 0) continue;

                lastAxis = new int[] {i, j};
                int rowCount = 0;   // 같은 행에 있는 씨앗 개수
                int colCount = 0;   // 같은 열에 있는 씨앗 개수
                int nextRow, nextCol;
                for (int d = 0; d < 4; d++) {
                    nextRow = i + dirRow[d];
                    nextCol = j + dirCol[d];
                    if (isOuted(nextRow, nextCol)) continue;
                    if (seeds[nextRow][nextCol] == 1 && d%2 == 0) colCount++;   // 같은 열에 있는 씨앗 카운트+1
                    else if (seeds[nextRow][nextCol] == 1 && d%2 == 1) rowCount++;  // 같은 행에 있는 씨앗 카운트+1
                }
                if (rowCount >= 1 && colCount >= 1) {
                    sb.append((i+1) + " " + (j+1));
                    System.out.println(sb);
                    return;
                }
            }
        }

        for (int d = 0; d < 4; d++) {   // 교차하는 게 아닌 경우, 겹치는 한 군데를 구해야 함
            if (isOuted(lastAxis[0] + dirRow[d], lastAxis[1] + dirCol[d]) || seeds[lastAxis[0] + dirRow[d]][lastAxis[1] + dirCol[d]] == 0) continue;

            lastAxis[0] += (K-1) * dirRow[d];
            lastAxis[1] += (K-1) * dirCol[d];
        }
        sb.append((lastAxis[0]+1) + " " + (lastAxis[1]+1));
        System.out.println(sb);
    }

    static void caseOther(int[] dirRow, int[] dirCol) {   // 한 줄로 겹치는데, 모두 다가 아니라 일부분만 겹칠 경우
        StringBuilder sb = new StringBuilder();
        sb.append((K*2) - seedCount).append("\n");
        int[] startAxis = new int[2];   // 겹치는 부분이 시작하는 좌표
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (seeds[i][j] == 1) { // 일단 제일 처음 씨앗뿌린 부분 저장하고 반복문 종료
                    startAxis = new int[] {i, j};
                    break;
                }
            }
        }

        for (int d = 0; d < 4; d++) {
            if (isOuted(startAxis[0] + dirRow[d], startAxis[1] + dirCol[d]) || seeds[startAxis[0] + dirRow[d]][startAxis[1] + dirCol[d]] == 0) continue;

            startAxis[0] += (seedCount - K) * dirRow[d];  // 겹치는 부분의 시작까지 이동
            startAxis[1] += (seedCount - K) * dirCol[d];
            for (int i = 0; i < (K*2) - seedCount; i++) {
                sb.append((startAxis[0] + 1) + " " + (startAxis[1] + 1)).append("\n");
                startAxis[0] += dirRow[d];
                startAxis[1] += dirCol[d];
            }
            break;
        }
        System.out.println(sb);

        // 1 8 4, 6
        // 0 1 1 1 1 1 1 0
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
