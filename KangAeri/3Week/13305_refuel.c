#include <stdio.h>
#include <stdlib.h>

typedef struct city{
	int no;
	long long price;
}city;

struct city cities[100000];
struct city tmp[100000];

void merge(int p, int q){
	int x=0;
	int r=(q+p)/2;
	int i=p;
	int j=r+1;
	while(i<=r && j<=q){
		if(cities[i].price<cities[j].price){
			tmp[x++]=cities[i++];
		} else{
			tmp[x++]=cities[j++];
		}
	}
	while(i<=r){
		tmp[x++]=cities[i++];
	}
	while(j<=q){
		tmp[x++]=cities[j++];
	}
	for(i=0;i<q-p+1;i++){
		cities[p+i] = tmp[i];
	}
}

void msort(int p, int q){
	int r=(q+p)/2;
	if(p>=q){
		return;
	}
	msort(p,r);
	msort(r+1,q);
	merge(p,q);
}


int main(){
	int n=0;
	int elem=0,i=0,j=0,cityMinNo=0;
	long long ans=0;
	int* road; 
	
	scanf("%d", &n);
	
	road = (int*)malloc(sizeof(int)*(n-1));	
	scanf("%d", &road[0]);
	
	for(i=1;i<n-1;i++){
		scanf("%d", &elem);
		road[i] = road[i-1]+elem;
	}
	for(i=0;i<n;i++){
		scanf("%lld", &cities[i].price);
		cities[i].no = i;
	}

	msort(0,n-1);

	cityMinNo=n-1;
	for(i=0;i<n;i++){
		if(cities[i].no==0){
			ans+=( cities[i].price * road[cityMinNo-1] );
			break;
		} else if(cities[i].no<cityMinNo){
			ans+=( cities[i].price * ((long long)road[cityMinNo-1]-(long long)road[cities[i].no-1]) );
			cityMinNo=cities[i].no;
		}
	}

	printf("%lld",ans);
	free(road);
	return 0;
}
