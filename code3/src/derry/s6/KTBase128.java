package derry.s6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KTBase128 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("李四");
        names.add("王五");

        List<Integer> ages = new ArrayList<>();
        ages.add(22);
        ages.add(23);
        ages.add(24);

        Map<String, Integer> newMap = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            newMap.put(names.get(i), ages.get(i));
        }

        List<String> showList = new ArrayList<>();
        for (Map.Entry<String, Integer> Sxxx1 : newMap.entrySet()) {
            String r1 = "姓名：" + Sxxx1.getKey() + "，年龄：" + Sxxx1.getValue();
//            System.out.println(r1);
            showList.add(r1);
        }


        for (int i =0; i< showList.size(); i++) {
            System.out.println(showList.get(i));
        }
    }
}
