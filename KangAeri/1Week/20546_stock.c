#include <stdio.h>
#include <stdlib.h>

int main(){
    int i=0,cash;
    int stock[14];
    int p1,p2,stock1=0,stock2=0; //jun1 song2

    scanf("%d",&cash);
    for(i=0;i<14;i++){
        scanf("%d",&stock[i]);
    }
    
    //p1
    p1=cash;
    stock1=0;
    for(i=0;i<14;i++){
        if(p1/stock[i]>0){
            stock1+=(p1/stock[i]);
            p1-=(p1/stock[i])*stock[i];
        }
    }
    p1+= stock[13]*stock1;
    
    
    //p2
    p2=cash;
    stock2=0;
    for(i=0;i<14;i++){
        if(i>2 && stock[i-3]<stock[i-2] && stock[i-2]<stock[i-1] &&stock[i-1]<stock[i]){
            p2+=stock[i]*stock2;
            stock2=0;
        }else if(i>2 && stock[i-3]>stock[i-2] && stock[i-2]>stock[i-1] &&stock[i-1]>stock[i]){
            stock2+=p2/stock[i];
            p2-=(p2/stock[i])*stock[i];
        }
    }
    p2+= stock[13]*stock2;
    
    if(p1>p2){
        printf("BNP\n");
    } else if(p1<p2){
        printf("TIMING\n");
    } else{
        printf("SAMESAME\n");
    }

    
    return 0;
}
