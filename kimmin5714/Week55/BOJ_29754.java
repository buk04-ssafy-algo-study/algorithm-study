import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Work { // i주마다 일한 횟수와 시간
    int[] cnt;
    int[] hour;
    int[] min;

    public Work(int weeks) {
        cnt = new int[weeks];
        hour = new int[weeks];
        min = new int[weeks];
    }

    @Override
    public String toString() {
        return "Work{" +
                "cnt=" + Arrays.toString(cnt) +
                ", hour=" + Arrays.toString(hour) +
                ", min=" + Arrays.toString(min) +
                '}';
    }
}

public class BOJ_29754 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int weeks = M / 7;
        Map<String, Work> youtuberMap = new HashMap<>(); // 이름이 Key, 일한 횟수와 시간을 Value로 저장

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = (Integer.parseInt(st.nextToken()) - 1) / 7; // 인덱스로 몇 번째 주인지 확인
            String startTime = st.nextToken();
            String endTime = st.nextToken();

            youtuberMap.putIfAbsent(name, new Work(weeks)); // HashMap에 없는 유튜버이면 생성
            Work work = youtuberMap.get(name);

            work.cnt[day]++; // 일한 횟수 추가
            updateWorkTime(startTime, endTime, work, day); // 일한 시간 업데이트
        }

        List<String> res = new ArrayList<>();
        for (String key : youtuberMap.keySet()) {
            Work work = youtuberMap.get(key);
            int weekWork = 0;
            for (int i = 0; i < weeks; i++) {
                if (work.cnt[i] >= 5 && work.hour[i] >= 60)
                    weekWork++;
            }
            if (weekWork >= weeks)
                res.add(key);
        }

        if (res.isEmpty()) {
            System.out.print("-1");
        } else {
            Collections.sort(res);
            for (String name : res) {
                System.out.println(name);
            }
        }
    }

    private static void updateWorkTime(String startTime, String endTime, Work work, int day) {
        StringTokenizer st = new StringTokenizer(startTime, ":");
        int h1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(endTime, ":");
        int h2 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());

        work.hour[day] += h2 - h1;
        work.min[day] += m2 - m1;

        if (work.min[day] < 0) { // 분 단위가 음수인 경우
            work.min[day] += 60;
            work.hour[day]--;
        }

        if (work.min[day] >= 60) { // 분 단위가 60 이상인 경우
            work.min[day] -= 60;
            work.hour[day]++;
        }
    }
}
