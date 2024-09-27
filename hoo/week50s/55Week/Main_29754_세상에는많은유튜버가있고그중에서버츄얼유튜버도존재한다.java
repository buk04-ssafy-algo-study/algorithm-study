package SSAFY.study.algo.week50s.week55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_29754_세상에는많은유튜버가있고그중에서버츄얼유튜버도존재한다 {

    static int N, M;
    static String[][] streamArray;  // 버츄얼 유튜버들의 방송들
    static Map<String, Integer> streamCountMap; // 일주일 간 스트림을 한 횟수를 저장하는 맵
    static Map<String, Integer> streamHourMap; // 일주일 간 스트림을 한 시간을 저장하는 맵
    static Map<String, Boolean> realVirtualYoutuberCheckMap;  // 해당 스트리머가 진짜 버츄얼 유튜버인지 여부 저장하는 맵


    public static void main(String[] args) throws IOException {
        init();
        findRealVirtualYoutuber();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        streamArray = new String[N][4];
        streamCountMap = new HashMap<>();
        streamHourMap = new HashMap<>();
        realVirtualYoutuberCheckMap = new HashMap<>();
        String[] stream;
        for (int i = 0; i < N; i++) {
            stream = br.readLine().split(" ");
            stream[2] = convertTime(stream[2]);
            stream[3] = convertTime(stream[3]);
            streamArray[i] = stream;
            if (!streamCountMap.containsKey(stream[0])) {   // 각 맵에 스트리머의 이름과 값 저장
                streamCountMap.put(stream[0], 0);
                streamHourMap.put(stream[0], 0);
                realVirtualYoutuberCheckMap.put(stream[0], true);    // 초기 상태는 진짜 버츄얼 유튜버라고 저장
            }
        }
        Arrays.sort(streamArray, new Comparator<String[]>() {   // 주어진 방송들은 날짜 순으로 정렬되어있지 않음. 방송들을 날짜 오름차순으로 정렬
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
            }
        });
    }

    static String convertTime(String time) {    // 주어진 문자열 시간을 분으로 치환
        int convertedTime = 0;
        convertedTime += Integer.parseInt(time.substring(0, 2)) * 60;
        convertedTime += Integer.parseInt(time.substring(3));

        return String.valueOf(convertedTime);
    }

    static void findRealVirtualYoutuber() {
        int day = 1;    // 날짜를 저장하는 변수
        int streamIndex = 0;    // 방송 배열의 인덱스 저장하는 변수
        while (day <= M) {  // 성식이가 마지막으로 방송을 본 일자까지 수행
            String[] stream;
            while (streamIndex < streamArray.length) {  // 방송들에 대해 반복문 수행
                stream = streamArray[streamIndex];
                if (Integer.parseInt(stream[1]) > day) break;   // 방송들 체크 중 다음날 방송이 나오면 멈춤

                streamCountMap.put(stream[0], streamCountMap.get(stream[0]) + 1);   // 스트리머의 일주일 총 방송횟수 +1
                streamHourMap.put( stream[0], streamHourMap.get(stream[0]) + (Integer.parseInt(stream[3]) - Integer.parseInt(stream[2])) ); // 스트리머의 일주일 총 방송시간 +오늘 방송 시간
                streamIndex++;
            }

            if (day % 7 == 0) checkIsRealVirtualYoutuberAndResetMaps(realVirtualYoutuberCheckMap); // 일주일을 다 체크했다면 진짜 버튜버인지 체크

            day++;
        }

        PriorityQueue<String> pq = new PriorityQueue<>();   // 사전순 출력을 위한 pq
        for (String key : realVirtualYoutuberCheckMap.keySet()) {   // 진짜 버튜버들을 pq에 저장
            if (realVirtualYoutuberCheckMap.get(key)) pq.offer(key);
        }

        StringBuilder sb = new StringBuilder();
        if (pq.size() == 0) System.out.println(-1);
        else {
            while (!pq.isEmpty()) sb.append(pq.poll()).append("\n");
            System.out.println(sb);
        }

    }

    static void checkIsRealVirtualYoutuberAndResetMaps(Map<String, Boolean> realVirtualYoutuberCheckMap) {  // 매주 말(7의 배수 날짜)마다 진짜 버튜버를 체크하는 함수. "매주" 조건을 충족하는 스트리머가 진짜인 스트리머임. 한 번 false면 아닌 거니까 true -> false로 바꾸는 게 직관적임
        for (String key : realVirtualYoutuberCheckMap.keySet()) {
            if (streamCountMap.get(key) < 5 || streamHourMap.get(key) < 60 * 60) realVirtualYoutuberCheckMap.put(key, false);    // 조건 충족 못하면 거짓으로 값 변경
            streamCountMap.put(key, 0);
            streamHourMap.put(key, 0);
        }
    }

}
