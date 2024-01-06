package SSAFY.study.algo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_골드5_9251_LCS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String input2 = br.readLine();
        int[] dpArr = new int[input2.length()];
        for (int i = 0; i < input1.length(); i++) {
            int temp = 0;
            for (int j = 0; j < input2.length(); j++) {
                if (temp < dpArr[j]) temp = dpArr[j];
                else if (String.valueOf(input1.charAt(i)).equals(String.valueOf(input2.charAt(j)))) dpArr[j] = temp + 1;
            }
        }
        Arrays.sort(dpArr);
        System.out.println(dpArr[dpArr.length-1]);
    }

}
