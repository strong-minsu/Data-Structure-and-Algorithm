package btree;
import btree.BTree_Node;
//�Լ�, ������ Btree_Node���� ������ �ڷᱸ���� ������ ����
public class B_tree {
	BTree_Node root;
    int MinDeg;

    public B_tree(int deg){
        this.root = null;
        this.MinDeg = deg;
    }

    public void traverse(){
        if (root != null){
            root.traverse();
        }
    }

    // Ű ã�� �Լ�
    public BTree_Node search(int key){
        return root == null ? null : root.search(key);
    }
    
    //insert �Լ�
    public void insert(int key){

        if (root == null){

            root = new BTree_Node(MinDeg,true);
            root.keys[0] = key;
            root.num = 1;
        }
        else {
            // ��Ʈ ��尡 ���� ���� Ʈ���� ������
            if (root.num == 2*MinDeg-1){
            	BTree_Node s = new BTree_Node(MinDeg,false);
                // ���� ��Ʈ ��尡 �� ��Ʈ ����� ���� ��尡 ��
                s.children[0] = root;
                // ���� ��Ʈ ��带 �и��ϰ� �� ��忡 Ű�� �ο���
                s.splitChild(0,root);
                // �� ��Ʈ ��忡�� ���� ��尡 2�� ����, ������ ���� �������� �ű�
                int i = 0;
                if (s.keys[0]< key)
                    i++;
                s.children[i].insertNotFull(key);

                root = s;
            }
            else
                root.insertNotFull(key);
        }
    }

    //remove �Լ�
    public void remove(int key){
        if (root == null){
            System.out.println("The tree is empty");
            return;
        }

        root.remove(key);

        if (root.num == 0){ // ��Ʈ ��忡 Ű�� 0�� ���
            // ���� �׸��� ������ ù ��° ���� �׸��� �� ��Ʈ�� �����ǰ� �ƴϸ� ��Ʈ ��带 null�� ������
            if (root.isLeaf)
                root = null;
            else
                root = root.children[0];
        }
    }
}
