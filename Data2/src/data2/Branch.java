package data2;

public class Branch<D extends Comparable> implements Bag {

    Bag<D> left;
    D key;
    Bag<D> right;
    int multiplicity;

    Branch(Bag<D> left, D key, Bag<D> right, int multiplicity) {
        this.left = left;
        this.right = right;
        this.key.equals(key);
        this.multiplicity = multiplicity;
    }

    Branch(Bag<D> left, D key, Bag<D> right) {
        this.left = left;
        this.right = right;
        this.key.equals(key);
        this.multiplicity = 1;
    }

    Branch(D key, int multiplicity) {
        this.left = new Leaf();
        this.right = new Leaf();
        this.key.equals(key);
        this.multiplicity = multiplicity;
    }

    Branch(D key) {
        this.left = new Leaf();
        this.right = new Leaf();
        this.key.equals(key);
        this.multiplicity = 1;
    }

    public int cardinality() {
            return this.multiplicity + this.left.cardinality() + this.right.cardinality();
    }

    public boolean isEmptyHuh() {
        return false;
    }

    public boolean member(D elt) {
        if (this.key.equals(elt)) {
            return true;
        } else {
            return this.left.member(elt) || this.right.member(elt);
        }
    }

    public Bag add(D elt) {
        if (this.member(elt)) {
            return this;
        } else {
            if (elt.compareTo(this.key) < 0) {
                return new Branch(this.left.add(elt), this.key, right);
            } else {
                return new Branch(left, this.key, this.right.add(elt));
            }
        }
    }

    public Bag remove(D elt) {
        if (elt.equals(this.key)) {
            return left.union(right);
        } else {
            if (elt.compareTo(this.key) < 0) {
                return new Branch(this.left.remove(elt), this.key, this.right);
            } else {
                return new Branch(this.left, this.key, this.right.remove(elt));
            }
        }
    }

    public Bag union(Bag t) {
        Bag returner = t.union(this.left).union(this.right).add(this.key);
        return returner;
    }

    public Bag inter(Bag t) {
        Bag returner = new Leaf();
        if (t.member(this.key)) {
            returner.add(this.key).union(left.inter(t)).union(right.inter(t));
        } else {
            this.remove(key).inter(t);
        }
        return returner;
    }

    public Bag diff(Bag t) {
        Bag returner = new Leaf();
        if (!t.member(this.key)) {
            returner.add(this.key).union(left.diff(t)).union(right.inter(t));
        } else {
            this.remove(key).diff(t);
        }
        return returner;
    }

    public boolean equal(Bag t) {
        return this.subset(t) && t.subset(this);
    }

    public boolean subset(Bag t) {
        return t.member(this.key) && left.subset(t) && right.subset(t);
    }

    public String toString() {
        return left.toString() + key + " " + right.toString();
    }
}
