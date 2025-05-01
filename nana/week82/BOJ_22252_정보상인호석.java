import java.util.*;
import java.io.*;

class Main {

    private static long res;
    private static Map<String, PriorityQueue<Integer>> info;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());

        res = 0;    // 구매한 정보 가치의 총합

        info = new HashMap<>();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if (n == 1) {
                if (!info.containsKey(name)) {
                    info.put(name, new PriorityQueue<>(Comparator.reverseOrder()));
                }
                int k = Integer.parseInt(st.nextToken());
                // 새로운 정보를 얻을 때
                for (int c = 0; c < k; c++) {
                    info.get(name).add(Integer.parseInt(st.nextToken()));
                }
            } else {
                // 정보를 얻어갈 때
                int b = Integer.parseInt(st.nextToken());
                // 정보를 얻을 고릴라의 이름이 없으면 통과
                if (!info.containsKey(name)) continue;
                while (b-- > 0 && !info.get(name).isEmpty()) {
                    res += info.get(name).poll();
                }
            }
        }

        System.out.println(res);
    }
}