#include <stdio.h>
#include <stdlib.h>

int city[201][201];
int plan[1000];

int main() {
    int n=0, m=0, flag=0, a=0,b=0,i=0,j=0;
    int* par;
    
    scanf("%d", &n);
    scanf("%d", &m);
    par = (int*)malloc(sizeof(int)*(n+1));
    for(i=0;i<=n;i++){
        par[i] =i;
    }
    for(i=1;i<=n;i++){
       for(j=1;j<=n;j++){
           scanf("%d", &city[i][j]);
           if(city[i][j]==1){
                a=par[i];
                while(par[a]!=a){
                    a = par[a];
                }
                b=par[j];
                while(par[b]!=b){
                    b = par[b];
                }
                if(b>a){
                    par[b] =a;
                } else{
                    par[a] =b;
                }
           }
        }
    }
    
    flag=1;
    scanf("%d",&plan[0]);
    b=par[plan[0]];
    while(par[b]!=b){
        b = par[b];
    }
    for(i=1;i<m;i++){
        scanf("%d",&plan[i]);
        a=par[plan[i]];
        while(par[a]!=a){
            a = par[a];
        }
        if(b!=a){
             printf("NO\n");
             flag=0;
             break;
        }
    }
    if(flag==1){
        printf("YES\n");
    }
    
    free(par);
    return 0;
}
