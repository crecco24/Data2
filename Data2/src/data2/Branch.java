package data2;

import data2.Sequence.Sequence;
import data2.Sequence.Sequenced;
import data2.Sequence.Sequence_Branch;
import data2.Sequence.Sequence_Leaf;
import data2.Sequence.Sequence_Cat;

public class Branch<D extends Comparable> implements Bag<D> {

    Bag<D> left;
    D key;
    Bag<D> right;
    int multiplicity;

    Branch(Bag<D> left, D key, Bag<D> right, int multiplicity) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.multiplicity = multiplicity;
    }

    Branch(Bag<D> left, D key, Bag<D> right) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.multiplicity = 1;
    }

    Branch(D key, int multiplicity) {
        this.left = new Leaf();
        this.right = new Leaf();
        this.key = key;
        this.multiplicity = multiplicity;
    }

    Branch(D key) {
        this.left = new Leaf();
        this.right = new Leaf();
        this.key = key;
        this.multiplicity = 1;
    }

    public int getMult(D elt) {
        if (elt.compareTo(key) == 0) {
            return multiplicity;
        } else if (elt.compareTo(key) < 0) {
            return this.left.getMult(elt);
        } else {
            return this.right.getMult(elt);
        }
    }

    public int cardinality() {
        return this.multiplicity + this.left.cardinality() + this.right.cardinality();
    }

    public boolean isEmptyHuh() {
        return this.multiplicity == 0 && left.isEmptyHuh() && right.isEmptyHuh();
    }

    public boolean member(D elt) {
        if (this.key.equals(elt) && this.multiplicity > 0) {
            return true;
        } else {
            return this.left.member(elt) || this.right.member(elt);
        }
    }

    public Bag addSome(D elt, int n) {
        if (this.member(elt)) {
            this.multiplicity = multiplicity + n;
            return this;
        } else {
            if (elt.compareTo(this.key) < 0) {
                return new Branch(this.left.addSome(elt, n), this.key, right);
            } else {
                return new Branch(left, this.key, this.right.addSome(elt, n));
            }
        }
    }

    public Bag add(D elt) {
        return addSome(elt, 1);
    }

    public Bag removeSome(D elt, int n) {
        if (elt.equals(this.key)) {
            int max = Math.max(0, this.multiplicity - n);
            return new Branch(this.left, this.key, this.right, max);
        } else {
            if (elt.compareTo(this.key) < 0) {
                return new Branch(this.left.removeSome(elt, n), this.key, this.right);
            } else {
                return new Branch(this.left, this.key, this.right.removeSome(elt, n));
            }
        }
    }

    public Bag remove(D elt) {
        return removeSome(elt, 1);
    }

    public Bag removeAll(D elt) {
        int n = getMult(elt);
        return removeSome(elt, n);
    }

    public Bag union(Bag t) {
        Bag returner = t.union(this.left).union(this.right).addSome(this.key, this.multiplicity);
        return returner;
    }

    public Bag inter(Bag t) {
        if (t.member(key)) {
            if (t.getMult(key) > this.getMult(key)) {
                return new Branch(left.inter(t), key, right.inter(t), this.getMult(key));
            } else {
                return new Branch(left.inter(t), key, right.inter(t), t.getMult(key));
            }
        } else {
            return left.inter(t).union(right.inter(t));
        }
    }

    public Bag diff(Bag t) {
        Bag removed = t.removeSome(key, this.getMult(key));
        return (left.union(right)).diff(removed);
    }

    public boolean equal(Bag t) {
        return this.subset(t) && t.subset(this);
    }

    public boolean subset(Bag t) {
        return (t.getMult(key) >= this.getMult(key)) && left.subset(t) && right.subset(t);
    }

    public String toString() {
        return toStringS(this.seq());
    }

    public String toStringS(Sequence<D> thingy) {
        StringBuffer all = new StringBuffer("");
        while (thingy.hasNext()) {
            all.append(thingy.next().toStringSequence());
            all.append(" ");
            thingy = thingy.next();
        }
        return all.toString();
    }

    public Sequence<D> seq() {
        return new Sequence_Branch(key, multiplicity, (new Sequence_Cat(left.seq(), right.seq())));
    }
}
