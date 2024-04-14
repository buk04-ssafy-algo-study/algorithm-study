// https://www.acmicpc.net/problem/17140
import java.io.*;
import java.util.*;

class Main {

    static int r, c, k;
    static int[][] A;
    static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        A = new int[100][100];
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());
        row = 3; col =3;
        for(int r=0;r<3;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<3;c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        int sec = 0;
        while(sec<100) {
            if(A[r][c]==k) break;
            if(row >= col) R();
            else C();
            sec++;
        }
   
        if(A[r][c]!=k) sec=-1;
        System.out.println(sec);
    }

    static void R() {
        Map<Integer, Integer> map = new HashMap<>();
        for(int r=0;r<row;r++) {
            for(int c=0;c<col;c++) {
                if(A[r][c]==0) continue; // 0 무시
                int cnt = map.getOrDefault(A[r][c], -1);
                if(cnt==-1) map.put(A[r][c], 1);
                else map.replace(A[r][c],cnt+1);
            }
            // 수 등장 횟수 카운트&정렬
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
            entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer>o1, Map.Entry<Integer, Integer> o2) {
                    if(o1.getValue() != o2.getValue()) return Integer.compare(o1.getValue(), o2.getValue());
                    return Integer.compare(o1.getKey(), o2.getKey());
                }
            });
            // 다시 넣기
            int cIdx = 0;
            for(Map.Entry<Integer, Integer> entry : entryList) {
                A[r][cIdx] = entry.getKey();
                A[r][++cIdx] = entry.getValue();
                cIdx++;
                if(cIdx>=100) break;
            }
            col = Math.max(col, cIdx);

            for(int cc=cIdx;cc<col;cc++) {
                A[r][cc] = 0;
            }
            map.clear();
        }
    }

    static void C() {
        Map<Integer, Integer> map = new HashMap<>();
        for(int c=0;c<col;c++) {
            for(int r=0;r<row;r++) {
                if(A[r][c]==0) continue; // 0 무시
                int cnt = map.getOrDefault(A[r][c], -1);
                if(cnt==-1) map.put(A[r][c], 1);
                else map.replace(A[r][c],cnt+1);
            }
            // 수 등장 횟수 카운트&정렬
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
            entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer>o1, Map.Entry<Integer, Integer> o2) {
                    if(o1.getValue() != o2.getValue()) return Integer.compare(o1.getValue(), o2.getValue());
                    return Integer.compare(o1.getKey(), o2.getKey());
                }
            });
            // 다시 넣기
            int rIdx = 0;
            for(Map.Entry<Integer, Integer> entry : entryList) {
                A[rIdx][c] = entry.getKey();
                A[++rIdx][c] = entry.getValue();
                rIdx++;
                if(rIdx>=100) break;
            }
            row = Math.max(row, rIdx);

            for(int rr=rIdx;rr<row;rr++) {
                A[rr][c] = 0;
            }
            map.clear();
        }
    }
}
