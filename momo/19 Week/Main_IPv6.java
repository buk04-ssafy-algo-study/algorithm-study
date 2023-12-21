import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        boolean flag = false;
        boolean flag2 = false;
        boolean two = false;
        String[] split = br.readLine().split(":");
        int allLen = 8;
        int cnt = 0;
        for(String a : split) {
            if(cnt > 1) {
                two = true;
                break;
            }
            if(a.equals("")) {
                cnt++;
            }
        }
        if(split.length == 1) {
            
            if (split[0].length() < 4 && split[0].length() > 0) {
                int len = 4 - split[0].length();
                while(len-- > 0) {
                    sb.append("0");
                }
            
            }
            sb.append(split[0] + ":0000:0000:0000:0000:0000:0000:0000");
        }
        
        else if(split.length == 0){
            sb.append("0000:0000:0000:0000:0000:0000:0000:0000");
        }
        else {
            for(String a : split ) {
                if(a.length() == 0 && flag) continue;
                // 4개 보다 작으면 0을 채워줘야함
                StringBuilder tmp = new StringBuilder();
                if (a.length() < 4 && a.length() > 0) {
                    int len = 4 - a.length();
                    while(len-- > 0) {
                        tmp.append("0");
                    }
                }
                if(a.length() == 0 && !flag) {
                    int len = 8 - (split.length-1);
                    while(len-- > 0) {
                        tmp.append("0000:");
                    }
                    if(tmp.length() > 0)
                        tmp.deleteCharAt(tmp.length() - 1);
                    allLen--;
                    flag = true;
                }
                if(two) {
                    if(a.length() == 0 && flag && !flag2 && split.length < 9) {
                        tmp.append(":0000");
                        flag2 = true;
                    }
                    if(a.length() == 0 && flag && !flag2 && split.length > 8) {
                        tmp.append("0000");
                        flag2 = true;
                    }
                }
                    sb.append(tmp + a + ":");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if(sb.toString().split(":").length != 8) {
            int len = 8 - sb.toString().split(":").length;
            while(len-- > 0) {
                sb.append(":0000");
            }

        }
    System.out.println(sb.toString());
    }
}
