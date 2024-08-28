import java.util.Arrays;

class Solution {
    public int solution(int goal, int[] rocks, int n) {
        Arrays.sort(rocks);

        int left = 0;
        int right = goal;

        int result = Integer.MIN_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            int prev = 0;
            
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) {
                    cnt++;
                } else {
                    prev = rocks[i];
                }
                if (cnt > n) {
                    break;
                }
            }
            
            if (goal - prev < mid && cnt <= n) {
                cnt++;
            }
            if (cnt > n) {
                right = mid - 1;
            } else {
                left = mid + 1;

                result = mid;
            }
        }
        return result;
    }
}