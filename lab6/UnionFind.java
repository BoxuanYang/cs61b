public class UnionFind {

    // TODO - Add instance variables?
    private int[] tree;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind (int n) {
        tree = new int[n];
        for(int i = 0; i < n; i++){
            tree[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int v1) throws IllegalArgumentException{
        if(v1 < 0 || v1 >= tree.length)
            throw new IllegalArgumentException("This is not valid");

    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {//OK
        return -tree[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {//OK
        return tree[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if(sizeOf(v1) <= sizeOf(v2)){
            tree[root2] -= sizeOf(v1);
            tree[root1] = root2;
        } else{
            tree[root1] -= sizeOf(v1);
            tree[root2] = root1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {//OK
        // TODO
        validate(v1);

        int root  = v1;
        while(tree[root] >= 0){
            root = tree[root];
        }

        int tmp = v1;
        int sth = v1;
        while(tree[tmp] != root){
            sth = tmp;
            tmp = tree[tmp];
            tree[sth] = root;
        }
        return root;
    }

}