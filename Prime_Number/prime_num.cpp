//입력받은 정수가 소수인지 판별하는 프로그램
using namespace std;
#include <stdio.h>
#include <math.h> //sqrt 함수를 사용하기 위해 필요
#include <iostream>

int is_prime1(int n) // 2 ~ N-1 까지의 수를 전부 나눠서 소수인지 판별하는 함수
{
	int i;
	for (i = 2; i < n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

int is_prime2(int n) // 2~ sqrt(N)까지의 수를 나눠서 소수인지 판별하는 함수
{
	int i, sqrn;
	sqrn = (int)sqrt(n);
	for (i = 2; i <= sqrn; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

void main(void)
{
	int n, result;
	cout << "소수인지 판별할 정수를 입력하시오: ";
	cin >> n;
	printf("\n");
	result = is_prime1(n);
	//result = is_prime2(n);
	
	//반환 받은 결과로 판단 True or False
	if (result == 1) {
		printf("%d는 소수입니다.\n", n);
	}
	else
		printf("%d는 소수가 아닙니다.\n", n);
}