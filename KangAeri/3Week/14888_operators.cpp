#include <stdio.h>

int numseq[11]; //input numer sequence
int opers[10]; //input operator sequence
int oporder[10]; //operator permutation
int chk[10]; //isSelected?

//global vars
int n=0, max=-1000000001, min=1000000001; 

void perm(int cnt){
	int j=0,k=0,res=0;
	if(cnt==n-1){ //*oporder full.
		//compute with *oporder
		res=numseq[0];
		for(j=0;j<n-1;j++){
			switch(oporder[j]){
				case 1:
					res+=numseq[j+1];
					break;
				case 2:
					res-=numseq[j+1];
					break;
				case 3:
					res*=numseq[j+1];
					break;
				case 4:
					res/=numseq[j+1];
					break;	
			}
		}
		if(res<min){
			min=res;
		}
		if(res>max){
			max=res;
		}		
		return;
	} else{
		for(k=0; k<n-1; k++){
			if(chk[k]==1){
				continue;
			} else{
				oporder[cnt]=opers[k];
				chk[k]=1;
				perm(cnt+1);
				chk[k]=0;
			}
		}
	}
	
} //func.permutation end

///////////////////////////////////////////////////////////////////////
int main() {
	int plus=0, minus=0, mul=0,div=0, res=0, i=0,j=0;
	
    scanf("%d", &n);

    for(i=0;i<n;i++){
		scanf("%d", &numseq[i]);
	}
    scanf("%d %d %d %d", &plus, &minus, &mul,&div);//add1 subtract2 multiply3 division4
    j=0;
	i=0;
	while(j<plus){
		opers[i++]=1;
		j++;
	}
	j=0;
	while(j<minus){
		opers[i++]=2;
		j++;
	}
	j=0;
	while(j<mul){
		opers[i++]=3;
		j++;
	}
	j=0;
	while(j<div){
		opers[i++]=4;
		j++;
	}
	
	perm(0); //compute
	printf("%d\n%d",max,min);
	
	return 0;
}
