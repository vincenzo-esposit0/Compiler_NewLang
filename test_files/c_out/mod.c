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
int i;
int a = 1;
int b = 2;
switch (i)
{case 1 : a = a + 1;

	printf("%d\n",a * 4);

break;
 case 2 : a = b + 3;

break;
 case 3 : 	printf("%d\n",3);

break;
 }}
int main(int argc, char** argv){ 
esercizio();
return (EXIT_SUCCESS);
}
