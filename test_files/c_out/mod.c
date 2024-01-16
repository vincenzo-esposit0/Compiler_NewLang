#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
void esercizio();
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
void esercizio(){
int a = 1;
int b = 2.2;
int x = 3;
{ 
int i = 1;
int j = 1;

do{ 
	printf("%d\n",j);
	printf("%s\n",",");
	printf("%d\n",i);
	printf("%s\n","ciao,");
j = j - 1;
i = i + 1;
} while(i < 20 && j > -20); 
}
int main(int argc, char** argv){ 
esercizio();
return (EXIT_SUCCESS);
}
