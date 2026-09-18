package derry.s7;

import derry.s5.Person;

public class KtBase132 {
    public static void main(String[] args) {
        Person1 person11 = new Person1();
        for (String name : person11.getNames()) {
            System.out.println(name);
        }
    }
}
