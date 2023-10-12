import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N;
    static int[] picks;
    static int[] numbers;
    static Set<Integer> set;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        picks = new int[N];
        numbers = new int[] {1,5,10,50};
        set = new HashSet<>();
        comb(0, 0);
        System.out.println(set.size());
    }
    static void comb(int cnt, int start) {
        if(cnt == N) {
            int sum = 0;
            for (int i = 0; i < picks.length; i++) {
                sum += picks[i];
            }
            set.add(sum);
            return;
        }
        for (int i = start; i < 4; i++) {
            picks[cnt] = numbers[i];
            comb(cnt + 1,i);
        }
    }
}