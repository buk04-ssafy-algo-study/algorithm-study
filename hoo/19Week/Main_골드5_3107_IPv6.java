package SSAFY.study.algo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_골드5_3107_IPv6 {

    static String[] splitString;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        splitString = br.readLine().split(":");
        if (splitString.length == 0) System.out.println("0000:0000:0000:0000:0000:0000:0000:0000");
        else countNotColonAndReplace();
    }

    static String makeShorten(int length) { // 0000:0000...을 만드는 함수
        String shorten = "";
        for (int i = 0; i < length; i++) {
            if (i == length-1) shorten += "0000";
            else shorten += "0000:";
        }

        return shorten;
    }

    static String changeString(String now) {    // 4-현재 문자의 길이만큼 0 더해주기
        String offset = "";
        for (int i = 0; i < 4 - now.length(); i++) {
            offset += "0";
        }

        return offset + now;
    }

    static void doIPv6(int start, int end, String shorten) {
        for (int i = start; i < end; i++) {
            if (splitString[i].equals("")) splitString[i] = shorten;
            else if (splitString[i].length() < 4) splitString[i] = changeString(splitString[i]);
        }
    }

    static void countNotColonAndReplace() { // 축약되지 않은 문자열을 count, 8-그 수 만큼 0000:0000...을 만듬
        int notColonCount = 0;
        for (int i = 0; i < splitString.length; i++) {
            if (!splitString[i].equals("")) notColonCount += 1;
        }
        String shorten = makeShorten(8-notColonCount);

        String result = "";
        if (splitString[0].equals("")) {    // 첫 자리부터 연속으로 두 번이상 0000이어서 축약된 경우, "" 처리가 두 번 되어 맨 처음 것은 빼줌
            doIPv6(1, splitString.length, shorten);
            result = String.join(":", splitString).substring(1);
        } else if (splitString.length != 8 && splitString.length == notColonCount) { // 마지막 자리와 앞자리가 0000이어서 축약된 경우도 마찬가지
            doIPv6(0, splitString.length, shorten);
            result = String.join(":", splitString);
            result += ":" + shorten;
        } else {    // 그 외 중간이 축약된 경우
            doIPv6(0, splitString.length, shorten);
            result = String.join(":", splitString);
        }

        System.out.println(result);
    }

}
