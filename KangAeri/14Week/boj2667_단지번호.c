#include <stdio.h>
#include <stdlib.h>
int cnt[25*25];
void merge(int l, int r){
    int i=0, x=0, y=0, p=(r+l)/2;
    int* tmp = (int*)malloc(sizeof(int)*(r-l+1));
    x=l;
    y=p+1; 
    while(x<=p && y<=r){
        if(cnt[x]>cnt[y]){
            tmp[i++]=cnt[x++];
        } else {
            tmp[i++]= cnt[y++];
    	}
    }
    while(x<=p){
        tmp[i++]=cnt[x++];
    }
    while(y<=r){
        tmp[i++]=cnt[y++];
    }
    for(x=l; x<=r; x++){
        cnt[x]=tmp[x-l];
    }
    free(tmp);
}

void msort(int l, int r){
    int p=(l+r)/2;
    if(l>=r){
        return;
    }
    msort(l,p);
    msort(p+1,r);
    merge(l,r);
} 

int main(){
	int i=0,j=0,n=0,no=0,ahead=0,bhead=0;
	int map[26][26];
	int head[25*25];
	char in[26];
	scanf("%d",&n);
	for(i=0;i<n*n;i++){
		head[i]=i;
		cnt[i]=0;
	}
	//input scan& union find
	for(i=0;i<n;i++){
		scanf("%s",in);
		for(j=0;j<n;j++){
			map[i][j]=in[j]-48;
			if(map[i][j]==0){
				head[i*n+j]=0;
				continue;
			}
			if(i>0 && map[i-1][j]>0 && j>0 && map[i][j-1]>0){
				ahead=(i-1)*n+j;
				bhead=i*n+j-1;
				while(head[ahead]!=ahead){
					ahead=head[ahead];
				}
				while(head[bhead]!=bhead){
					bhead=head[bhead];
				}
				if(ahead!=bhead){
					cnt[ahead]+=cnt[bhead];
					cnt[bhead]=0;
					head[bhead]=ahead;
					no--;
				}
				head[i*n+j]=ahead;
				cnt[ahead]++;
			} else if(i>0 && map[i-1][j]>0){
				ahead=(i-1)*n+j;
				while(head[ahead]!=ahead){
					ahead=head[ahead];
				}
				head[i*n+j]=head[ahead];
				cnt[ahead]++;
			} else if(j>0 && map[i][j-1]>0){
				bhead=i*n+j-1;
				while(head[bhead]!=bhead){
					bhead=head[bhead];
				}
				head[i*n+j]=head[bhead];
				cnt[bhead]++;
			} else{
				cnt[i*n+j]++;
				no++;
			}
		}
	}
//result
	msort(0,n*n-1);
	printf("%d\n",no);
	for(i=no-1;i>=0;i--){
		printf("%d\n",cnt[i]);
	}
	
	return 0;
}
