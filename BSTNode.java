public class BSTNode<K extends Comparable<K>, T>{
    private K key;
    private T value;
    private BSTNode<K,T> left;
    private BSTNode<K,T> right;
    public BSTNode(K key, T value) {
        this.key = key;
        this.value = value;
    }
    public BSTNode<K,T> getLeft() {return left;}
    public void setLeft(BSTNode<K,T> left) {this.left = left;}
    public BSTNode<K,T> getRight() {return right;}
    public void setRight(BSTNode<K,T> right) {this.right = right;}
    public K getKey() {return key;}
    public void setKey(K key) {this.key = key;}
    public T getValue() {return value;}
    public void setValue(T value) {this.value = value;}

    public  BSTNode<K,T> search(BSTNode<K,T> r, K toFind){
        if(r == null){
            return null;
        }
        if(toFind.compareTo(r.getKey()) ==0){
            return r;
        }
        else if(toFind.compareTo(r.getKey()) < 0){
            return search(r.getLeft(), toFind);
        }
        return search(r.getRight(), toFind);
    }

    // Fill in the following methods

    // insert a node with key k and value v into the
    // BinarySearch Tree rooted at r
    public  void insert(BSTNode<K,T> r, K k, T v){
        if (r == null){
            return;
        }

        if (k.compareTo(r.getKey()) < 0){
            if(r.getLeft() == null){
                r.setLeft(new BSTNode<K,T>(k,v));
            }
            else{
                insert(r.getLeft(), k, v);
            }
        }
        else if (k.compareTo(r.getKey()) > 0){
            if(r.getRight() == null){
                r.setRight(new BSTNode<K,T>(k,v));
            }
            else{
                insert(r.getRight(), k, v);
            }
        }
        else {
            r.setValue(v);
        }
    }
    //return the node with the minimum key value
    public BSTNode<K, T> getMin(BSTNode<K, T> r){
        if (r == null){
            return null;
        }
        if (r.getLeft() == null){
            return r;
        }
        return getMin(r.getLeft());
    }

    //return the node with the maximum key value
    public BSTNode<K,T> getMax(BSTNode<K,T> r){
        if (r == null){
            return null;
        }
        if (r.getRight() == null){
            return r;
        }
        return getMax(r.getRight());
    }


}
