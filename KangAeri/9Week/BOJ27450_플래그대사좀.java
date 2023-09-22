package probs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BOJ27450 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0,k=0,i=0;
		long tmp=0, inh=0, inc=0, cnt=0, ans=0;
		long[] shout;
		String[] in;
		String[] tarIn;
		
		in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		k = Integer.parseInt(in[1]);
		shout= new long[n];
		
		in = br.readLine().split(" ");
		tarIn = br.readLine().split(" ");
		
		for(i=0; i<n; i++) {
			tmp = Integer.parseInt(tarIn[i])-Integer.parseInt(in[i]);
			if(i-1>=0) {//by moving rightward
				inh-=cnt;
			}	
			if(i-k>=0) {
				cnt-=shout[i-k];
				inh+=k*shout[i-k];
			}
			inc =cnt*k+inh;
			tmp -=inc;
			if(tmp>0) {
				ans+=tmp/k;
				shout[i]=tmp/k;
				cnt+=tmp/k;
				if(tmp%k>0) {
					ans++;
					shout[i]++;
					cnt++;
				}
			}
		}
		System.out.println(ans);
	}
}
