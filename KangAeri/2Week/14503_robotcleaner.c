#include <stdio.h>
#include <stdlib.h>


int main(){
	int n=0,m=0, posr=0,posc=0,dir=0;
	int i=0,j=0, tr=0,tc=0;
	int ans=0;
	int** room; 
	int dlt[4][2] ={{-1,0},{0,1},{1,0},{0,-1}};//n-e-s-w
	
	scanf("%d %d", &n, &m);
	room = (int**)malloc(sizeof(int*)*n);
	for(i=0;i<n;i++){
		room[i] = (int*)malloc(sizeof(int)*m);
	}
	scanf("%d %d %d", &posr, &posc, &dir);
	for(i=0;i<n;i++){
		for(j=0;j<m;j++){
			scanf("%d",&room[i][j]);
		}
	}
	
	//clean
	while(1){
		if(room[posr][posc]==0){
			room[posr][posc]=-1;
			ans++;
		}
		if( room[posr-1][posc]==0 || room[posr+1][posc]==0 || room[posr][posc-1]==0 || room[posr][posc+1]==0){
			dir=(dir+3)%4;
			if(room[posr+dlt[dir][0]][posc+dlt[dir][1]]==0){
				posr = posr+dlt[dir][0];
				posc = posc+dlt[dir][1];
			}
		} else{
			tr = dlt[(dir+2)%4][0]+posr;
			tc = dlt[(dir+2)%4][1]+posc;
			if(room[tr][tc]!=1){
				posr = tr;
				posc = tc;
			} else {
				break;
			}
		}
	}
	
	
	printf("%d",ans);
	//free mem
	for(i=0;i<n;i++){
		free(room[i]);
	}
	free(room);
	return 0;
}
