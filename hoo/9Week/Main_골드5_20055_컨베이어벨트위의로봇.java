package SSAFY.study.algo.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드5_20055_컨베이어벨트위의로봇 {

    private static class Robot {    // 로봇 객체
        int idx = 0;    // 로봇의 위치, 항상 0부터 시작

        public void move() {    // 로봇 한 칸 이동하는 함수
            this.idx += 1;
        }

        @Override
        public String toString() {
            return "위치: " + this.idx;
        }
    }

    private static int stage;   // 종료 시 단계
    private static int zeroCount;   // 내구도가 0인 칸의 개수
    private static int N;   // 벨트의 길이
    private static int K;   // 종료 조건: 내구도가 0인 칸이 K개 이상
    private static int[] durability;    // 1 ~ 2N 칸 별 내구도
    private static List<Robot> Robots;  // 칸 위에 있는 로봇들의 정보를 담을 리스트

    public static void main(String[] args) throws Exception {
        init();
        while (zeroCount < K) {
            stage += 1;
            rotateBelt();
            moveRobot();
            onboardRobot();
        }
        System.out.println(stage);

    }

    private static void init() throws Exception {
        stage = 0;
        zeroCount = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        durability = new int[2*N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        Robots = new ArrayList<>();
    }

    private static void rotateBelt() {  // 1. 컨베이어 벨트 한 칸 회전(로봇과 함께)
        int[] tempDurability = new int[durability.length];
        for (int i = 0; i < durability.length; i++) {
            tempDurability[(i+1) % durability.length] = durability[i];
        }
        durability = tempDurability;

        for (int i = Robots.size()-1; i >= 0; i--) {  // 로봇을 리스트에서 빼줄 경우가 있으므로 뒤에서부터 탐색
            Robot robot = Robots.get(i);
            robot.move();   // 로봇 한 칸 이동
            if (robot.idx == N-1) Robots.remove(i); // 내리는 위치에 도달한 로봇은 내려줌
        }
    }

    private static void moveRobot() {   // 2. 로봇을 움직임
        for (int i = Robots.size()-1; i >= 0; i--) {  // 로봇을 리스트에서 빼줄 경우가 있으므로 뒤에서부터 탐색
            Robot robot = Robots.get(i);
            int nextRobotIdx = robot.idx + 1;   // 로봇이 움직일 칸
            if (i != Robots.size()-1 && Robots.get(i+1).idx == nextRobotIdx) continue;    // 조건 1: 움직일 칸에 로봇이 있다면 해당 로봇은 움직이지 못함
            else if (durability[nextRobotIdx] == 0) continue;    // 조건 2: 움직일 칸의 내구도가 0이면 움직이지 못함

            durability[nextRobotIdx] -= 1;   // 로봇이 이동한 칸의 내구도 -1
            countZero(nextRobotIdx);
            robot.move();

            if (robot.idx == N-1) Robots.remove(i); // 내리는 위치에 도달한 로봇은 내려줌
        }
    }

    private static void onboardRobot() {    // 3. 로봇을 올리는 칸(idx = 0)에 올림
        if (Robots.size() != 0 && Robots.get(0).idx == 0) return; // 조건 1: 0번째 칸에 로봇이 있으면 로봇을 올리지 못함
        if (durability[0] == 0) return; // 조건 2: 올리는 칸의 내구도가 0이면 로봇을 올리지 못함

        durability[0] -= 1; // 올리는 칸 내구도 -1
        countZero(0);
        Robots.add(0, new Robot());
    }

    private static void countZero(int idx) {    // 해당 칸의 내구도가 0이면 zeroCount 증가
        if (durability[idx] == 0) zeroCount += 1;
    }

}
