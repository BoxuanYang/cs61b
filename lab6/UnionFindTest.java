import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void testSizeOf() {
        UnionFind tree = new UnionFind(15);
        tree.union(1, 2);

        int i;
        for(i = 0; i < 15; ++i) {
            if (i != 1 && i != 2) {
                assertTrue(tree.sizeOf(i) == 1);
            } else {
                assertTrue(tree.sizeOf(i) == 2);
            }
        }

        tree.union(3, 4);

        for(i = 0; i < 15; ++i) {
            if (i != 3 && i != 4 && i != 1 && i != 2) {
                assertTrue(tree.sizeOf(i) == 1);
            } else {
                assertTrue(tree.sizeOf(i) == 2);
            }
        }

        tree.union(1, 3);

        for(i = 0; i < 15; ++i) {
            if (i != 1 && i != 2 && i != 3 && i != 4) {
                assertTrue(tree.sizeOf(i) == 1);
            } else {
                assertTrue(tree.sizeOf(i) == 4);
            }
        }

    }

    @Test
    public void testParent() {
        UnionFind tree = new UnionFind(15);
        tree.union(1, 2);


        for(int i = 0; i < 15; i++) {
            if (i == 1) {
                //System.out.println("The value of tree.parent(" + i + ") is: " + tree.parent(i));
                assertTrue(tree.parent(i) == 2);
                continue;
            }

            if (i == 2) {
                //System.out.println("The value of tree.parent(" + i + ") is: " + tree.parent(i));
                assertTrue(tree.parent(i) == -2);
                continue;
            }

            else {
                //System.out.println("The value of tree.parent(" + i + ") is: " + tree.parent(i));
                assertEquals(-1, tree.parent(i));
            }

        }

        tree.union(3, 4);
        for(int i = 0; i < 15; ++i) {
            if (i == 1) {
                assertTrue(tree.parent(i) == 2);
                continue;
            }

            if (i == 2) {
                assertTrue(tree.parent(i) == -2);
                continue;
            }

            if (i == 3) {
                assertTrue(tree.parent(i) == 4);
                continue;
            }

            if (i == 4) {
                assertTrue(tree.parent(i) == -2);
                continue;

            } else {
                System.out.println("The value of tree.parent(" + i + ") is: " + tree.parent(i));
                assertTrue(tree.parent(i) == -1);
            }
        }

        tree.union(1, 3);
        for(int i = 0; i < 15; ++i) {
            if (i == 1) {
                assertTrue(tree.parent(i) == 2);
                continue;
            }

            if (i == 2) {
                assertTrue(tree.parent(i) == 4);
                continue;
            }

            if (i == 3) {
                assertTrue(tree.parent(i) == 4);
                continue;
            }

            if (i == 4) {
                assertTrue(tree.parent(i) == -4);
                continue;
            } else {
                assertTrue(tree.parent(i) == -1);
            }
        }

    }

    @Test
    public void testFind() {
        UnionFind tree = new UnionFind(15);
        tree.union(1, 3);
        assertTrue(tree.find(1) == 3);
        assertTrue(tree.find(6) == 6);

        tree.union(3, 4);
        assertTrue(tree.find(4) == 3);

        tree.union(5, 6);
        tree.union(1, 5);
        assertTrue(tree.find(5) == 3);
        assertTrue(tree.find(6) == 3);
    }

}