
package data2;

public class Leaf<D extends Comparable> implements Bag {

    Leaf() {
    }

    public Bag Empty() {
        return new Leaf();
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public boolean member(int elt) {
        return false;
    }

    public Bag add(D elt) {
        return new Branch(new Leaf(), elt, new Leaf());
    }

    public Bag remove(D elt) {
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
        return "";
    }
}
