#include <stdio.h>

struct me{
	char name;
	int age;
	double height;
};

int main(void){
	int a[3] = {1,2,3};
	printf("%d\n",a);
	
	char b[6] = "hello";
	printf("%c\n",b[4]);
	
	char *c = b;
	printf("%c\n", c[0]);
	
	{
		struct me i;
		i.name = "kimtaeho";
		i.age = 22;
		i.height = 171.1;
		printf("%c, %d, %f", i.name, i.age, i.height);
	}

}
