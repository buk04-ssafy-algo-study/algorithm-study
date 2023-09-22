#include <stdio.h>

int main(void)
{
	int T,n;
	int i=0,tri=0;
    long long int ans[100]; //ans[99]==888855064897
    for(i=0;i<5;i++){
		if(i<3){
			ans[i]=1;
		} else{
			ans[i]=2;
		}
	}
    for(i=5;i<100;i++){
		ans[i]=ans[i-1]+ans[i-5]; //dp
	}
	scanf("%d", &T);
	for (i= 0; i < T; i++)
	{
		scanf("%d", &n); 
		printf("%lld\n", ans[n-1]);   
	}
	
	return 0;
}
