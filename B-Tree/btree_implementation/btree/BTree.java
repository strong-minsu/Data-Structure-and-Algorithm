package btree;
import btree.BTree_Node;
import btree.B_tree;
import java.util.*;

public class BTree {
	public static void main(String[] args) {
		//���� ����: �ð� ����
		long startTime = System.nanoTime();
		int degree = 5;
		//int degree = 7;
		//int degree = 9;
		
		B_tree t = new B_tree(degree); // B-Tree with minimum degree 5, 7, 9 �� �ּ��κп��� ���� (�Է¹ޱ� ����....
        System.out.println(String.format("B-Tree minimum degree is %d", degree));


		int [] rand_array = new int[1000];
		//���� ���� ����
		Random rand = new Random();
		int insert_number;
		int count_i = 0;
		
		//���� ����: 0~9999������ ���� �������� �迭�� �ֱ� -> remove�� ��� 
		//�Է��ڷ�� �ߺ��� �� ����.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//�迭�� ���� ���� Ʈ���� insert������ �ֱ�
				t.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        System.out.println();

		System.out.println("Traversal of tree after insert");
        t.traverse();
        System.out.println();
        
		int remove_index = 0;
		int count_r = 0;
		
		//���� ����: 500�� �׸� �����
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500���� �׸� �����ؾ��ϹǷ�, ��ġ�� ���� ���� ������ ���ǹ� ����
			if(rand_array[remove_index] != -1) {
				t.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
        System.out.println();

		System.out.println("Traversal of tree after remove");
        t.traverse();
        System.out.println();
        
        System.out.println();
        long endTime = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime-startTime));

	}

}
