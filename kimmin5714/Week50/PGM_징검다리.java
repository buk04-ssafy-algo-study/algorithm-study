import java.util.*;
class PGM_징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 1;
        int right = distance;
        Arrays.sort(rocks);
        
        while(left<=right) {
            int mid = (left+right)/2;
            
            if(getRemoveCnt(mid, rocks, distance) <= n) {
                answer = mid;
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }
        return answer;
    }
    private int getRemoveCnt(int mid, int[] rocks, int distance){
        int tmp = 0;
        int cnt = 0;
        for(int i=0;i<rocks.length;i++){
            int diff = rocks[i] - tmp;
            if(diff < mid)
                cnt++;
            else
                tmp = rocks[i];
        }
        
        if(distance-tmp<mid)
            cnt++;
        return cnt;
    }
}
