public class LLRBT<T>{

    BSTree tree;
    int elements;

    class BSTree {
        T element;
        BSTree left, right;

        BSTree add(T element) {
            if (this.element == null) {
                this.element = element;
                return this;
            } else if (this.element.equals(element)) // we found it, so don't add, return null
                return null;
            else if (element.hashCode() > this.element.hashCode()) {
                if (right == null)
                    right = new BSTree();
                return right.add(element);
            } else {
                if (left == null)
                    left = new BSTree();
                return left.add(element);
            }
        }

        void add(BSTree subtree) {
            if (subtree != null && subtree.element != null) {
                add(subtree.element);
                add(subtree.left);
                add(subtree.right);
            }
        }

        BSTree find (T element) {
            if (this.element != null && element != null) {
                if (this.element.equals(element)) // found it!
                    return this;
                else if (element.hashCode() > this.element.hashCode()) {
                    if (right != null)
                        return right.find(element);
                } else {
                    if (left != null)
                        return left.find(element);
                }
            }
            return null;  // could not find it
        }

        public String toString() {
            if (element == null)
                return "";
            String rtn = "";
            if (left != null && left.element != null)
                rtn += left + ", ";

            rtn += element;

            if (right != null && right.element != null)
                rtn += ", " + right;
            return rtn;
        }

    }

    public void add(T element) {
        if (tree == null)
            tree = new BSTree();
        if (tree.add(element) != null)
            elements++;  // successfully added (ie it was not already there)
    }


    public void remove(T element) {
        BSTree root = tree.find(element);
        if (root != null) {
            BSTree l = root.left;
            BSTree r = root.right;
            root.element = null;
            root.left = null;
            root.right = null;
            tree.add(l);
            tree.add(r);
            elements--;
        }
    }


    public boolean contains(T element) {
        if (tree == null)
            return false;
        else
            return tree.find(element) != null;
    }


    public int size() {
        return elements;
    }


    public String toString() {
        if (tree == null)
            return "";
        else
            return tree.toString();
    }
}
