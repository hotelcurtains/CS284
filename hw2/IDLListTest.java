package hw2;
import java.util.ArrayList;
public class IDLListTest {
    public static void main(String[] args) {
        IDLList<Integer> test = new IDLList<Integer>();
        test.add(4);
        test.add(7);
        test.add(0,5);
        test.append(12);
        System.out.println(test); // expected: 5, 4, 7, 12
        System.out.println(test.get(1)); // expected: 4
        System.out.println(test.getHead()); // expected: 5
        System.out.println(test.getLast()); // expected: 12
        System.out.println(test.size()); // expected: 4

        System.out.println(test.remove()); // expected: 5
        System.out.println(test); // expected: 4, 7, 12
        System.out.println(test.size()); // expected: 3

        System.out.println(test.removeLast()); // expected: 12
        System.out.println(test); // expected: 4, 7
        System.out.println(test.size()); // expected: 2
        
    }
}
