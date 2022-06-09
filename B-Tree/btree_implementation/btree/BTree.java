package btree;

//�������� �۵��� (�տ��� ������ B_tree(�Լ�����)class, BTree_Node(�ʿ��� �ڷᱸ��, �Լ� ����)class) import�ϱ�
import btree.BTree_Node;
import btree.B_tree;
import java.util.*;

public class BTree {
	public static void main(String[] args) {
	
		int degree5 = 5;
		int degree7 = 7;
		int degree9 = 9;
		
		int [] rand_array = new int[1000];
		//���� ���� ���� -seed�� ���� ����ð� �м��� ����
		Random rand = new Random(10);
		
		int insert_number = 0;
		int count_i = 0;
		int remove_index = 0;
		int count_r = 0;
		
		B_tree t5 = new B_tree(degree5);
		B_tree t7 = new B_tree(degree7);
		B_tree t9 = new B_tree(degree9);// B-Tree with minimum degree 5, 7, 9 �� �ּ��κп��� ���� (�Է¹ޱ� ����....
        System.out.println(String.format("B-Tree minimum degree is %d", degree5));
        
        //���� ����: �ð� ����
      	long startTime5 = System.nanoTime();
		//���� ����: 0~9999������ ���� �������� �迭�� �ֱ� -> remove�� ��� 
		//�Է��ڷ�� �ߺ��� �� ����.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//�迭�� ���� ���� Ʈ���� insert������ �ֱ�
				t5.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        //System.out.println();

		//System.out.println("Traversal of tree after insert");
        //t.traverse();
        //System.out.println();
		
		//���� ����: 500�� �׸� �����
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500���� �׸� �����ؾ��ϹǷ�, ��ġ�� ���� ���� ������ ���ǹ� ����
			if(rand_array[remove_index] != -1) {
				t5.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
		//System.out.println();

		//System.out.println("Traversal of tree after remove");
        //t.traverse();
        //System.out.println();
        
        long endTime5 = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime5-startTime5));
        
        
        System.out.println();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //degree 7�� ���
        insert_number = 0;
		count_i = 0;
		remove_index = 0;
		count_r = 0;
        System.out.println(String.format("B-Tree minimum degree is %d", degree7));
        
        //���� ����: �ð� ����
      	long startTime7 = System.nanoTime();
		//���� ����: 0~9999������ ���� �������� �迭�� �ֱ� -> remove�� ��� 
		//�Է��ڷ�� �ߺ��� �� ����.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//�迭�� ���� ���� Ʈ���� insert������ �ֱ�
				t7.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        //System.out.println();

		//System.out.println("Traversal of tree after insert");
        //t.traverse();
        //System.out.println();
		
		//���� ����: 500�� �׸� �����
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500���� �׸� �����ؾ��ϹǷ�, ��ġ�� ���� ���� ������ ���ǹ� ����
			if(rand_array[remove_index] != -1) {
				t7.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
		//System.out.println();

		//System.out.println("Traversal of tree after remove");
        //t.traverse();
        //System.out.println();
        
        long endTime7 = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime7-startTime7));
        
        
        System.out.println();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //degree 9�� ���
        insert_number = 0;
		count_i = 0;
		remove_index = 0;
		count_r = 0;
        System.out.println(String.format("B-Tree minimum degree is %d", degree9));
        
        //���� ����: �ð� ����
      	long startTime9 = System.nanoTime();
		//���� ����: 0~9999������ ���� �������� �迭�� �ֱ� -> remove�� ��� 
		//�Է��ڷ�� �ߺ��� �� ����.
		while(count_i<1000) {
			insert_number = rand.nextInt(10000);
			if(!Arrays.asList(rand_array).contains(insert_number)) {
				rand_array[count_i] = insert_number;
				//�迭�� ���� ���� Ʈ���� insert������ �ֱ�
				t9.insert(rand_array[count_i]);
				count_i++;
			}
			else
				continue;
		}
        //System.out.println();

		//System.out.println("Traversal of tree after insert");
        //t.traverse();
        //System.out.println();
		
		//���� ����: 500�� �׸� �����
		while(count_r < 500) {
			remove_index = rand.nextInt(1000);
			//500���� �׸� �����ؾ��ϹǷ�, ��ġ�� ���� ���� ������ ���ǹ� ����
			if(rand_array[remove_index] != -1) {
				t9.remove(rand_array[remove_index]);
				rand_array[remove_index] = -1;
				count_r++;
			}
			else {
				continue;
			}
		}
		//System.out.println();

		//System.out.println("Traversal of tree after remove");
        //t.traverse();
        //System.out.println();
        
        long endTime9 = System.nanoTime();
        System.out.println(String.format("B-Tree insert, remove run time is %d ns", endTime9-startTime9));

	}

}
