#include <stdio.h>
#include <stdlib.h>
#define size 101

int mov[4][2] = {{0,1}, {-1,0}, {0,-1}, {1,0}};
int x,y,d,g,top;
int map[size][size];
int stk[size*size];


int main(){
	int n=0,i=0,j=0,k=0,ans=0;
	int dir=0;
	
	scanf("%d", &n);
	for(i=0; i<size;i++){
		for(j=0; j<size; j++){
			map[i][j]=0;
		}
	}
	
	for(i=0; i<n;i++){
		scanf("%d %d %d %d", &y, &x, &d, &g); //x,y:starting point
		top=0;
		stk[top++]=d;
		map[x][y]=1;
		x+=mov[d][0];
		y+=mov[d][1];
		map[x][y]=1;
		for(j=0; j<g; j++){
    		for(k=top-1; k>=0; k--){
    			dir = (stk[k]+1)%4;
    			x+=mov[dir][0];
    			y+=mov[dir][1];
    			map[x][y]=1;
    			stk[top++]=dir;
    		}
	    }
	}
	///////////////////////////////////////////////
	ans=0;
    for (i = 0; i <size-1; i++){
        for (j = 0; j <size-1; j++){
            if (map[i][j]== 1 && map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j + 1]==1){
                ans++;
            }
        }
    }
    /*
    for(i=0; i<size;i++){
		for(j=0; j<size; j++){
			printf("%d",map[i][j]);
		}
		printf("\n");
	}*/
	printf("%d\n",ans);
	
	return 0;
}




