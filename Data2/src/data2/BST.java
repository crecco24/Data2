
package data2;

public interface BST <D extends Comparable>{

    int cardinality();

    boolean isEmptyHuh();

    boolean member(D elt);

    BST add(D elt);

    BST remove(D elt);

    BST union(Bag t);

    BST inter(Bag t);

    BST diff(Bag t);

    boolean equal(Bag t);

    boolean subset(BST t);

    String toString();
}
