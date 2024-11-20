package SSAFY.study.algo.week60s.week62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_22866_탑보기 {

    static int N;
    static int[] buildings;
    static int[] canSee;
    static int[] minCanSeeIndex;

    public static void main(String[] args) throws IOException {
        init();
        countCanSee();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N + 1]; // 인덱스를 1부터 사용
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) buildings[i] = Integer.parseInt(st.nextToken());
        canSee = new int[N + 1];    // 각 빌딩이 볼 수 있는 빌딩 수 저장할 배열
        minCanSeeIndex = new int[N + 1];    // 볼 수 있는 빌딩들 중 인덱스가 가장 작은 빌딩 저장할 배열
        Arrays.fill(minCanSeeIndex, -100_000); // 초기값 변경
    }

    static void countCanSee() {
        Stack<Integer> canSeeLeftStack = new Stack<>(); // 현재 시점에서 볼 수 있는 왼쪽 빌딩들 인덱스 저장한 스택
        Stack<Integer> canSeeRightStack = new Stack<>(); // 볼 수 있는 오른쪽 빌딩들 인덱스 저장한 스택

        for (int i = 1; i <= N; i++) {
            canSeeLeftStack = countCanSeeAtNowBuilding(canSeeLeftStack, i, true);
        }
        for (int i = N; i >= 1; i--) {
            canSeeRightStack = countCanSeeAtNowBuilding(canSeeRightStack, i, false);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (canSee[i] == 0) sb.append(0).append("\n");
            else {
                sb.append(canSee[i]).append(" ").append(minCanSeeIndex[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static Stack<Integer> countCanSeeAtNowBuilding(Stack<Integer> canSeeStack, int index, boolean isLeft) {
        while (!canSeeStack.isEmpty() && buildings[canSeeStack.peek()] <= buildings[index]) {
            canSeeStack.pop(); // 현재 빌딩보다 높이가 낮거나 같은 빌딩들은 제거
        }
        canSee[index] += canSeeStack.size(); // 현재 스택 내에 있는 빌딩 개수만큼 볼 수 있음을 표시
        if (!canSeeStack.isEmpty()) {
            int closestBuilding = canSeeStack.peek();
            if (minCanSeeIndex[index] == -100_000 || Math.abs(closestBuilding - index) < Math.abs(minCanSeeIndex[index] - index)) {
                minCanSeeIndex[index] = closestBuilding; // 볼 수 있는 빌딩 중 가장 가까운 빌딩 번호 갱신
            }
        }
        canSeeStack.push(index); // 스택에 현재 빌딩 추가
        return canSeeStack;
    }

}
