import java.io.*;
import java.util.*;

public class Main {

    static final int GOAL_STREAM_COUNT = 5;  // 최소 5회
    static final int GOAL_STREAM_TIME = 60 * 60; // 최소 60시간 (3600분)

    // Vtuber 클래스
    static class Vtuber {
        int streamCount;
        int streamTime;

        public Vtuber(int streamCount, int streamTime) {
            this.streamCount = streamCount;
            this.streamTime = streamTime;
        }

        public void addStream(int time) {
            this.streamCount++;
            this.streamTime += time;
        }

        public void reset() {
            this.streamCount = 0;
            this.streamTime = 0;
        }
    }

    // 방송 정보 클래스
    static class ForOrder implements Comparable<ForOrder> {
        String name;
        int day;
        int startTime;
        int endTime;

        public ForOrder(String name, int day, String startTime, String endTime) {
            this.name = name;
            this.day = day;
            this.startTime = convertToMinutes(startTime);
            this.endTime = convertToMinutes(endTime);
        }

        // 시간을 분으로 변환하는 메서드
        private int convertToMinutes(String time) {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours * 60 + minutes;
        }

        @Override
        public int compareTo(ForOrder o) {
            return this.day - o.day;
        }

        public int getStreamTime() {
            return endTime - startTime; // 방송 시간을 분 단위로 반환
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 방송 수
        int M = Integer.parseInt(st.nextToken()); // 마지막으로 본 날짜
        int weeksCount = M / 7; // 총 몇 주차인지 계산

        Map<String, Vtuber> vtuberData = new HashMap<>(); // 각 유튜버의 방송 횟수와 시간 저장
        Map<String, Integer> jjinVtuberWeeks = new HashMap<>(); // 찐 버츄얼 유튜버가 몇 주를 충족했는지 저장
        Set<String> jjinVtuber = new TreeSet<>(); // 최종 찐 버츄얼 유튜버 이름 저장 (사전 순 정렬)

        ForOrder[] streams = new ForOrder[N];

        // 방송 정보를 입력받음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            String startTime = st.nextToken();
            String endTime = st.nextToken();

            streams[i] = new ForOrder(name, day, startTime, endTime);
        }

        // 방송을 날짜 순으로 정렬
        Arrays.sort(streams);

        int currentWeek = 1;
        for (int i = 0; i < N; i++) {
            String name = streams[i].name;
            int day = streams[i].day;
            int streamTime = streams[i].getStreamTime();

            // 현재 주차 계산 (7일 단위로 주차 증가)
            int week = (day - 1) / 7 + 1;

            // 주차가 바뀔 때마다 이전 주차에서 찐 유튜버인지 체크
            if (week != currentWeek) {
                // 현재 주차에 대한 데이터 확인
                for (Map.Entry<String, Vtuber> entry : vtuberData.entrySet()) {
                    String vtuberName = entry.getKey();
                    Vtuber data = entry.getValue();
                    if (data.streamCount >= GOAL_STREAM_COUNT && data.streamTime >= GOAL_STREAM_TIME) {
                        jjinVtuberWeeks.put(vtuberName, jjinVtuberWeeks.getOrDefault(vtuberName, 0) + 1);
                    }
                }
                vtuberData.clear(); // 주차가 바뀌면 데이터 초기화
                currentWeek = week; // 주차 변경
            }

            // 해당 유튜버의 방송 데이터를 추가
            vtuberData.putIfAbsent(name, new Vtuber(0, 0));
            vtuberData.get(name).addStream(streamTime);
        }

        // 마지막 주차에 대해서도 처리
        for (Map.Entry<String, Vtuber> entry : vtuberData.entrySet()) {
            String vtuberName = entry.getKey();
            Vtuber data = entry.getValue();
            if (data.streamCount >= GOAL_STREAM_COUNT && data.streamTime >= GOAL_STREAM_TIME) {
                jjinVtuberWeeks.put(vtuberName, jjinVtuberWeeks.getOrDefault(vtuberName, 0) + 1);
            }
        }

        // 모든 주차에서 조건을 만족한 유튜버만 추출
        for (Map.Entry<String, Integer> entry : jjinVtuberWeeks.entrySet()) {
            if (entry.getValue() == weeksCount) {
                jjinVtuber.add(entry.getKey());
            }
        }

        // 결과 출력
        if (jjinVtuber.isEmpty()) {
            System.out.println(-1);
        } else {
            for (String vtuberName : jjinVtuber) {
                System.out.println(vtuberName);
            }
        }
    }
}