# <p align="center"> Compiler - NewLang </p>

L'obiettivo di questo progetto è la creazione di un compilatore per il linguaggio NewLang. Sviluppato come parte dell'esame di Compilatori del corso di Laurea Magistrale in Software Engineering and IT Management, il compilatore si occupa della traduzione di programmi scritti in NewLang in programmi scritti nel linguaggio C.


## Scelte progettuali 

Il compilatore qui presente è stato sviluppato in conformità alle specifiche fornite; tutte le situazioni non chiaramente definite in tali specifiche sono state affrontate e gestite di conseguenza.

### Visite degli alberi
L'elaborazione delle diverse fasi della compilazione, tra cui la *costruzione dell'albero sintattico*, la *gestione dello scoping*, il *type checking* e la *generazione del codice intermedio C*, è affidata a quattro classi separate che implementano il pattern Visitor. Questa implementazione consente di visitare tutti i nodi dell'albero in modo efficiente.

La suddivisione delle fasi distintive in classi separate ha favorito la creazione di un codice più manutenibile e flessibile.

#### 1. MySyntaxTree
Si occupa di generare il syntax tree e di fornire una sua rappresentazione  in XML nel file.xml

#### 2. MyScopingVisitor
Si occupa gestire lo scoping del linguaggio, grazie allo scoping vistor è stato possibile gestire l'uso prima della dichiarazione sia di una variabile che di una funzione.
I costrutti che permettono la creazione di un nuovo scope in NewLang sono:
  - while
  - for
  - if 
  - else
  - dichiarazione di funzione
  - intero programma
  
  tutti i costrutti che hanno al loro interno un body delegano a quest'ultimo la creazione e la gestione dello scope.
  
  Inoltre viene anche data la possibilità di avere in scope innestati dichiarazioni di variabili già esistenti in scope superiori (Most-cloesly-nested rule)


#### 3. MyTypeVisitor
Questo visitor si occupa di controllare i tipi seconodo le specifiche del type system del linguaggio ed eventuali errori semantici

In questa classe viene implementata l'inferenza di tipo

#### 4. MyCTranslatorVisitor
Questo visitor si occupa di tradurre il codice scritto in NewLang in codice C che verrà poi compilato tramite GCC.

### Tipi e conversioni

Il linguaggio NewLang dispone dei seguenti tipi: *real*, *integer*, *char*, *string*, *bool*.
 Il linguaggio proposto non prevede casting impliciti per nessun tipo, fatta eccezione per il tipo integer.
 Infatti qualsiasi espressione di tipo integer può essere memorizzata o passata a una variabile di tipo real (attenzione, non è vero il contrario).
 
 ##### Inferenza di tipo:
 il linguaggio NewLang prevede la possibilità di dichiarare una variabile di tipo 
var e contestualmente assegnarle un valore costante (es. var x << 10). L’analisi semantica in 
questo caso deve inferire il tipo intero di x dal tipo della costante 10 ed inserire (x, int) nel type 
environment corrente.

### Funzioni Write, WriteLn e Read
Queste funziioni permettono di leggere e scrivere da standard output
  - Write:()-->;
    - Permette di comunicare con l'utente tramite console, la funzione accetta almeno un parametro o più intervallati da ","
  - Writeln:()-->!; 
    - Uguale alla precedente ma dopo la scrittura va a capo;
  - Read: 
    - <variabile><--; 
    - <variabile><-- <"STRINGA">;
      - La Read è presente in due versioni, quella dove viene semplicemente prelvato da console un input oppure quella in cui c'è un'aggiunta di una stringa che funge da label.
     
### Vincoli 
I vincoli che seguono sono stati aggiunti per migliorare l'utilizzo e la comprensione:

  1. Non è possibile dichiarare una funzione con il nome "main" neanche la funzione start:
  
## Get Started
In primo luogo bidogna:
  - clonare la seguente repository
  - avviare il compile di maven
  

Il programma prende in input un file .txt contente codice in linguaggio NewLang, siccessivamente genera un file.xml che conterrà la rappresentazione dell'albero sintattico, in seguito genera un file .c e in automatico se si è su windows o su mac aprirà un prompt dei comandi avviando gcc e il programma sarà avviato.


