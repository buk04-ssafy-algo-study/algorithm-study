package SSAFY.study.algo.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드2_27450_플래그대사그만좀말해요 {

    private static long totalYellCount;	// 소리친 횟수
    private static int hanbyulIdx;	// 한별이 위치

    private static int N;
    private static int K;
    private static long[] enemies;	// 적들의 강함
    private static long[] enemiesGoal;	// 적들의 목표 강함

    public static void main(String[] args) throws Exception {
        init();
        doFlagScript();
        System.out.println(totalYellCount);
    }

    private static void init() throws Exception {
        totalYellCount = 0;
        hanbyulIdx = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        enemies = new long[N];
        enemiesGoal = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            enemies[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            enemiesGoal[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void doFlagScript() {
        while (hanbyulIdx < N) {	// 끝 적한테 갈 때까지
            long enemy = enemies[hanbyulIdx];	// 현재 한별이가 위치한 곳의 적
            long enemyGoal = enemiesGoal[hanbyulIdx];	// 그 적의 목표 세기
            if (enemy >= enemyGoal) {	// 그 위치의 적이 목표 세기를 달성했다면 다음 적으로
                hanbyulIdx += 1;
                continue;
            }
            yell();
        }
    }

    private static void yell() {	// 소리 지르기
        int yellIdx = hanbyulIdx;	// 목소리의 위치
        int yellStrong = K;	// 처음 목소리의 세기

        long haveToYellCount = enemiesGoal[hanbyulIdx] - enemies[hanbyulIdx];
        if (haveToYellCount <= 0) return;   // 해당 적이 이미 목표 이상의 세기를 가진 경우 함수 종료
        long yellCount = haveToYellCount / K;	// 소리치는 첫 위치에서 소리쳐야하는 횟수
        if (yellCount == 0) yellCount = 1;	// 목표까지 남은 강함이 한별이 목소리보다 작을 수도 있음 그럴 때는 1로

        while (yellStrong > 0) {	// 목소리 0되기 전까지
            if (yellIdx >= N) break;
            enemies[yellIdx] += yellStrong * yellCount;

            yellIdx += 1;
            yellStrong -= 1;
        }
        totalYellCount += yellCount;
    }

}
