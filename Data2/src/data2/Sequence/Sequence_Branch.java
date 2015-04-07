package data2.Sequence;

public class Sequence_Branch<D extends Comparable> implements Sequence<D>, Sequenced<D> {


        D here;
        int multiplicity;
        Sequence<D> next;

        public Sequence_Branch(D here, int multiplicity, Sequence<D> next) {
            this.here = here;
            this.multiplicity = multiplicity;
            this.next = next;
        }

        public boolean hasNext() {
            return true;
        }

        public D here() {
            return this.here;
        }

        public Sequence<D> next() {
            if (multiplicity > 1) {
                return new Sequence_Branch(here, multiplicity - 1, next);
            } else {
                return next;
            }
        }

        public Sequence<D> seq() {
            return this;
        }

        public String toStringSequence() {
            return "" + this.here;
        }

    }
