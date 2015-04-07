package data2.Sequence;

public class Sequence_Cat<D extends Comparable> implements Sequence<D>, Sequenced<D> {

    Sequence<D> left;
    Sequence<D> right;

    public Sequence_Cat(Sequence<D> lleft, Sequence<D> right) {
        this.left = left;
        this.right = right;
    }

    public boolean hasNext() {
        return this.left.hasNext() || this.right.hasNext();
    }

    public D here() {
        if (this.left.hasNext()) {
            return this.left.here();
        } else {
            return this.right.here();
        }
    }

    public Sequence<D> next() {
        if (this.left.hasNext()) {
            return new Sequence_Cat(this.left.next(), this.right);
        } else {
            return this.right.next();
        }
    }

    public String toStringSequence() {
        return this.left.toStringSequence() + " " + this.right.toStringSequence();
    }

    public Sequence<D> seq() {
        return this;
    }
}
