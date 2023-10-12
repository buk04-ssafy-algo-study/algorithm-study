#include <stdio.h>
int n,ans;
int chk[2000];
int val[4]={1,5,10,50};
void comb(int len, int start,int sum){
	int i=0;
	if(len==n){
		if(chk[sum]!=1){
			chk[sum]=1;
			ans++;
		}
		return;
	}
	for(i=start; i<4;i++){
		comb(len+1, i, sum+val[i]);
	}
}
int main(){
	int i=0;
	scanf("%d", &n);
	for(i=0;i<2000;i++){
		chk[i]=0;
	}
	ans=0;
	comb(0,0,0);
	printf("%d",ans);
	return 0;
}

