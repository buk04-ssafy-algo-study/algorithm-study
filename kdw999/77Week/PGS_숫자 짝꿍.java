import java.util.*;

class Solution {
   public String solution(String X, String Y) {
	     
       int[] xArr = new int[10];
       int[] yArr = new int[10];

       for(int i=0; i<X.length(); i++) xArr[X.charAt(i)- 48]++;
       for(int i=0; i<Y.length(); i++) yArr[Y.charAt(i)- 48]++;
       
       String result = "";
       
       for(int i=9; i>=0; i--) {
           if(xArr[i] >= 1 && yArr[i] >= 1){
               
               int min = Math.min(xArr[i], yArr[i]);
            
               result += String.valueOf(i).repeat(min);
               // for(int j=0; j<min; j++) result += String.valueOf(i).repeat(min);
           }
       }
       
       if(result.equals("")) return "-1";
       else if(result.charAt(0) == '0') return "0";
       else return result;
	    }
}