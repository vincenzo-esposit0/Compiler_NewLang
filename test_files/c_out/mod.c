#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
int myfun(int,int);
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
int a;
int b = 1;
int c = 2;
int myfun(int i,int s){
return i * s;
}
void esercizio(){
a = myfun (c * 4 + 2,b + 1)+myfun (4,2)+myfun (5,4);
	printf("%d\n",a);
}
int main(int argc, char** argv){ 
esercizio();
return (EXIT_SUCCESS);
}
