import java.util.*;

public class Main_15663_N과M9 {
    private static int N, M;
    private static int[] num, res;
    private static HashSet<String> set;
    private static boolean[] isVisited;
    private static StringBuilder sb;

    private static void perm(int count) {
        if (count == M) {
            // System.out.println(Arrays.toString(res));
            String ans = Arrays.toString(res);
            if(!set.contains(ans)){
                set.add(ans);

                for(int r:res){
                    sb.append(r+" ");
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isVisited[i]) {
                res[count] = num[i];
                isVisited[i] = true;
                perm(count + 1);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        N = sc.nextInt();
        M = sc.nextInt();

        num = new int[N];
        res = new int[M];
        isVisited = new boolean[N];
        set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }

        Arrays.sort(num);
        perm(0);

        System.out.println(sb);
    }
}
