package SSAFY.study.algo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_5430_AC {

    static StringBuilder sb = new StringBuilder();
    static String p;
    static int n;
    static Deque<Integer> dq;
    static boolean errFlag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
            doAC();
            if (errFlag) {
                sb.deleteCharAt(sb.length()-1);
                sb.append("error").append("\n");
            }
        }
        System.out.println(sb);

    }

    static void init(BufferedReader br) throws IOException {
        p = br.readLine();
        n = Integer.parseInt(br.readLine());
        String inputString = br.readLine();
        String subInputString = inputString.substring(1, inputString.length()-1);
        String[] stringArr = subInputString.split(",");
        dq = new ArrayDeque<>();
        for (int i = 0; i < stringArr.length; i++) {
            if (!stringArr[i].equals("")) dq.offer(Integer.parseInt(stringArr[i]));
        }
        errFlag = false;
    }

    static void doAC() {
        sb.append("[");
        int reverseCount = 0;
        for (int i = 0; i < p.length(); i++) {
            String command = String.valueOf(p.charAt(i));
            if (command.equals("R")) reverseCount += 1;
            else if (command.equals("D")) {
                if (dq.isEmpty()) { // 비어있는데 D 쓰면 에러로 간주하고 종료
                    errFlag = true;
                    return;
                }
                if (reverseCount % 2 == 0) {    // 이때까지 뒤집은 횟수가 짝수번이면 앞에서 뽑아내고
                    dq.poll();
                } else {    // 이떄가지 뒤집은 횟수가 홀수번이면 뒤에서 뽑아냄
                    dq.pollLast();
                }
            }
        }
        int dqSize = dq.size();
        for (int i = 0; i < dqSize; i++) {
            int nextInt;
            if (reverseCount % 2 == 0) nextInt = dq.poll();
            else nextInt = dq.pollLast();
            sb.append(nextInt).append(",");
        }
        if (dqSize != 0) sb.deleteCharAt(sb.length()-1);
        sb.append("]").append("\n");
    }

}
