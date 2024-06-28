import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S, T;
        S = br.readLine();
        T = br.readLine();

        dfs(S, T.length(), T);

        if(ans != 1)
            ans = 0;
        System.out.print(ans);
    }

    private static void dfs(String s, int size, String t) {
        if(ans == 1) return;

        if(s.length() == t.length()){
            if (s.equals(t)) {
                ans = 1;
            }
            return;
        }
        if (s.length() > t.length()-1)
            return;

        //t맨 뒤가 A이면 뺀다.
        if(t.charAt(size-1) == 'A') {
            String newT = backA(t, size);
            dfs(s,size-1,newT);
        }

        //t맨 앞이 B면 B제외하고 뒤집는다.
        if(t.charAt(0) == 'B'){
            String newT = backBandReverse(t,size);
            dfs(s,size-1,newT);
        }
    }

    private static String backBandReverse(String t, int size) {
        char[] Sch = t.toCharArray();
        char[] ch = new char[size-1];

        int last = Sch.length-1;
        for (int i = 0; i < size-1; i++) {
            ch[i] = Sch[last];
            last--;
        }
        return String.valueOf(ch);
    }

    private static String backA(String s, int size) {
        char[] ch = new char[size - 1];
        char[] Sch = s.toCharArray();
        for (int i = 0; i < size - 1; i++)
            ch[i] = Sch[i];

        return String.valueOf(ch);
    }
}
