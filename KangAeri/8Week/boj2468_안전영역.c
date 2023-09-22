#include <stdio.h>
#include <stdlib.h>

int region[101][101];

int findnum(int n, int h){
	int k=0,m=0,group=0,tmp=0;
	int x, y;
	int** arr= (int**)malloc(sizeof(int*)*(n)); //alloc mem for arrCopy
	for(k=0;k<n;k++){
		arr[k] = (int*)malloc(sizeof(int)*(n)); 
	}
    //arr : save parentidx info for grouping
	
	//union find
	for(k=0;k<n;k++){
		for(m=0;m<n;m++){
            //flood//
			if(region[k][m]<=h) { 
				arr[k][m]=-1;
				continue;
			}
			//not flood//
			if(k>0 && (arr[k-1][m]>-1)){ // check north
				x = arr[k-1][m];
				while(arr[x/n][x%n]!=x){
					x = arr[x/n][x%n]; //get parent idx
				}	
			} else{
				x=-1; //flood space
			}
			
			if(m>0 && (arr[k][m-1]>-1)){ //check west
				y = arr[k][m-1];
				while(arr[y/n][y%n]!=y){
					y = arr[y/n][y%n]; //get parent idx
				}	
			} else{
				y=-1;
			}
			
            //if north& west both not flooddded 
			if(x>-1 && y> -1){
				if(x<y){//change with smaller parent idx
					arr[k][m] = x;
					arr[y/n][y%n] = x;			
				} else{
					arr[k][m] = y;
					arr[x/n][x%n] = y;	
				}
			} else if(x>-1){
				arr[k][m] = x;
			}else if(y>-1){
				arr[k][m] = y;
			} else{
				arr[k][m]=k*n+m;
			}	
			
		}//end for:m
	}//end for:k
	
	//count num of the safezoneGroup 
	group=0;
	for(k=0;k<n;k++){
		for(m=0;m<n;m++){
			if(arr[k][m]==(k*n+m)){
				group++;
			}
		}
	}
    
	//free mem
	for(k=0;k<n;k++){
		free(arr[k]);
	}
	free(arr);
	return group;
}//func findnum end




int main() {
	int n,i=0,j=0;
	int height=0,groupN=0,groupMax=0;
    scanf("%d", &n);
    
    for(i=0;i<n;i++){ //init
		for(j=0;j<n;j++){
			scanf("%d", &region[i][j]);
			if(height<region[i][j]){
				height = region[i][j];//max height
			}
		}
	}
	
    for(i=0;i<height;i++){//flood height++
    	groupN = findnum(n, i);
		if(groupMax<groupN){
			groupMax = groupN;
		}
	}
	printf("%d",groupMax);
	return 0;
}
