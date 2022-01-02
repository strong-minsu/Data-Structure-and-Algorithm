//u, v 두 수의 최대 공약수를 구하는 프로그램 
using namespace std;
#include <stdio.h>
#include <iostream>

int get_gcd(int u, int v)  //greatest common divisor 구하는 함수 
{
	//유클리드 (뺄셈을 이용함)
	int t;
	while (u > 0) {
		if (u < v) {
			t = u; u = v; v = t;
		}
		u = u - v;
	}
	return v;
}

int gcd_modulus(int u, int v)  //greatest common divisor 구하는 함수 
{
	//개선된 유클리드 (나머지를 이용함)
	int t;
	while (v > 0)
	{
		t = u % v;
		u = v;
		v = t;
	}
	return u;
}

int gcd_recursion(int u, int v)
{
	if (v == 0) {
		return u;
	}
	else {
		return gcd_recursion(v, u & v);
	}
}

void main(void)
{
	int u, v;
	int gcd;
	cout << "정수 u의 값을 입력하세요: ";
	cin >> u;
	printf("\n");
	cout << "정수 v의 값을 입력하세요: ";
	cin >> v;
	printf("\n");
	printf("%d와 %d의 gcd 값은: ", u, v);
	//gcd = get_gcd(u, v); //유클리드 알고리즘
	gcd = gcd_modulus(u, v); //유클리드 알고리즘 개선 (나머지를 이용해 gcd구하기)
	//gcd = gcd_recursion(u, v); //개선된 유클리드 재귀함수를 이용해 gcd구하기
	printf("%d\n", gcd);
}