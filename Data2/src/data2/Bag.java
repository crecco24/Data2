package data2;

public interface Bag <D extends Comparable>{

    int cardinality();

    boolean isEmptyHuh();

    boolean member(D elt);

    Bag add(D elt);

    Bag remove(D elt);

    Bag union(Bag t);

    Bag inter(Bag t);

    Bag diff(Bag t);

    boolean equal(Bag t);

    boolean subset(Bag t);

    String toString();
}