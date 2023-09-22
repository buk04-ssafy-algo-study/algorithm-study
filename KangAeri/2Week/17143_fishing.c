#include <stdio.h>

int mov[4][2] = {{-1,0},{1,0},{0,1},{0,-1}};

typedef struct shark{
   int v;//velocity
   int d;//direction
   int s;//size
}shark;

struct shark state[100][100];
struct shark deck[100][100];

int main(){
   int r,c,m,got=0;
   int vel=0, dir=0, size=0;
   int i,j,ni,nj;
   int k,p,t;

   scanf("%d %d %d", &r, &c, &m);
 
   for(i=0;i<r;i++){
      for(j=0;j<c;j++){
         deck[i][j].s=0;
         deck[i][j].d=-1;
         deck[i][j].v=0;
      }
   }
   
   for (k= 0; k <m; k++){
      scanf("%d %d %d %d %d", &i, &j, &vel, &dir,&size);
      deck[i-1][j-1].v = vel;
      deck[i-1][j-1].d = dir-1;
      deck[i-1][j-1].s = size;     

   }
   
   for(k=0;k<c;k++){
      //copy to state
      for(i=0;i<r;i++){
         for(j=0;j<c;j++){
            state[i][j] = deck[i][j];
            deck[i][j].s=0;
         }
      }

      //catch shark
      for(p=0;p<r;p++){
         if(state[p][k].s>0){
            got+=state[p][k].s;
            state[p][k].s=0;
            break;
         }
      } 
   
      //move sharks
      for(i=0;i<r;i++){
         for(j=0;j<c;j++){
            if(state[i][j].s<=0){
            	continue;
            }       
           ni= i + (mov[state[i][j].d][0] * state[i][j].v);
		 nj= j + (mov[state[i][j].d][1] * state[i][j].v);
		   dir=state[i][j].d;
		    
           if(ni<0){
              ni*=-1;
              if((ni/(r-1))%2==0){
                 ni%=(r-1);
                 dir=1;
              } else{
                 ni=r-1-(ni%(r-1));
                 dir=0;
              }
           } else if(ni>=r){
              if((ni/(r-1))%2==0){
                 ni%=(r-1);
                 dir=1;
              } else{
                 ni=r-1-(ni%(r-1));
                 dir=0;
              }
           }
             
           //col mov  
           if(nj<0){
              nj*=-1;
              if((nj/(c-1))%2==0){
                 nj%=(c-1);
                 dir=2;
              } else{
                 nj=c-1-(nj%(c-1));
                 dir=3;
              }      
           } else if(nj>=c){
              if((nj/(c-1))%2==0){
                 nj%=(c-1);
                 dir=2;
              } else{
                 nj=c-1-(nj%(c-1));
                 dir=3;
              } 
           }
             
           if(deck[ni][nj].s <state[i][j].s){
               deck[ni][nj] = state[i][j];
               deck[ni][nj].d = dir;
           }
           state[i][j].s = 0;            
         }//end for:j
      }//end for:i
	
   } //end for:k
   
   printf("%d\n",got);

   return 0;
}
