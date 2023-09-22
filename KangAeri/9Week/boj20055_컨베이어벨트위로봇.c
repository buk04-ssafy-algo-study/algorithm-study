#include <stdio.h>

int main(){
	int n=0,k=0,len=0, ans=0;
	int i=0;
	int cnt=0, idx=0, ni=0, start=0, fall=0;
	int cb[202];
	int flag[202];
	scanf("%d %d",&n, &k);
	len=2*n;
	for(idx=0; idx<len;idx++){
		scanf("%d",&cb[idx]);
		flag[idx]=0;
	}
	while(1){
		ans++;
		
		//1.
		start=(start-1+len)%len;
		fall = (start+n-1)%len;
		idx = (start+n-2)%len;
		
		//2.
		i=n-1;
		while(idx!=(start-1+len)%len){
		//while(i>0){	
		//	printf("%d ",i);
			ni = (idx+1)%len;
			if(flag[idx]==1 && flag[ni]==0 && cb[ni]>0){
				flag[idx]=0;
				flag[ni]=1;
				cb[ni]--;
				if(cb[ni]==0){
					cnt++;
				}
				if(ni==fall){
					printf("fall ");
					flag[ni]==0;
				}
			}
		
			idx=(idx-1+len)%len;
			printf("(%d) ",idx);
			i--;
		}
		printf("//");
		//3. 
		if(cb[start]>0){
			flag[start]=1;	
			cb[start]--;
			if(cb[start]==0){
				cnt++;
			}			
		}
	
		//4.
		if(cnt>=k){
			printf("%d\n",ans);
			break;
		}
	}

	return 0;
}
