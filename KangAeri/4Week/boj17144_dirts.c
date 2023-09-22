#include <stdio.h>
#include <stdlib.h>

int dlt[4][2] = {{-1,0}, {0,1}, {1, 0}, {0,-1}};
int info[50][50];
int room[50][50];

int main(){
	int r=0,c=0,t=0;
	int m=0, k=0,d=0;
	int ni=0,nj=0,cnt=0;
	int i=0,j=0,tmp=0, ac=0;

	scanf("%d %d %d", &r, &c, &t);
	
	for(i=0;i<r;i++){
		//room[i] =(int*)malloc(sizeof(int)*c);
		for(j=0;j<c;j++){
			scanf("%d", &tmp);	
			room[i][j]=tmp;
			info[i][j]=tmp;
			if(tmp==-1){
				ac=i;//-> On(ac,0) & (ac-1,0)
			}
		}
	}
////////////////
	for(k=0; k<t; k++){
		//printf("fork");
		for(i=0; i<r; i++) {
			for(j=0; j<c; j++){
				for(d=0;d<4;d++){
					ni = i+dlt[d][0];
					nj = j+dlt[d][1];
					if(ni>=0 && nj>=0 && ni<r && nj<c && room[ni][nj]>=0){
						info[ni][nj] += (room[i][j]/5);
						info[i][j]-=(room[i][j]/5);
					}
				}//	dirts spread
			}
		}
		//printf("log");
		i=ac+1;
		j=0;
		d=2;
		cnt=0;
		while(!(i==ac && j==1)){//ac lower part
			cnt++;
			ni = i+dlt[d][0];
			nj = j+dlt[d][1];
			if(ni<ac || ni>=r || nj<0 ||nj>=c){
				d= (d+3)%4;
				ni = i+dlt[d][0];
				nj = j+dlt[d][1];
			}
			info[i][j] = info[ni][nj];
			i=ni;
			j=nj;
		}	
		info[ac][1] = 0;
		
		i=ac-2;
		j=0;
		d=0;
		while(!(i==ac-1 && j==1)){//ac upper part
			ni = i+dlt[d][0];
			nj = j+dlt[d][1];
			if(ni<0 || ni>=ac || nj<0 ||nj>=c){
				d= (d+1)%4;
				ni = i+dlt[d][0];
				nj = j+dlt[d][1];
			}
			info[i][j] = info[ni][nj];
			i=ni;
			j=nj;
		}
		info[ac-1][1] = 0;
		
		//printf("%d",k);
		///final.copy
		for(i=0; i<r; i++){
			for(j=0; j<c; j++){
				room[i][j] = info[i][j];
			}
		}
		//printf("log");

	}
	
	cnt=0;
	////////////////////////
	for(i=0; i<r; i++){
		for(j=0; j<c; j++){
			if(room[i][j]>0){
				cnt+=room[i][j];
			}
		}
	}
	printf("%d",cnt);

	

	return 0;
}//endmain

