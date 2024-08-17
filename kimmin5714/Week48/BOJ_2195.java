import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2195 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String P = br.readLine();
        int res = 0;

        for(int pIdx=0;pIdx<P.length();pIdx++){ // P문자열 앞에서부터 확인
            int maxSame = 0;
            int pLastIdx = 0;
            for(int sIdx=0;sIdx<S.length();sIdx++){ // S문자열 앞에서부터 확인
                int same = 0;

                if(S.charAt(sIdx) == P.charAt(pIdx)){ // 같은 문자 만난 경우

                    // 어디까지 같은 지 확인하기 위한 임시 인덱스
                    int sTmpIdx = sIdx+1;
                    int pTmpIdx = pIdx+1;
                    same++; // 몇 글자 같은지 확인 (for 인덱스 조정)

                    // 어디까지 같은 지 확인
                    while(pTmpIdx<P.length() && sTmpIdx<S.length() && S.charAt(sTmpIdx) == P.charAt(pTmpIdx) ){
                        sTmpIdx++;
                        pTmpIdx++;
                        same++;
                    }

                    // 제일 많이 겹치는 경우
                    if(maxSame < same) {
                        // 검사한 데 이후부터 다시 검사하기 위한 인덱스 조정
                        sIdx = sTmpIdx-1;
                        pLastIdx = pTmpIdx-1;
                    }
                    maxSame = Math.max(maxSame, same);
                }
            }
            res++; // copy() 횟수 추가
            pIdx = pLastIdx; // 제일 많이 겹친 곳 이후부터 검사
        }
        System.out.print(res);
    }
}
