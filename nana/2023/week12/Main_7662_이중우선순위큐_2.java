package study.week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_7662_이중우선순위큐_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int k = Integer.parseInt(br.readLine());

            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {

                st = new StringTokenizer(br.readLine());
                String order = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (order.equals("I")) {
                    // I 일 때 맵에 키값으로 없다면 num:1 을 넣음
                    if (!map.containsKey(num))
                        map.put(num, 1);
                    else
                        // 이미 존재하는 값이라면 num: value++를 넣음
                        map.replace(num, map.get(num) + 1);
                } else {

                    // order 가 D지만 맵이 비어있다면 넘어감
                    if(map.isEmpty()) continue;

                    // num 이 1이면 가장 큰 값 -1 이면 가장 작은 값을 꺼냄
                    if (num == 1) {
                        // 맵의 가장 큰 값의 value가 1이라면 하나만 있는 값이므로
                        // 마지막값을 모두 빼줌
                        if (map.get(map.lastKey()) == 1)
                            map.pollLastEntry();
                        else
                            // value가 1이 아니라면 값이 두 개 이상이므로
                            // key는 건들지 않고 value만 하나 줄여준다
                            map.replace(map.lastKey(), map.get(map.lastKey()) - 1);
                    } else {
                        // 위 방법과 동일하게 진행
                        if (map.get(map.firstKey()) == 1)
                            map.pollFirstEntry();
                        else
                            map.replace(map.firstKey(), map.get(map.firstKey()) - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey() + " " + map.firstKey() + "\n");
            }


        }

        System.out.println(sb);

    }
}
