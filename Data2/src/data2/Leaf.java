
package data2;

import data2.Sequence.Sequence;
import data2.Sequence.Sequenced;
import data2.Sequence.Sequence_Branch;
import data2.Sequence.Sequence_Leaf;
import data2.Sequence.Sequence_Cat;

public class Leaf<D extends Comparable> implements Bag<D> {

    Leaf() {
    }

    public Bag Empty() {
        return new Leaf();
    }
    
    public int getMult(D elt){
        return 0;
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public boolean member(D elt) {
        return false;
    }

    public Bag add(D elt) {
        return new Branch(new Leaf(), elt, new Leaf());
    }
    
    public Bag addSome(D elt, int n){
        return new Branch(new Leaf(), elt, new Leaf(), n);   
    }

    public Bag remove(D elt) {
        return this;
    }
    
    public Bag removeSome(D elt, int n){
        return this;
    }
    
    public Bag removeAll(D elt){
        return this;
    }

    public Bag union(Bag t) {
        return t;
    }

    public Bag inter(Bag t) {
        return this;
    }

    public Bag diff(Bag t) {
        return t;
    }

    public boolean equal(Bag t) {
        return t.isEmptyHuh();
    }

    public boolean subset(Bag t) {
        return true;
    }

    public String toString() {
        return toStringS(this.seq());
    }
    
    public String toStringS(Sequence<D> thingy){
        return "";
    }

    public Sequence<D> seq() {
        return new Sequence_Leaf();
    }
}
