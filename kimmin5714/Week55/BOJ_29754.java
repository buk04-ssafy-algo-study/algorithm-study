import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Work {
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
        Map<String, Work> youtuberMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = (Integer.parseInt(st.nextToken()) - 1) / 7;
            String startTime = st.nextToken();
            String endTime = st.nextToken();

            youtuberMap.putIfAbsent(name, new Work(weeks));
            Work work = youtuberMap.get(name);

            work.cnt[day]++;
            updateWorkTime(startTime, endTime, work, day);
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

        if (work.min[day] < 0) {
            work.min[day] += 60;
            work.hour[day]--;
        }

        if (work.min[day] >= 60) {
            work.min[day] -= 60;
            work.hour[day]++;
        }
    }
}
