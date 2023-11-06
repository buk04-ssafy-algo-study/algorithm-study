#include <stdio.h>
#include <stdlib.h>
int fque[1000000];
int jque[1000000];
int mov[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};

int main(){
	char in[1001];
	int i=0,j=0, r=0, c=0, flag=1;
	int tmp=0, cur=0, jfront=0, jrear=0, ffront=0, frear=0, ni=0,nj=0;
	int** maze;
	int** fire;

	scanf("%d %d ", &r, &c);
	maze = (int**)malloc(sizeof(int*)*r);
	fire = (int**)malloc(sizeof(int*)*r);
	for(i=0; i<r; i++){
		maze[i]=(int*)malloc(sizeof(int)*c);
		fire[i]=(int*)malloc(sizeof(int)*c);
	}
	
	for(i=0; i<r; i++){		
		scanf("%s",in);
		for(j=0; j<c; j++){
			switch (in[j]){
				case '#':
					maze[i][j]=-1;
					fire[i][j]=-1;
					break;
				case '.':
					maze[i][j]=0;
					fire[i][j]=0;
					break;
				case 'J' : 
					maze[i][j]=1;
					fire[i][j]=0;
					//user = i*c+j;
					jque[jrear++]=i*c+j;
					break;
				case 'F' :
					maze[i][j]=-2;
					fire[i][j]=-2;
					fque[frear++]=i*c+j;
					break;
			}
		}
	} //input end 
	
	flag=-1;
	while(jfront<jrear && flag==-1){
		
		// fire spread
		tmp=frear;
		while(ffront<tmp&& flag==-1){
			cur=fque[ffront++];
			for(i=0; i<4;i++){
				ni = cur/c + mov[i][0];
				nj = cur%c + mov[i][1];
				if(ni>=0 && ni<r &&nj>=0 && nj<c&& fire[ni][nj]>-1){
					fire[ni][nj]=-2;	
					fque[frear++]=ni*c+nj;
				}
			}
		}
		
		//j moves
		tmp=jrear;
		while(jfront<tmp && flag==-1){
			cur=jque[jfront++];
			if(cur/c==0 || cur/c==r-1 || cur%c==0 || cur%c==c-1){
				flag=maze[cur/c][cur%c];
				break;
			}	
			for(i=0; i<4;i++){
				ni = cur/c + mov[i][0];
				nj = cur%c + mov[i][1];
				if(ni>=0 && ni<r &&nj>=0 && nj<c && maze[ni][nj]==0 && fire[ni][nj]!=-2){
					if(ni==0 || ni==r-1 || nj==0 || nj==c-1){
						flag=maze[cur/c][cur%c]+1;
						break;
					} else{
						jque[jrear++]=ni*c+nj;
						maze[ni][nj]=maze[cur/c][cur%c]+1;	
					}		
				}
			}
		}
	}
	
	//answer out
	if(flag>0){
		printf("%d",flag);
	} else{
		printf("IMPOSSIBLE");
	}
	
	////freee mem
	for(i=0; i<r; i++){
		free(maze[i]);
        free(fire[i]);
	}
	free(maze);
    free(fire);

	return 0;
}