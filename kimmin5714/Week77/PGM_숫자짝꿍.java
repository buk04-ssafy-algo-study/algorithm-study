public class PGM_숫자짝꿍 {
    public String solution(String X, String Y) {
        int[] cntX = new int[10]; // X에서 각 숫자의 개수를 저장
        int[] cntY = new int[10]; // Y에서 각 숫자의 개수를 저장

        // X의 숫자 개수 카운트
        for (char c : X.toCharArray()) {
            cntX[c - '0']++;
        }

        // Y의 숫자 개수 카운트
        for (char c : Y.toCharArray()) {
            cntY[c - '0']++;
        }

        StringBuilder sb = new StringBuilder();

        // 9부터 0까지 더 적게 있는 만큼 추가
        for (int i = 9; i >= 0; i--) {
            int minCnt = Math.min(cntX[i], cntY[i]); // X, Y 둘 다에서 등장하는 개수만큼 추가
            for (int j = 0; j < minCnt; j++) {
                sb.append(i);
            }
        }

        if (sb.length() == 0) return "-1";
        if (sb.charAt(0) == '0') return "0";

        return sb.toString();
    }
}