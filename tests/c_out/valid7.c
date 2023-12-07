#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
int succ_fibonacci(int);
float potenza(float,float);
float divisione(float,float);
float prodotto(float,float);
float differenza(float,float);
float somma(float,float);
void esempio();
int menu();
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
int succ_fibonacci(int i){
if(i < 0){
	return 0;
}
else{
	if(i == 0){
	return 0;
}
else{
	if(i == 1){
	return 1;
}
}
}
return succ_fibonacci(i - 1) + succ_fibonacci(i - 2);
}
float potenza(float a,float b){
return pow((float)(a), (float)(b));
}
float divisione(float a,float b){
return a / b;
}
float prodotto(float a,float b){
return a * b;
}
float differenza(float a,float b){
return a - b;
}
float somma(float a,float b){
return a + b;
}
void esempio(){
int op;
float risultato;
float a;
float b;
int c;
int fibRes;
int comando = -1;
while(comando != 0){
	printf("%s","1) Visualizza Menù" );
	printf("%s","0) Termina" );
	printf("Inserisci comando:");
	scanf("%d",&comando);
if(comando == 1){
	op = menu();
if(op != 7){
		printf("Inserisci il primo numero reale:");
	scanf("%f",&a);
	printf("Inserisci il secondo numero reale:");
	scanf("%f",&b);
}
else{
		printf("Inserisci un intero:");
	scanf("%d",&c);
}
if(op == 2){
	risultato = somma(a,b);
}
else{
	if(op == 3){
	risultato = differenza(a,b);
}
else{
	if(op == 4){
	risultato = prodotto(a,b);
}
else{
	if(op == 5){
	risultato = divisione(a,b);
}
else{
	if(op == 6){
	risultato = potenza(a,b);
}
else{
	fibRes = succ_fibonacci(c);
}
}
}
}
}
if(op != 7){
		printf("%s","Il risultato dell'operazione scelta è :" );
	printf("%f",risultato );
}
else{
		printf("%s","Il risultato dell'operazione scelta è :" );
	printf("%d",fibRes );
}
}
}
	printf("%s","Ciao" );}
int main(int argc, char** argv){ 
esempio();
return (EXIT_SUCCESS);
}
int menu(){
int op;
	printf("%s","--------Menu--------" );
	printf("%s","2) Addizione" );
	printf("%s","3) Sottrazione" );
	printf("%s","4) Moltiplicazione" );
	printf("%s","5) Divisione" );
	printf("%s","6) Potenza" );
	printf("%s","7) Fibonacci" );
	printf("Inserisci operazione:");
	scanf("%d",&op);
while(op < 2 || op > 7){
	printf("Operazione non valida [2-7], inserisci operazione:");
	scanf("%d",&op);
}
return op;
}
