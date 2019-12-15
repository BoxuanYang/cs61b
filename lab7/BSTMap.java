import edu.princeton.cs.algs4.BST;
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    Node root;
    int size;

    public class Node{
        K key;
        V value;
        Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = right = null;
        }

        public void put(K key, V value) {
            //it's already there, do nothing
            if(this.get(key) != null)
                return;

            //if it is not there
            else{
                //if the root's key is less than key
                if(this.key.compareTo(key) < 0){
                    //if the root has no right children
                    if(this.right == null){
                        size++;
                        this.right = new Node(key, value);
                    }
                    //if the root has right children
                    else
                        this.right.put(key, value);
                }


                //if the root's key is greater than key
                else if(this.key.compareTo(key) > 0){
                    //if the root has no left children
                    if(this.left == null){
                        this.left = new Node(key, value);
                        size++;
                    }

                    //if the root has left children
                    else
                        this.left.put(key, value);
                }
            }
        }


        public V get(K key) {
            //if the root's key is equal to key
            if(this.key == key)
                return this.value;

            //if the root's key is less than key
            else if(this.key.compareTo(key) < 0){
                //if it does not have right child
                if(this.right == null)
                    return null;
                //if it has right child
                else
                    return this.right.get(key);
            }

            //if the root's key is greater than key
            else if (this.key.compareTo(key) > 0){
                //if it does not have left child
                if(this.left == null)
                    return null;
                    //if it has left child
                else
                    return this.left.get(key);
            }
            return null;
        }

        @Override
        public String toString(){
            if(this.key == null)
                return "";
            else if(this.left == null && this.right == null)
                return this.key.toString();
            else
                return this.left.toString() + " " + this.key.toString() + " " + this.right.toString();
        }
    }

    public BSTMap(K key, V value){
        root = new Node(key , value);
        size = 1;
    }

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if(root == null)
            return false;
        else
            return root.get(key) != null;
    }

    @Override
    public V get(K key) {
        if(root == null)
            return null;
        else
            return root.get(key);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(root == null){
            root = new Node(key, value);
            size = 1;
        }
        else
            root.put(key, value);
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        String str = root.toString();
        System.out.println(str);
    }
}
