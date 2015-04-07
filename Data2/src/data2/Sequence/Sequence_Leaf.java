package data2.Sequence;

public class Sequence_Leaf<D extends Comparable> implements Sequence<D>, Sequenced<D> {

    public D here() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    public Sequence<D> next() {
        return this;
    }

    public String toStringSequence() {
        return "";
    }

    public Sequence<D> seq() {
        return this;
    }

}
