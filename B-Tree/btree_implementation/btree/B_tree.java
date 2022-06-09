package btree;
import btree.BTree_Node;
//함수, 생성자 Btree_Node에서 구현된 자료구조를 가지고 구현
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

    // 키 찾는 함수
    public BTree_Node search(int key){
        return root == null ? null : root.search(key);
    }
    
    //insert 함수
    public void insert(int key){

        if (root == null){

            root = new BTree_Node(MinDeg,true);
            root.keys[0] = key;
            root.num = 1;
        }
        else {
            // 루트 노드가 가득 차면 트리가 높아짐
            if (root.num == 2*MinDeg-1){
            	BTree_Node s = new BTree_Node(MinDeg,false);
                // 이전 루트 노드가 새 루트 노드의 하위 노드가 됨
                s.children[0] = root;
                // 이전 루트 노드를 분리하고 새 노드에 키를 부여함
                s.splitChild(0,root);
                // 새 루트 노드에는 하위 노드가 2개 있음, 오래된 것을 저쪽으로 옮김
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

    //remove 함수
    public void remove(int key){
        if (root == null){
            System.out.println("The tree is empty");
            return;
        }

        root.remove(key);

        if (root.num == 0){ // 루트 노드에 키가 0인 경우
            // 하위 항목이 있으면 첫 번째 하위 항목이 새 루트로 지정되고 아니면 루트 노드를 null로 설정함
            if (root.isLeaf)
                root = null;
            else
                root = root.children[0];
        }
    }
}
