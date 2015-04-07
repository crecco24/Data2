package data2;

import data2.Sequence.Sequence;
import data2.Sequence.Sequenced;

public interface Bag <D extends Comparable> extends Sequenced<D>{
    
    int getMult(D elt);

    int cardinality();

    boolean isEmptyHuh();

    boolean member(D elt);

    Bag add(D elt);
    
    Bag addSome(D elt, int n);
    
    Bag remove(D elt);

    Bag removeSome(D elt, int n);
    
    Bag removeAll(D elt);

    Bag union(Bag t);

    Bag inter(Bag t);

    Bag diff(Bag t);

    boolean equal(Bag t);

    boolean subset(Bag t);

    String toString();
}