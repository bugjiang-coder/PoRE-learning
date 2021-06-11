#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
char name[0x10] = {0};

void overflowme();
int main(){
	char buf[0x100];
	char buf1[0x100];
	setbuf(stdout,buf);
	setbuf(stdin,buf1);
	puts("Welcome to pore21");
	puts("What's your student id?");
	char magic[8] = "fffffff";
	char id[0x10];
	fflush(stdout);
	read(0, &id, 0x18);
	if(*(long long*)&magic != 0xdeadbeef)
	{
		puts("Emmmm...");
		exit(0);
	}
	puts("What's your name?");
	overflowme();
	return 0;

}

void overflowme(){
	char buffer[0x20];
	fflush(stdout);
	read(0, name, 0x10);
	if(strcmp(name, "pore\n") != 0)
	{
		system("echo Uh oh~");
		exit(0);
	}
	else
	{
		puts("Try to overflow the buffer and get the shell");
	}
	read(0,&buffer,0x100);
}

