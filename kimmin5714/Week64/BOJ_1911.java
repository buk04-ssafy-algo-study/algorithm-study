import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pool implements Comparable<Pool>{
    int start, end;

    public Pool(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pool o) {
        if(this.start == o.start)
            return o.end - this.end; // 시작값 같으면 끝 값 내림차순 정렬
        return this.start - o.start; // 시작값 기준 오름차순 정렬
    }
}
public class BOJ_1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        PriorityQueue<Pool> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Pool(start, end));
        }

        int res = 0; // 널빤지 개수
        int fill = 0; // 널빤지 맨 끝 위치

        while(!pq.isEmpty()){
            Pool cur = pq.poll();

            if(cur.end < fill) // 현재 웅덩이가 이미 널빤지로 채워진 경우
                continue;

            if(fill < cur.start) // 현재 웅덩이 시작 위치 기준 최대 위치로 변경
                fill = cur.start;

            int remain = (cur.end - fill) % L; // 널빤지 범위 넘어가는 값 구하기:(웅덩이 끝 - 널빤지 끝)%길이
            res += (cur.end - fill) / L;	// 사용할 널빤지 개수 구하기
            fill = cur.end;

            if(remain != 0) { // 널빤지 범위 넘어갈 때
                res++;
                fill += L - remain;
            }
        }
        System.out.print(res);
    }
}