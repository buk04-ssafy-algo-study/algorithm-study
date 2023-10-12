package practices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1541 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] eq;
		String[] tmp;
		int i=0,j=0, sum=0, ans=0;
		
		eq = br.readLine().split("-");
		
		for(i=0; i<eq.length;i++) {
			sum=0;
			if(eq[i].indexOf('+')>-1) {
				tmp = eq[i].split("\\+");
				for(j=0; j<tmp.length;j++) {
					sum+=Integer.parseInt(tmp[j]);
				}
			} else {
				sum = Integer.parseInt(eq[i]);
			}
			
			if(i==0) {
				ans+=sum;
			} else {
				ans-=sum;
			}
		}

		System.out.println(ans);
	}
	
	

}
