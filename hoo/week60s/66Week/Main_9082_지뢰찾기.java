package SSAFY.study.algo.week60s.week66;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_9082_지뢰찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(findMine(br)).append("\n");
        }
        System.out.println(sb);
    }

    static int findMine(BufferedReader br) throws IOException {
        int[] map = initMap(br);

        int answer = 0;
        for (int i = 0; i < map.length; i++) {
            if ( i == 0 && (map[i] != 0 && map[i+1] != 0) ) {   // 제일 첫 칸인 경우, 현재 칸과 오른쪽 칸의 지뢰 숫자가 1이상이면 지뢰 확정적 개수 +1
                map[i]--;
                map[i+1]--;
                answer++;
            } else if ( i == map.length-1 && (map[i-1] != 0 && map[i] != 0) ) {  // 제일 마지막 칸인 경우, 현재 칸과 왼쪽 칸의 지뢰 숫자가 1이상이면 지뢰 확정적 개수 +1
                map[i-1]--;
                map[i]--;
                answer++;
            } else if ( (1 <= i && i < map.length-1) && (map[i-1] != 0 && map[i] != 0 && map[i+1] != 0) ) {    // 그 외 다른 칸들, 좌 바로 아래 우를 모두 살펴서 모든 칸 지뢰 개수 1이상이면 지뢰 확정적 개수 +1
                map[i-1]--;
                map[i]--;
                map[i+1]--;
                answer++;
            }
        }

        return answer;
    }

    static int[] initMap(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N];  // map의 0에는 숫자, 1에는 문자 저장
        String inputRow = br.readLine();
        for (int i = 0; i < N; i++) map[i] = Integer.parseInt(String.valueOf(inputRow.charAt(i)));
        inputRow = br.readLine();   // 다음 줄은 필요 없어서 입력 받기만 하고 방치

        return map;
    }

}
