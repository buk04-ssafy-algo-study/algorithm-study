import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> crane = new ArrayList<>();
        for(int i=0;i<N;i++)
            crane.add(Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> box = new ArrayList<>();
        for(int i=0;i<M;i++)
            box.add(Integer.parseInt(st.nextToken()));

        int res = 0;
        // 내림차순 정렬
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        while(true) {
            if(box.size()==0) break; // 박스를 모두 옮겼을 때 반복문 종료
            if(crane.get(0)<box.get(0)) { // 남은 박스를 옮길 수 없을 때
                res = -1;
                break;
            }

            for (int i = 0; i < crane.size(); i++) {
                int craneWeight = crane.get(i);

                for (int j = 0; j < box.size(); j++) {
                    int boxWeight = box.get(j);
                    if (boxWeight <= craneWeight) { // 박스를 옮길 수 있을 때
                        box.remove(j); // 박스 리스트에서 삭제
                        break; // 다음 크레인 사용
                    }
                }
            }
            res++; // 모든 크레인 사용
        }
        System.out.print(res);
    }
}
