package com.company;

import java.util.*;
import java.lang.reflect.*;

public class constructor {

    public static HashMap<String, Method> map = new HashMap<>();//Создадим объект класса HashMap:

    public static Commands com = new Commands();//Объект класса с командами

    static {

        for (Method met : com.getClass().getDeclaredMethods()) {//Берем список всех методов в классе Commands

            if (met.isAnnotationPresent(Annotation.class)) {//Смотрим, есть ли у метода нужная нам Аннотация
                Annotation ant = met.getAnnotation(Annotation.class);//Берем объект нашей Аннотации
                map.put(ant.name(), met);//Кладем в качестве ключа нашей карты параметр name() и метод
            }
        }
    }


    public static void mm()  {
            try {
                    System.out.print("Введите команду:");//Ввожу строку
                    Scanner console = new Scanner(System.in);
                    String a = console.nextLine();
                    String[] strSplit = a.split(" ");
                    String strCommand = strSplit[0].toLowerCase();//Разделяю первое слово от остальных
                    String[] strArguments = Arrays.copyOfRange(strSplit, 1, strSplit.length);

                    Method met = map.get(strCommand);//(strCommand-первое слово в строке или команда)

                    met.invoke(com, (Object) strArguments);


            }
            catch ( IllegalAccessException | InvocationTargetException | NullPointerException e) {
                    System.out.println("Неправильно введена команда, попробуйте заново.\n" +
                            " Подсказка: Используйте команду help, которая выводит справку по доступным командам ");
            }
    }
}

