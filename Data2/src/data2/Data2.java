
package data2;

public class Data2 {

    public static void main(String[] args) {

        Leaf leaf = new Leaf();
        Branch b1 = new Branch(leaf, 1, leaf);
        Branch b3 = new Branch(leaf, 3, leaf);
        Branch b7 = new Branch(leaf, 7, leaf);
        Branch b9 = new Branch(leaf, 9, leaf);
        Branch b2 = new Branch(b1, 2, b3);
        Branch b8 = new Branch(b7, 8, b9);
        Branch b5 = new Branch(b2, 5, b8);

        // should be:
        //           5
        //          / \
        //         2   8
        //       /  \ /  \
        //      1   3 7   9
        System.out.println("b5 should contain 1,2,3,5,7,8,9 - Does contain: {" + b5 + "}");
        System.out.println("b5 cardinality should be 7- is actually " + b5.cardinality());
        System.out.println("b2 cardinality should be 3- is actually " + b2.cardinality());
        System.out.println("testing b5 emptiness. Should be false- is actually " + b5.isEmptyHuh());
        System.out.println("testing a leaf's empitness. Should be true- is actually " + leaf.isEmptyHuh());
        System.out.println("Testing membership of 3 in 5. Should be true- is actually " + b5.member(3));
        System.out.println("testing membership of 3 in 2. Should be true- is actually " + b2.member(3));
        System.out.println("Testing membership of 3 in 7. Should be false- is actually " + b7.member(3));

        // Once a number is added to a set, it should have membership of that set.
        System.out.println("Testing membership of 4 in new set once 4 is added."
                + "should return true- actually returns " + b5.add(4).member(4));
       
        // Once a number is removed from a set, it should no longer have membership
        // within that set
        System.out.println("Testing membership of 3 within a new set once 3 is"
                + " removed. Should return false- actually returns "
                + b5.remove(3).member(3));
        
        System.out.println("Testing the equality between b5 and the union of"
                + "b2 and b8 with the addition of 5. Should be true-"
                + "is actually " + b5.equal(b2.union(b8).add(5)));
        
        System.out.println("Union of b2 and b8 should contain 3. Should"
                + "return true- is actually " + b2.union(b8).member(3));
        
        System.out.println("Difference of b5 and b2 should be equal to b8"
                + " add 5. Should return true. Is actually " + 
                b2.diff(b5).equal(b8.add(5)));
        
        Bag bTest = b2.diff(b5);
        System.out.println("Cardinality of diff is " + bTest.cardinality() + " " + bTest.toString());
        
        System.out.println("b5 remove 5 should be equal to b2 union b8."
                + "Should return true. Is actually-"
                + b5.remove(5).equal(b2.union(b8)));
        
        System.out.println("b5 is not equal to b3. Should return false- is "
                + "actually " + b5.equal(b3));
        
        System.out.println("b2 is a subset of b5. Should return true. Is actually " + b2.subset(b5));
        
        System.out.println("b5 is not a subset of b2. Should return false. Is actually " + b5.subset(b2));
    }
}

