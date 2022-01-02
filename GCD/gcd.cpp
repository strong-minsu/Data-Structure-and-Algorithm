//u, v �� ���� �ִ� ������� ���ϴ� ���α׷� 
using namespace std;
#include <stdio.h>
#include <iostream>

int get_gcd(int u, int v)  //greatest common divisor ���ϴ� �Լ� 
{
	//��Ŭ���� (������ �̿���)
	int t;
	while (u > 0) {
		if (u < v) {
			t = u; u = v; v = t;
		}
		u = u - v;
	}
	return v;
}

int gcd_modulus(int u, int v)  //greatest common divisor ���ϴ� �Լ� 
{
	//������ ��Ŭ���� (�������� �̿���)
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
	cout << "���� u�� ���� �Է��ϼ���: ";
	cin >> u;
	printf("\n");
	cout << "���� v�� ���� �Է��ϼ���: ";
	cin >> v;
	printf("\n");
	printf("%d�� %d�� gcd ����: ", u, v);
	//gcd = get_gcd(u, v); //��Ŭ���� �˰���
	gcd = gcd_modulus(u, v); //��Ŭ���� �˰��� ���� (�������� �̿��� gcd���ϱ�)
	//gcd = gcd_recursion(u, v); //������ ��Ŭ���� ����Լ��� �̿��� gcd���ϱ�
	printf("%d\n", gcd);
}