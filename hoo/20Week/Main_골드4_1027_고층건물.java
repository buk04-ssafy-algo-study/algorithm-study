package SSAFY.study.algo.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드4_1027_고층건물 {

    static int N;
    static int[] buildings;
    static int maxSee;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            maxSee = Math.max(maxSee, countCanSee(i));
        }
        System.out.println(maxSee);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) buildings[i] = Integer.parseInt(st.nextToken());
        maxSee = 0;
    }

    static int countCanSee(int buildingNumber) { // i번째 건물에서 볼 수 있는 건물 수 카운트
        int count = 0;
        double incline = 0.0;  // 비교 대상 기울기를 저장하기 위한 변수

        for (int i = buildingNumber-1; i >= 0; i--) {   // 빌딩의 왼쪽을 보며 기울기가 감소하는 건물의 개수 카운트
            double tempIncline = 1.0 * (buildings[buildingNumber] - buildings[i]) / (buildingNumber - i);  // 1.0을 곱해주어 double로 타입캐스팅
            if (i == buildingNumber-1 || tempIncline < incline) {
                count += 1;
                incline = tempIncline;
            }
        }

        for (int i = buildingNumber+1; i < N; i++) {  // 빌딩의 오른쪽을 보며 기울기가 증가하는 건물의 개수 카운트
            double tempIncline = 1.0 * (buildings[buildingNumber] - buildings[i]) / (buildingNumber - i);
            if (i == buildingNumber+1 || tempIncline > incline) {
                count += 1;
                incline = tempIncline;
            }
        }

        return count;
    }

}
