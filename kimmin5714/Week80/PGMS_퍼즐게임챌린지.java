class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int right = 0;
        int left = 1;
        for(int i=0;i<diffs.length;i++) { // 숙련도 최댓값 찾기
            right = (diffs[i]>right) ? diffs[i] : right;
        }

        // 이분탐색
        int mid = -1;
        while(left<=right) {
            mid = (right+left)/2;

            if(calculateLevel(mid,diffs,times,limit)){ // 가능하면 더 작은 범위 탐색
                answer = mid;
                right = mid-1;
            }
            else { // 불가능하면 더 큰 범위 탐색
                left = mid+1;
            }
        }

        return answer;
    }
    public boolean calculateLevel(int level, int[] diffs, int[] times, long limit) {
        long time = 0;

        // 문제에 주어진 식 그대로 계산
        for(int i=0;i<diffs.length;i++) {
            int diff = diffs[i]-level;

            if(diff<=0) time += times[i];
            else {
                int preTime = (i == 0)? 0 : times[i-1];
                time += (preTime + times[i])*diff + times[i];
            }
            if(time>limit) return false;
        }

        if(time<=limit) return true;
        else {
            return false;
        }
    }
}