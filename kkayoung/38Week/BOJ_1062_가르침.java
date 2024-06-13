// https://www.acmicpc.net/problem/1062
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static boolean[] readable = new boolean[26];
    static String[] words;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new String[N];
        
        // input words
        for(int i=0;i<N;i++) {
            words[i] = br.readLine();
        }
        
        if(K==26) {
            answer = N;
        }
        else if(K>4) {
            readable['a'-'a'] = true;
            readable['c'-'a'] = true;
            readable['t'-'a'] = true;
            readable['i'-'a'] = true;
            readable['n'-'a'] = true;

            read(0, 0);
        }

        System.out.println(answer);
    }

    static void read(int cnt, int start) {
        if(cnt==(K-5)) {
            int tmp = 0;
            for(int w=0;w<N;w++) {
                if(canRead(words[w])) {
                    tmp++;
                }
            }
            answer = Math.max(answer, tmp);
            return;
        }

        for(int i=start;i<26;i++) {
            if(readable[i]) continue;
            readable[i] = true;
            read(cnt+1, i);
            readable[i] = false;
        }
    }

    static boolean canRead(String word) {
        int length = word.length();

        for(int idx=4;idx<length-4;idx++) {
            if(!readable[word.charAt(idx)-'a']) return false;
        }
        return true;
    }

    
}
