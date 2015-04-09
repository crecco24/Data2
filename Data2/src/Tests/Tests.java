package Tests;

import data2.Bag;
import data2.Leaf;
import java.util.Random;

public class Tests<D extends Comparable> {

    int testEmptyIsEmpty = 0;
    int testIsEmptyCardinality = 0;
    int testRemoveGetMult = 0;
    int testRemoveAddEqual = 0;
    int testAddMember = 0;
    int testMemberUnion = 0;
    int testMultUnion = 0;
    int testUnionSubset = 0;
    int testSubsetDiff = 0;
    int testDiffInterEmptyEqual = 0;
    int testEqualUnionInter = 0;
    int testInterEmpty = 0;
    int tests = 1000;

    public Tests() {
    }

    public int randInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }

    public Bag randIntBag(int numElts) {
        if (numElts == 0) {
            return new Leaf();
        } else {
            return randIntBag(numElts - 1).addSome(randInt(1, 50), randInt(1, 10));
        }
    }

    public String randString() {
        String[] stringArray;
        stringArray = new String[26];
        stringArray[0] = "a";
        stringArray[1] = "b";
        stringArray[2] = "c";
        stringArray[3] = "d";
        stringArray[4] = "e";
        stringArray[5] = "f";
        stringArray[6] = "g";
        stringArray[7] = "h";
        stringArray[8] = "i";
        stringArray[9] = "j";
        stringArray[10] = "k";
        stringArray[11] = "l";
        stringArray[12] = "m";
        stringArray[13] = "n";
        stringArray[14] = "o";
        stringArray[15] = "p";
        stringArray[16] = "q";
        stringArray[17] = "r";
        stringArray[18] = "s";
        stringArray[19] = "t";
        stringArray[20] = "u";
        stringArray[21] = "v";
        stringArray[22] = "w";
        stringArray[23] = "x";
        stringArray[24] = "y";
        stringArray[25] = "z";

        return stringArray[randInt(0, 25)];
    }

    public Bag randStringBag(int numElts) {
        if (numElts == 0) {
            return new Leaf();
        } else {
            return randStringBag(numElts - 1).addSome(randString(), randInt(1, 10));
        }
    }

    public boolean randBool() {
        int bool = randInt(0, 1);

        if (bool == 0) {
            return false;
        } else if (bool == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Bag randBoolBag(int numElts) {
        if (numElts == 0) {
            return new Leaf();
        } else {
            return randBoolBag(numElts - 1).addSome(randBool(), randInt(1, 10));
        }
    }

    public Bag randBag(int numElts) {
        int chooser = randInt(1, 3);

        if (chooser == 1) {
            return randIntBag(numElts);
        }

        if (chooser == 2) {
            return randStringBag(numElts);
        }

        if (chooser == 3) {
            return randBoolBag(numElts);
        } else {
            return randBoolBag(numElts);
        }
    }

    public void testEmptyIsEmpty(int count) throws Exception {
        if (count == 0) {
            Bag t = new Leaf();
            if (!t.isEmptyHuh()) {
                throw new Exception("Failure: An empty bag is not empty");
            }
        } else {
            int numElts = randInt(1, 10);
            Bag u = randBag(numElts);
            if (u.isEmptyHuh()) {
                throw new Exception("Failure: A nonempty set is empty!");
            }

        }
        testEmptyIsEmpty++;
    }

    public void testIsEmptyCardinality(Bag t) throws Exception {
        if (!t.isEmptyHuh() && (t.cardinality() == 0)) {
            throw new Exception("Failure: Nonempty set and had a non-zero cardinality");
        }
        if (t.isEmptyHuh() && (t.cardinality() != 0)) {
            throw new Exception("Failure: Empty with not cardinality = 0");
        }
        testIsEmptyCardinality++;
    }

    public void testRemoveGetMult(Bag t, int x) throws Exception {
        int c = t.remove(x).cardinality();
        // Either something was removed -> and it decreased the tree by one
        // Or the thing wasn't there to begin with, and nothing was removed
        if (t.getMult(x) >= 1 && c != t.cardinality() - 1) {
            throw new Exception("Failure - we remove x from a bag, but cardinality did not decrease by 1");
        }
        if (t.getMult(x) == 0 && c != t.cardinality()) {
            throw new Exception("The object wasn't there to be removed by cardinality didn't remain the same");
        } else {
        }
        testRemoveGetMult++;
    }

    public void testRemoveAddEqual(Bag t) throws Exception {
        int rand = randInt(0, 50);
        Bag u = t.add(rand);
        if (u.getMult(rand) - 1 != t.getMult(rand)) {
            throw new Exception("Failure: After adding an item, the count for that item"
                    + " should increase by 1");
        }
        u = u.remove(rand);
        if (u.getMult(rand) != t.getMult(rand)) {
            throw new Exception("Failure: After removing the same item, the count for that item"
                    + " should be back where it was originally");
        }
        if (!t.equal(u)) {
            throw new Exception("Failure: The tree changed after adding and removing same item");
        }
        testRemoveAddEqual++;
    }

    public void testAddMember(Bag t, int x, int y) throws Exception {
        Boolean bool = t.add(x).member(y);
        if (bool && x == y) {
            //Success! X = Y and it's in the tree
        } else {
            if (bool && t.member(y)) {
                //"Success! Y was a member of y beforehand and "
                //"it's in the tree"
            } else {
                if (!bool && ((x != y && !t.member(y)))) {
//          Success! X != Y and is not a member of the original"
//                    + " tree and therefore is not a member of this tree"
                } else {
                    throw new Exception("Failure! Problem with member or add! X: " + x + " Y: " + y + "bool: " + bool + "mem: " + !t.member(y));
                }
            }
        }
        testAddMember++;
    }

    public void testMemberUnion(Bag t, Bag r, int x) throws Exception {
        Boolean bool = t.union(r).member(x);
        if (bool && t.member(x)) {
            //"Success! X is a member of the t tree"
            if (t.getMult(x) <= 0) {
                throw new Exception("Failure! If it's a member of the tree, the count"
                        + " shouldn't be zero");
            }
        } else {
            if (bool && r.member(x)) {
                //Success! X is a member of the r tree
                // Here we indirectly test that it must be positive!
                if (r.getMult(x) <= 0) {
                    throw new Exception("Failure! If it's a member of the tree, the count"
                            + " shouldn't be zero");
                }
            } else {
                if (!bool && (!r.member(x) && !t.member(x))) {
                    //"Success! X is not a member of the right or left "
                    //      + "tree and therefore not a part of the union

                    // There is no option for a negative number; must be zero!
                    if (t.getMult(x) != 0 || r.getMult(x) != 0 || t.union(r).getMult(x) != 0) {
                        throw new Exception("Failure! If it is not a member of the tree, "
                                + "the count"
                                + " should be zero");
                    }
                } else {
                    throw new Exception("Failure! Problem with member or union!");
                }
            }
        }
        testMemberUnion++;
    }

    // A more precise property for union & cardinality:
    // (count (union u v) x) = (+ (count u x) (count v x))
    // Nice that there is only one case
    public void testMultUnion(Bag t, Bag r, int x) throws Exception {
        if (t.union(r).getMult(x) != ((t.getMult(x)) + (r.getMult(x)))) {
            throw new Exception("Failure! The union of two trees should have the "
                    + "same count of x as the two trees count of x added together b/c "
                    + " concept of addition");
        }
        testMultUnion++;
    }

    public void testUnionSubset(Bag t, Bag r) throws Exception {
        Bag unionLR = t.union(r);
        if (!(t.subset(unionLR) && r.subset(unionLR))) {
            throw new Exception("Failure!The left and right trees are not subsets"
                    + " of their union");
        }
        testUnionSubset++;
    }

    public void testSubsetDiff(Bag t, Bag r) throws Exception {
        Bag tDiffR = t.diff(r);
        Bag rDiffT = r.diff(t);
        // If R - T = R && T - R = T 
        // Then, they must be completely disjoint and not be subsets of each other
        if (tDiffR.equal(r) && rDiffT.equal(t)) {
            if (!r.isEmptyHuh() && !t.isEmptyHuh()) {
                if (r.subset(t) || t.subset(r)) {
                    throw new Exception("Problem with Subset and Difference characteristics");
                }
            }
        }

        testSubsetDiff++;
    }

    // This test also says something is wrong with diff because all of the other
    // tests -> besides the ones using diff -> work
    public void testDiffInterEmptyEqual(Bag t, Bag r) throws Exception {
        // t inter r = the empty set iff t - r = t
        if ((t.inter(r)).equal(new Leaf()) && r.diff(t).equal(t)) {
//            "Success! A inter B = the empty set iff A - B = A"
        } else if (!(t.inter(r)).equal(new Leaf()) && !r.diff(t).equal(t)) {
//            "Success! A inter B != the empty set iff A - B != A"
        } else {
            throw new Exception("Failure! Wrong: diff, inter, empty, or equal");
        }
        testDiffInterEmptyEqual++;
    }

    public void testEqualUnionInter(Bag t, Bag r) throws Exception {
        if ((t.union(r).cardinality() == (t.inter(r)).cardinality() * 2) && t.equal(r)) {
//            "Success! The two trees are equal and 2* inter = union"
        } else if ((t.union(r).cardinality() != t.inter(r).cardinality() * 2) && !t.equal(r)) {
//            "Success! They are not equal and 2 * inter != union"
        } else {
            throw new Exception("Failure! Wrong: equal, union, or diff");
        }
        testEqualUnionInter++;
    }

    // The Identity Property for Inter
    public void testInterEmpty(Bag t) throws Exception {
        Boolean bool = t.inter(new Leaf()).equal(new Leaf());
        // If the intersection of any tree with the empty set
        // equals the empty set...
        if (bool && !t.isEmptyHuh()) {
//           "Success! The intersection of a non-empty"
//                    + " set with the "
//                    + "empty set is just the empty set!"
        } else if (bool && t.isEmptyHuh()) {
//            "Success! The intersection of an empty set"
//                    + " with the empty set is just the empty set!"
        } else {
            throw new Exception("Failure! Wrong: inter, equal, isEmptyHuh, or "
                    + "empty()");
        }
        testInterEmpty++;
    }

    public void runAll() throws Exception {
        // "Testing for Empty() & IsEmptyHuh?: "
        System.out.println();
        for (int i = 0; i < tests; i++) {
            int randomInt = randInt(0, 1);
            testEmptyIsEmpty(randomInt);
        }
        System.out.println("Testing for Empty() & IsEmptyHuh?: " + testEmptyIsEmpty + " times.");

        System.out.println();
        // Testing for Cardinality & IsEmptyHuh
        for (int i = 0; i < tests; i++) {
            int len = randInt(0, 10);
            Bag l = randIntBag(len);
            testIsEmptyCardinality(l);
        }
        System.out.println("Testing: IsEmptyHuh? & Cardinality: " + testIsEmptyCardinality + " times");


        System.out.println();

        // Testing Cardinality & Remove
        for (int i = 0; i < tests; i++) {
            int elt = randInt(0, 50);
            int len = randInt(0, 10);
            Bag l = randIntBag(len);
            testRemoveGetMult(l, elt);
        }
        System.out.println("Testing: Cardinality & Remove: " + testRemoveGetMult + " times");

        // Testing: Remove (EQUAL) &  Add"
//        System.out.println();
//
//        for (int i = 0; i < tests; i++) {
//            int len = randInt(0, 10);
//            Bag l = randIntBag(len);
//            testRemoveAddEqual(l);
//        }
//        System.out.println("Testing: Remove (EQUAL) &  Add: " + testRemoveAddEqual + " times");

        System.out.println();
        // Testing for Add & Member 
        // member (add t x) y = true <-> x = y \/ member t y = true
        for (int i = 0; i < tests; i++) {
            int elt = randInt(0, 10);
            int elt2 = randInt(0, 10);
            int len = randInt(0, 10);
            Bag l = randIntBag(len);
            testAddMember(l, elt, elt2);
        }
        System.out.println("Testing: Add & Member: " + testAddMember + " times");

        System.out.println();
        // Testing for Union & Member
        // member (union s s') x = true <-> member s x = true \/ member s' x = true
        for (int i = 0; i < tests; i++) {
            int elt = randInt(0, 10);
            int len = randInt(0, 10);
            int len2 = randInt(0, 10);
            Bag l = randIntBag(len);
            Bag r = randIntBag(len2);
            testMemberUnion(l, r, elt);
        }
        System.out.println("Testing: Member & Union: " + testMemberUnion + " times");

//        System.out.println();
//        //Check for union & get count
//        for (int i = 0; i < tests; i++) {
//            int elt = randInt(0, 10);
//            int len = randInt(0, 10);
//            int len2 = randInt(0, 10);
//            Bag l = randIntBag(len);
//            Bag r = randIntBag(len2);
//            testMultUnion(l, r, elt);
//        }
//        System.out.println("Testing: Union & Get Count: " + testMultUnion + " times");

//        System.out.println();
//        // Testing Union & Subset 
//        for (int i = 0; i < tests; i++) {
//            int len = randInt(0, 10);
//            int len2 = randInt(0, 10);
//            Bag l = randIntBag(len);
//            Bag r = randIntBag(len2);
//            testUnionSubset(l, r);
//        }
//        System.out.println("Testing: Union & Subset: " + testUnionSubset + " times");

        System.out.println();
        // Testing Subset & Diff 
        for (int i = 0; i < tests; i++) {
            int len = randInt(0, 10);
            int len2 = randInt(0, 10);
            Bag l = randIntBag(len);
            Bag r = randIntBag(len2);
            testSubsetDiff(l, r);
        }
        System.out.println("Testing: Subset & Diff: " + testSubsetDiff + " times");

        System.out.println();
        // Testing: Diff (EMPTY & Inter) & Equal
        for (int i = 0; i < tests; i++) {
            int len = randInt(0, 10);
            int len2 = randInt(0, 10);
            Bag l = randIntBag(len);
            Bag r = randIntBag(len2);
            //Adding a random Number so we can get all the cases
            int randomNumber = randInt(0, 4);
            if (randomNumber == 3) {
                testDiffInterEmptyEqual(l, l);
            }
            testDiffInterEmptyEqual(l, r);
        }
        System.out.println("Testing: Diff (EMPTY & INTER) & Equal: " + testDiffInterEmptyEqual + " times");

        System.out.println();
        // Testing: Equal (UNION) & Inter
        for (int i = 0; i < tests; i++) {
            int len = randInt(0, 10);
            int len2 = randInt(0, 10);
            Bag l = randIntBag(len);
            Bag r = randIntBag(len2);
            //Adding a random Number so we can get all the cases
            int randomNumber = randInt(0, 4);
            if (randomNumber == 3) {
                testEqualUnionInter(l, l);
            }
            testEqualUnionInter(l, r);
        }
        System.out.println("Testing: Equal (UNION) & Inter: " + testEqualUnionInter + " times");

        System.out.println();
        // Testing Inter & Empty() 
        for (int i = 0; i < tests; i++) {
            int len = randInt(0, 10);
            Bag l = randIntBag(len);
            testInterEmpty(l);
        }
        System.out.println("Testing Inter & Empty(): " + testInterEmpty + " times");
        System.out.println("================================");

        for (int i = 0; i < 25; i++) {
            int len = randInt(0, 10);
            Bag l = randIntBag(len);
            //System.out.println("Sequencing: " + l.stringIt());
        }
    }
}
