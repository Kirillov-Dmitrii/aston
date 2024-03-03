import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(9, 11);

        System.out.println(list.get(0));
        System.out.println(list.get(9));
        System.out.println(list.get(10));

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ListIterator<Integer> listIterator = list1.listIterator();

        for (int i = 0; i < list1.size(); i++) {
            
        }
    }
}
