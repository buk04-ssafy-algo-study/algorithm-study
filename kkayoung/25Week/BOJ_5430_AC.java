// https://www.acmicpc.net/problem/5430
import java.io.*;
import java.util.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            String[] nums = arr.substring(1,arr.length()-1).split(",");

            executeP(nums, n, p);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void executeP(String[] nums, int n, String p) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        for(String num:nums) deque.add(num);

        int reversed = 0;
        for(int i=0;i<p.length();i++) {
            char c = p.charAt(i);
            if(c=='R') { // reverse
                reversed++;
            } else { // delete
                if(n==0 || deque.size()==0) {
                    sb.append("error\n");
                    return;
                } else if(reversed%2==0) { // even
                    deque.pollFirst(); // remove front
                } else { // odd
                    deque.pollLast(); // remove rear
                }   
            }
        }

        sb.append("[");
        if(reversed%2==0) { // delete from front
            while(!deque.isEmpty()) {
                sb.append(deque.pollFirst());
                if(deque.size()==0) break;
                sb.append(",");
            }
        } else { // delete from rear
            while(!deque.isEmpty()) {
                sb.append(deque.pollLast());
                if(deque.size()==0) break;
                sb.append(",");
            }
        }
        sb.append("]\n");   
    }
}