```diff
-  importante che GCC sia installato sulla macchina!
-  importante che maven sia installato sulla macchina!
-  importante che java sia installato sulla macchina

```
### Esempio di programma scritto in NewLang
```c

start: def test(): void{

    integer condizione << 1, operazione ;

    while condizione = 1 loop {

       ("quale operazione aritmetica vuoi scegliere?") -->!;
       ("se vuoi fare la somma scrivi 1 ") -->!;
       ("se vuoi fare la sottrazione scrivi 2 ") -->!;
       ("se vuoi fare la moltiplicazione scrivi 3 ") -->!;
       ("se vuoi fare la divisione  scrivi 4 ") -->!;
        ("se vuoi fare la potenza  scrivi 5 ") -->!;
       operazione <-- "inserisci la tua scelta qui (1,2,3,4,5)";

       if operazione = 1 or operazione = 2 or operazione = 3 or operazione =  4 or operazione =  5 then {
           elaboraScelta(operazione);

       }

       ("vuoi continuare? scrivi 1, vuoi stopparti scrivi 0") -->!;
       condizione <--;
   }
}


def elaboraScelta(integer operazione ): void{

    real numA, numB, result;
    numA, numB, result << 0.0, 0.0, 0.0;

    numA <-- "inserisci il primo numero";
    numB <-- "inserisci il secondo numero";

    if operazione = 1 then {
               somma(numA, numB, result);
               ("il risultato della somma  è :  " , result)-->!;
           }
    if operazione = 2 then {
            sottrazione(numA,numB, result);
            ("il risultato della sottrazione  è :  " , result)-->!;
        }
        if operazione = 3 then {
            moltiplicazione(numA, numB, result);
            ("il risultato della moltiplicazione  è :  " , result)-->!;
        }
        if operazione = 4 then {
            if numB <> 0 then {
                divisione(numA, numB, result);
                ("il risultato della divisione  è :  " , result)-->!;
            }else{
                ("Divisione impossibile, il denomitaore non può essere 0") -->!;
            }
        }
        if operazione = 5 then {
                        potenza(numA, numB, result);
                        ("il risultato della potenza  è :  " , result)-->!;
                    }


}

def somma( real numA, numB | out real result): void {
   result << numA + numB;

}

def sottrazione( real numA, numB | out real result): void {
     result << numA - numB;
}
def divisione( real numA, numB | out real result): void {

     result << numA / numB;

}
def moltiplicazione( real numA, numB | out real result): void {
     result << numA * numB;
}
def potenza( real numA, numB | out real result): void {
     result << numA^numB;
}

```
## La sua traduzione in C

```c
#include <stdio.h>

#include <stdlib.h>

#include <string.h>

#include <stdbool.h>

#include <math.h>
 //prototipi funzioni
void elaboraScelta(int operazione);
void somma(float numA, float numB, float * result);
void sottrazione(float numA, float numB, float * result);
void divisione(float numA, float numB, float * result);
void moltiplicazione(float numA, float numB, float * result);
void potenza(float numA, float numB, float * result);
void test();

char * conversioneFloat(float number) {

  char * buf = malloc(10 * sizeof(char));
  sprintf(buf, "%f", number);
  return buf;
}
char * conversioneInt(int number) {
  char * buf = malloc(10 * sizeof(char));
  sprintf(buf, "%d", number);
  return buf;
}
char supporto[100];

void elaboraScelta(int operazione) {
  float numA, numB, result;
  numA = 0.0;
  numB = 0.0;
  result = 0.0;
  printf("inserisci il primo numero");
  scanf("%f", & numA);
  printf("inserisci il secondo numero");
  scanf("%f", & numB);
  if (operazione == 1) {
    somma(numA, numB, & result);
    printf("%s %f \n", "il risultato della somma  è :  ", result);
  }
  if (operazione == 2) {
    sottrazione(numA, numB, & result);
    printf("%s %f \n", "il risultato della sottrazione  è :  ", result);
  }
  if (operazione == 3) {
    moltiplicazione(numA, numB, & result);
    printf("%s %f \n", "il risultato della moltiplicazione  è :  ", result);
  }
  if (operazione == 4) {
    if (numB != 0) {
      divisione(numA, numB, & result);
      printf("%s %f \n", "il risultato della divisione  è :  ", result);
    } else {
      printf("%s \n", "Divisione impossibile, il denomitaore non può essere 0");
    }
  }
  if (operazione == 5) {
    potenza(numA, numB, & result);
    printf("%s %f \n", "il risultato della potenza  è :  ", result);
  }
}
void somma(float numA, float numB, float * result) {
  * result = numA + numB;
}
void sottrazione(float numA, float numB, float * result) {
  * result = numA - numB;
}
void divisione(float numA, float numB, float * result) {
  * result = numA / numB;
}
void moltiplicazione(float numA, float numB, float * result) {
  * result = numA * numB;
}
void potenza(float numA, float numB, float * result) {
  * result = pow(numA, numB);
}
void test() {
  int operazione, condizione = 1;
  while (condizione == 1) {
    printf("%s \n", "quale operazione aritmetica vuoi scegliere?");
    printf("%s \n", "se vuoi fare la somma scrivi 1 ");
    printf("%s \n", "se vuoi fare la sottrazione scrivi 2 ");
    printf("%s \n", "se vuoi fare la moltiplicazione scrivi 3 ");
    printf("%s \n", "se vuoi fare la divisione  scrivi 4 ");
    printf("%s \n", "se vuoi fare la potenza  scrivi 5 ");
    printf("inserisci la tua scelta qui (1,2,3,4,5)");
    scanf("%d", & operazione);
    if (operazione == 1 || operazione == 2 || operazione == 3 || operazione == 4 || operazione == 5) {
      elaboraScelta(operazione);
    }
    printf("%s \n", "vuoi continuare? scrivi 1, vuoi stopparti scrivi 0");
    scanf("%d", & condizione);
  }
}
int main() {
  int intero = 0;
  char carattere = ' ';
  float float1 = 0;
  char * stringa = "";
  bool booleano = false;
  test();
  return 0;
}

```





  
      
    
 
