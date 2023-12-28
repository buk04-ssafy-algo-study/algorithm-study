import java.util.*;
import java.io.*;
public class Main_선긋기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;

    static class Node implements Comparable<Node> {
        int start;
        int end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node other) {
            if(this.start < other.start) {
                return -1;
            }else if(this.start == other.start) {
                if(this.end < other.end){
                    return -1;
                }
            }
            else {
                return 1;
            }
            return 1;
        }
        @Override
        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }

    }

    static Node[] node;

    static int N , cnt = 0;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        node = new Node[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            node[i] = new Node(start, end);
        }
        Arrays.sort(node);
        int ans = 0;
        int start = 0;
        int end = 0;
        for(int i=0;i<N;i++) {
            if(i == 0) {
                start = node[i].start;
                end = node[i].end;
                ans = end - start;
                continue;
            }
            Node cur = node[i];
            if(cur.start <= end) {
                if(cur.end <= end) continue;
                ans += cur.end - end;
                end = cur.end;
            }else {
                ans += cur.end - cur.start;
                end = cur.end;
            }
        }
        System.out.println(ans);
    }
}