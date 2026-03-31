#include <stdio.h>
#include <stdlib.h>

int main(void){
	int *p = (int*)malloc(sizeof(int)*10);
	for(int i = 0; i < 10; i++){
		p[i] = i;
	}
	for(int i= 0; i < 10; i++){
		printf("p[%d] = %d\n", i, p[i]);
	}
	free(p);
}
