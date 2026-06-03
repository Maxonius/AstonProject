package test;

import collection.CustomArrayList;

public class CustomArrayListTest {
    public static void main(String[] args) {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("BMW");
        list.add("Audi");
        list.add("Porsche");

        System.out.println(list.size());

        System.out.println(list.get(2));

        list.remove(1);

        System.out.println(list.size());

        System.out.println(list.get(1));

        System.out.println(list.isEmpty());

        try {
            System.out.println(list.get(2));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }

        System.out.println(list.size());

        System.out.println(list.get(16));
    }
}
