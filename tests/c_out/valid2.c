#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
void esercizio();
float sommac(int,int,float,char**);
void stampa(char*);
char* intToString(int var){
    char* int_str = malloc(256);
    sprintf(int_str, "%d", var);
    return int_str;
}

char* doubleToString(double var){
    char* double_str = malloc(256);
    sprintf(double_str, "%f", var);
    return double_str;
}

char* boolToString(int var){
    if (var == 1){
        return "true";
    }
    if (var == 0){
        return "false";
    }
    return "";
}

char* concat(char* s1, char* i) {
    char* s = malloc(256);
    sprintf(s, "%s%s", s1, i);
    return s;
}
int c = 1;
void esercizio(){
char* taglia = "";
char* ans1 = "";
int a = 1;
int b = 2.2;
int x = 3;
char* ans = "no";
float risultato = sommac(a,x,b,&taglia);
stampa("la somma  incrementata  è "); 
	printf("%s",taglia );
stampa(" ed è pari a "); 
	printf("%f",risultato );
	printf("%s","vuoi continuare? (si/no) - inserisci due volte la risposta" );
ans = malloc(256); 
	scanf("%s",ans);
ans1 = malloc(256); 
	scanf("%s",ans1);
while(!strcmp(ans,"si")){
	printf("inserisci un intero:");
	scanf("%d",&a);
	printf("inserisci un reale:");
	scanf("%d",&b);
risultato = sommac(a,x,b,&taglia);
stampa("la somma  incrementata  è "); 
	printf("%s",taglia );
stampa(" ed è pari a "); 
	printf("%f",risultato );
	printf("vuoi continuare? (si/no):");
ans = malloc(256); 
	scanf("%s",ans);
}
	printf("%s","" );
	printf("%s","ciao" );}
float sommac(int a,int d,float b,char** size){
float result;
result = a + b + c + d;
if(result > 100){
	char* valore = "grande";
*size = valore;
}
else{
	char* valore = "piccola";
*size = valore;
}
return result;
}
void stampa(char* messaggio){
int a;
int i;
	int x;
	for(x = 4; x >= 1;x--){
			printf("%s","" );
	}
	printf("%s",messaggio );
}
int main(int argc, char** argv){ 
stampa("");
return (EXIT_SUCCESS);
}
