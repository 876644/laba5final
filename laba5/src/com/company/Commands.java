package com.company;

import com.company.Enums.*;
import com.company.classes.Coordinates;
import com.company.classes.Person;
import com.company.classes.StudyGroup;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Commands  {

   Class<Commands> clas = Commands.class;


    @Annotation(name = "clear")//работает
    public void clear(String[] args) {
        if (args.length != 0){

            System.out.println("У команды clear нет аргумента. " );

        }else {
            Main.collection.clear();
            System.out.println("Была произведена очистка коллекции. ");

            String nameHistory = "clear";
            Main.saveHistory.add(nameHistory);
        }
    }

     @Annotation(name = "help")//работает
    public void help(String[] args){

         if (args.length != 0){

             System.out.println("У команды help нет аргумента." );

         }else {

             System.out.println("help : вывести справку по доступным командам\n" +
                     "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                     "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                     "add {element} : добавить новый элемент в коллекцию\n" +
                     "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                     "remove_by_id id : удалить элемент из коллекции по его id\n" +
                     "clear : очистить коллекцию\n" +
                     "save : сохранить коллекцию в файл\n" +
                     "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                     "exit : завершить программу (без сохранения в файл)\n" +
                     "head : вывести первый элемент коллекции\n" +
                     "remove_head : вывести первый элемент коллекции и удалить его\n" +
                     "history : вывести последние 15 команд (без их аргументов)\n" +
                     "remove_any_by_students_count studentsCount : удалить из коллекции один элемент, значение поля studentsCount которого эквивалентно заданному\n" +
                     "average_of_students_count : вывести среднее значение поля studentsCount для всех элементов коллекции\n" +
                     "filter_by_form_of_education formOfEducation : вывести элементы, значение поля formOfEducation которых равно заданному");
             String nameHistory = "help";
             Main.saveHistory.add(nameHistory);
         }
    }

    @Annotation(name = "info")//работает
    public void info(String[] args){

        if (args.length != 0){

            System.out.println("У команды info нет аргумента.");

        }else{

        System.out.println(" Информация о коллекции:\n" +
                "Тип коллекции - PriorityQueue\n" +
                "Дата инициализации - " + LocalDate.now() + "\n" +
                "Количество эллементов - " + Main.collection.size());
            String nameHistory = "info";
            Main.saveHistory.add(nameHistory);
        }
    }

   @Annotation(name = "show")//работает
    public void show(String[] args){

        if (args.length != 0){

            System.out.println("У команды show нет аргумента.");

        }else {

           int sizeOfCollection = Main.collection.size();
           if(sizeOfCollection==0){
               System.out.println("В коллекции нет объектов.");
           }

           for(int b=1; b<=sizeOfCollection;b++){

               System.out.println("Объект коллекции № " + b);
               StudyGroup example = Main.collection.poll();
               Main.twoColl.add(example);

                   if (example != null) {
                       System.out.println("Номер iD: " + example.getId());
                       System.out.println("Имя группы: " + example.getNameG());
                       System.out.println("Координата x: "+ example.getCoordinates().getX());
                       System.out.println("Координата y: "+ example.getCoordinates().getY());
                       System.out.println("Дата: " + example.getCreationDate());
                       System.out.println("Колличество студентов: " + example.getStudentsCount());
                       System.out.println("Форма обучения: " + example.getFormOfEducation());
                       System.out.println("Сместр: " + example.getSemesterEnum());
                       System.out.println("Имя студента: " + example.getGroupAdmin().getName());
                       System.out.println("iD паспорта: " + example.getGroupAdmin().getPassportID());
                       System.out.println("Цвет глаз: " + example.getGroupAdmin().getEyeColor());
                       System.out.println("Цвет волос: " + example.getGroupAdmin().getHairColor());
                       System.out.println("Национальность: " + example.getGroupAdmin().getNationality());
                   }

           }
           Main.collection.addAll(Main.twoColl);
           Main.twoColl.clear();
            String nameHistory = "show";
            Main.saveHistory.add(nameHistory);
       }
    }

    @Annotation(name = "add")//работает
    public void add(String[] argss)  {

        Scanner scanner = new Scanner(System.in);
        String[] elemets = new String[12];

        if (argss.length != 0){

            System.out.println("У команды add нет аргумента.");

        }else {

            Random random = new Random();
            elemets[0]= String.valueOf(random.nextInt(1000)+1);
            while (Main.AlliD.contains(Long.parseLong(elemets[0]))){
                elemets[0]= String.valueOf(random.nextInt(1000)+1);
            }
            Main.AlliD.add(Long.parseLong(elemets[0]));

            System.out.println("Введите имя группы: ");
            String nameG = scanner.nextLine();
                while (nameG.equals("") || !Pattern.compile("[A-Z]*[a-z]*[0-9]*").matcher(nameG).matches() ) {
                    System.out.println("Неправильно введены данные имени группы. Повторите попытку. Пример имени группы :R3136");
                    System.out.println("Введите имя группы: ");
                    nameG = scanner.nextLine();
                }
            elemets[1] = nameG;

            System.out.println("Введите координату x: ");
            String x = scanner.nextLine();
                while (!Pattern.compile("[0-9].*[0-9]*").matcher(x).matches() || x.equals("")){
                    System.out.println("Неправильно введены данные координаты x. Повторите попытку. Пример координаты x: 2.56 ");
                    System.out.println("Введите коориднату x: ");
                    x = scanner.nextLine();
                }
                while (Double.parseDouble(x) > 887 ) {
                    System.out.println("Неправильно введены данные координаты x. Повторите попытку. Пример координаты x: 2.56 ");
                    System.out.println("Введите коориднату x: ");
                    x = scanner.nextLine();
                }
            elemets[2] = x;

            System.out.println("Введите координату y: ");
            String y = scanner.nextLine();
                while (!Pattern.compile("\\d+").matcher(y).matches()) {
                    System.out.println("Неправильно введены данные координаты y. Повторите попытку. Пример координаты y: 2");
                    System.out.println("Введите коориднату y: ");
                    y = scanner.nextLine();
                }
            elemets[3] = x;

            Date date = new Date();

            System.out.println("Введите колличество студентов: ");
            String studentsCount = scanner.nextLine();
            while ((!Pattern.compile("\\d+").matcher(studentsCount).matches()) ) {
                System.out.println("Неправильно введены данные колличество студентов. Повторите попытку. Пример колличества студентов: 56 ");
                System.out.println("Введите колличество студентов: ");
                studentsCount = scanner.nextLine();
            }
            while (Long.parseLong(studentsCount) <= 0){
                System.out.println("Неправильно введены данные колличество студентов. Повторите попытку. Пример колличества студентов: 56 ");
                System.out.println("Введите колличество студентов: ");
                studentsCount = scanner.nextLine();
            }
            elemets[4] = studentsCount;

            System.out.println("Введите форму образования: ");
            String formEducation = scanner.nextLine();
            while (!formEducation.equals("FULL_TIME_EDUCATION")&&!formEducation.equals("DISTANCE_EDUCATION")&&!formEducation.equals("EVENING_CLASSES")) {
                System.out.println("Неправильно введена форма образования. Повторите попытку. Пример формы образования: DISTANCE_EDUCATION ");
                System.out.println("Введите форму образования: ");
                formEducation= scanner.nextLine();
            }
            elemets[5] = formEducation;

            System.out.println("Введите семестр: ");
            String semesterEnum = scanner.nextLine();
            while (!semesterEnum.equals("FIFTH")&&!semesterEnum.equals("SIXTH")&&!semesterEnum.equals("SEVENTH")) {
                System.out.println("Неправильно введён семестр. Повторите попытку. Пример семестра: SEVENTH");
                System.out.println("Введите семестр: ");
                semesterEnum= scanner.nextLine();
            }
            elemets[6] = semesterEnum;

            System.out.println("Введите имя студента: ");
            String name = scanner.nextLine();
            while ( name.equals("") || !Pattern.compile("[A-Z]*[a-z]*").matcher(name).matches() ) {
                System.out.println("Неправильно введено имя студента. Повторите попытку. Пример имени: Lora");
                System.out.println("Введите имя студента: ");
                name = scanner.nextLine();
            }
            elemets[7] = name;

            System.out.println("Введите ID паспорта: ");
            String passportID = scanner.nextLine();
            while (passportID.equals("") || (!Pattern.compile("[A-Z]*[a-z]*[0-9]*").matcher(passportID).matches())){
                System.out.println("Неправильно введены данные ID паспорта. Повторите попытку. Пример iD паспорта: i1578kL");
                System.out.println("Введите ID паспорта: ");
                passportID = scanner.nextLine();
            }
            while (((passportID).length()) > 31){
                System.out.println("Неправильно введены данные ID паспорта. Повторите попытку. Пример iD паспорта: i1578kL");
                System.out.println("Введите ID паспорта: ");
                passportID = scanner.nextLine();
            }
            elemets[8] = passportID;

            System.out.println("Введите цвет глаз: ");
            String eyeColor = scanner.nextLine();
            while (!eyeColor.equals("GREEN")&&!eyeColor.equals("YELLOW")&&!eyeColor.equals("ORANGE")&&!eyeColor.equals("WHITE")){
                System.out.println("Неправильно введены данные цвета глаз. Повторите попытку. Пример цвета глаз: GREEN ");
                System.out.println("Введите цвет глаз: ");
                eyeColor = scanner.nextLine();
            }
            elemets[9] = eyeColor;

            System.out.println("Введите цвет волос: ");
            String hairColor = scanner.nextLine();
            while (!hairColor.equals("GREEN")&&!hairColor .equals("RED")&&!hairColor .equals("YELLOW")&&!hairColor .equals("WHITE")){
                System.out.println("Неправильно введены данные цвета волос. Повторите попытку. Пример цвета волос: GREEN ");
                System.out.println("Введите цвет волос: ");
                hairColor  = scanner.nextLine();
            }
            elemets[10] = hairColor ;

            System.out.println("Введите национальность: ");
            String nationality = scanner.nextLine();
            while (!nationality.equals("RUSSIA")&&!nationality.equals("GERMANY")&&!nationality.equals("CHINA")&&!nationality.equals("NORTH_KOREA")){
                System.out.println("Неправильно введены данные национальности. Повторите попытку. Пример национальности: RUSSIA");
                System.out.println("Введите национальность: ");
                nationality = scanner.nextLine();
            }
            elemets[11] =  nationality;

            Coordinates coordinates = new Coordinates(Double.parseDouble(elemets[2]), Integer.valueOf(elemets[3]));
            Person person = new Person((elemets[7]), (elemets[8]), ColorY.valueOf(elemets[9]), ColorH.valueOf(elemets[10]), Country.valueOf(elemets[11]));
            StudyGroup studyGroup = new StudyGroup(Long.valueOf(elemets[0]), (elemets[1]), coordinates, date, Long.valueOf(elemets[4]), FormOfEducation.valueOf(elemets[5]), Semester.valueOf(elemets[6]), person);

            Main.collection.add(studyGroup);
            System.out.println("Объект был успешно добавлен в коллекцию.");

            String nameHistory = "add";
            Main.saveHistory.add(nameHistory);
        }
    }

    @Annotation(name = "update")//работает
    public void update(String[] args){
        if (args.length==1 && Pattern.compile("\\d+").matcher(args[0]).matches()){

            long number = Long.parseLong(args[0]);
            int sizeOfCollection = Main.collection.size();

            if(sizeOfCollection==0){
                System.out.println("В коллекции нет объектов.");
            }

            for(int b=1; b<=sizeOfCollection;b++){
                StudyGroup example = Main.collection.poll();
                if(example != null ){
                    if(number == example.getId()) {
                        System.out.println("Данные элемента с таким iD был найден и удалён. Введите новые двнные элемента");
                        String[] argss = new String[0];
                        add(argss);
                    }else Main.twoColl.add(example);
                }
            }
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();

            String nameHistory = "update";
            Main.saveHistory.add(nameHistory);
        }else {
            System.out.println("У команды update должен быть один аргумент.");

        }
    }

    @Annotation(name = "remove_by_id")//работает
    public void remove(String[] args){
        if (args.length==1 && Pattern.compile("\\d+").matcher(args[0]).matches()){

            long number = Long.parseLong(args[0]);
            int sizeOfCollection = Main.collection.size();

            if(sizeOfCollection==0){
                System.out.println("В коллекции нет объектов.");
            }

            for(int b=1; b<=sizeOfCollection;b++){
                StudyGroup example = Main.collection.poll();
                if(example != null ){
                    if(number == example.getId()) System.out.println("Данные элемента с таким iD был найден и удалён. ");
                    else Main.twoColl.add(example);
                }
            }
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();

            String nameHistory = "remove_by_id";
            Main.saveHistory.add(nameHistory);
        }else {
            System.out.println("У команды remove_by_id должен быть один аргумент.");

        }
    }

    @Annotation(name = "save")//работает
    public void save (String[] args) throws IOException {
        if (args.length == 0){

            FileWriter fileWriter = new FileWriter(String.valueOf(Main.file));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int sizeOfCollection = Main.collection.size();
            if(sizeOfCollection==0){
                System.out.println("В коллекции нет объектов.");
            }

            for(int b=1; b<=sizeOfCollection;b++){

                StudyGroup example = Main.collection.poll();
                Main.twoColl.add(example);

                if (example != null) {
                    bufferedWriter.write(String.valueOf(example.getId()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getNameG()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getCoordinates().getX()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getCoordinates().getY()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getStudentsCount()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getFormOfEducation()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getSemesterEnum()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getGroupAdmin().getName()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getGroupAdmin().getPassportID()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getGroupAdmin().getEyeColor()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getGroupAdmin().getHairColor()));
                    bufferedWriter.write(";");
                    bufferedWriter.write(String.valueOf(example.getGroupAdmin().getNationality()));
                    bufferedWriter.newLine();
                }
            }
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();
            bufferedWriter.close();
            System.out.println("Объекты сохранены в файл.");

            String nameHistory = "save";
            Main.saveHistory.add(nameHistory);

        }else {
            System.out.println("У команды save нет аргумента.");

        }
    }

    @Annotation(name = "execute_script")
    public void execute(String[] args){
        if(args.length == 1 && Pattern.compile("^[A-Za-z0-9+_.-]+\\.[a-zA-Z0-9]+$").matcher(args[0]).matches() ) {
            try {
                String nameOfFile = args[0];
                File file = new File(nameOfFile);

                if (file.exists()) {

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = bufferedReader.readLine()) !=null) {
                        System.out.println("Команда: " + line);
                        String[] strSplit = line.split(" ");
                        String strCommand = strSplit[0].toLowerCase();//Разделяю первое слово от остальных
                        String[] strArguments = Arrays.copyOfRange(strSplit, 1, strSplit.length);

                        Method met = constructor.map.get(strCommand);//(strCommand-первое слово в строке или команда)

                        String str = new String("execute_script");
                        if (strCommand.equals(str.toLowerCase()) && strArguments.length == 1 && strArguments[0].equals(nameOfFile)){

                           System.out.println("В файле есть команда execute_script с таким же именем файла, который был введен ранее.\n" +
                                    "Эта команда не была реализована, потому что это приведёт к бесконечному повтору команд из файла. ' ");

                        }else met.invoke(constructor.com, (Object) strArguments);
                    }
                    bufferedReader.close();

                }else System.out.println("Такого файла не существует.");

            } catch(IllegalAccessException | InvocationTargetException | IOException e){
                System.out.println("В файле неправильные данные команд.");
            }

            String nameHistory = "execute_script";
            Main.saveHistory.add(nameHistory);

        }else  System.out.println("У команды execute_script должен быть один аргумент, который являеться названием файла.");
    }

    @Annotation(name = "exit")//работает
    public void exit(String[] args){
        if (args.length != 0){

            System.out.println("У этой команды нет аргумента, попробуйте ещё раз.\n "  +
                    "Подсказка: Используйте команду help, которая выводит справку по доступным командам.");

        }else {
            System.out.println("Работа программы завершается без сохранения данных.");
            System.exit(0);

            String nameHistory = "exit";
            Main.saveHistory.add(nameHistory);
        }
    }

    @Annotation(name = "head")//работает
    public void head(String[] args){

        if (args.length != 0){

            System.out.println("У этой команды нет аргумента, попробуйте ещё раз.\n "  +
                    "Подсказка: Используйте команду help, которая выводит справку по доступным командам.");

        }else {
            if (Main.collection.size() !=0){
                StudyGroup example = Main.collection.peek();
                System.out.println("Первый элемент коллекции:");

                if (example != null) {
                    System.out.println("Номер iD: " + example.getId());
                    System.out.println("Имя группы: " + example.getNameG());
                    System.out.println("Координата x: "+ example.getCoordinates().getX());
                    System.out.println("Координата y: "+ example.getCoordinates().getY());
                    System.out.println("Дата: " + example.getCreationDate());
                    System.out.println("Колличество студентов: " + example.getStudentsCount());
                    System.out.println("Форма обучения: " + example.getFormOfEducation());
                    System.out.println("Сместр: " + example.getSemesterEnum());
                    System.out.println("Имя студента: " + example.getGroupAdmin().getName());
                    System.out.println("iD паспорта: " + example.getGroupAdmin().getPassportID());
                    System.out.println("Цвет глаз: " + example.getGroupAdmin().getEyeColor());
                    System.out.println("Цвет волос: " + example.getGroupAdmin().getHairColor());
                    System.out.println("Национальность: " + example.getGroupAdmin().getNationality());
                }
            }
            String nameHistory = "head";
            Main.saveHistory.add(nameHistory);
        }
    }

    @Annotation(name = "remove_head")//работает
    public void remove_head(String[] args) {
        if (args.length != 0){

            System.out.println("У этой команды нет аргумента, попробуйте ещё раз.\n "  +
                    "Подсказка: Используйте команду help, которая выводит справку по доступным командам.");

        }else {
            if (Main.collection.size()!=0) {
                System.out.println("Первый элемент коллекции:");
                StudyGroup example = Main.collection.poll();

                if (example != null) {
                    System.out.println("Номер iD: " + example.getId());
                    System.out.println("Имя группы: " + example.getNameG());
                    System.out.println("Координата x: "+ example.getCoordinates().getX());
                    System.out.println("Координата y: "+ example.getCoordinates().getY());
                    System.out.println("Дата: " + example.getCreationDate());
                    System.out.println("Колличество студентов: " + example.getStudentsCount());
                    System.out.println("Форма обучения: " + example.getFormOfEducation());
                    System.out.println("Сместр: " + example.getSemesterEnum());
                    System.out.println("Имя студента: " + example.getGroupAdmin().getName());
                    System.out.println("iD паспорта: " + example.getGroupAdmin().getPassportID());
                    System.out.println("Цвет глаз: " + example.getGroupAdmin().getEyeColor());
                    System.out.println("Цвет волос: " + example.getGroupAdmin().getHairColor());
                    System.out.println("Национальность: " + example.getGroupAdmin().getNationality());
                }
                System.out.println("Этот объект был удалён.");

                String nameHistory = "remove_head";
                Main.saveHistory.add(nameHistory);
            } else System.out.println("В коллекции нет эллементов.");
        }
    }

    @Annotation(name = "remove_any_by_students_count")//работает
    public void remove_any_by_students_count(String[] args){
        if((args.length == 1)&&(Pattern.compile("\\d+").matcher(args[0]).matches())){
            long study = Integer.parseInt(args[0]);
            int count = 0;

                int sizeOfCollection = Main.collection.size();

                if(sizeOfCollection==0){
                    System.out.println("В коллекции нет объектов.");
                }
                for(int b=1; b<=sizeOfCollection;b++){
                    StudyGroup example = Main.collection.poll();

                    if(example != null ){
                        if(study == example.getStudentsCount() && count == 0) {
                            System.out.println("Один элемент с данным колличеством студентов был удален.");
                            count+=1;
                        }else Main.twoColl.add(example);
                    }
                }
                Main.collection.addAll(Main.twoColl);
                Main.twoColl.clear();

            String nameHistory = "remove_any_by_students_count";
            Main.saveHistory.add(nameHistory);
        }else System.out.println("У этой команды должен быть один аргумент. Повторите попытку.");
    }

    @Annotation(name = "average_of_students_count")//работает
    public void average_of_students_count (String[] args){
        if (args.length != 0){

            System.out.println("У этой команды нет аргумента, попробуйте ещё раз.\n "  +
                    "Подсказка: Используйте команду help, которая выводит справку по доступным командам.");

        }else {
            int sizeOfCollection = Main.collection.size();
            if(sizeOfCollection==0){
                System.out.println("В коллекции нет объектов.");
            }else {
                int summ = 0;
                int count = 0;
                for (int b = 1; b <= sizeOfCollection; b++) {

                    StudyGroup example = Main.collection.poll();
                    Main.twoColl.add(example);

                    if (example != null) {
                        summ += example.getStudentsCount();
                        count+=1;
                        System.out.println("mm");
                    }
                }

                System.out.println("Среднее значение колличества студентов = " + summ/count);
                Main.collection.addAll(Main.twoColl);
                Main.twoColl.clear();

                String nameHistory = "average_of_students_count";
                Main.saveHistory.add(nameHistory);
            }
        }
    }

    @Annotation(name = "filter_by_form_of_education")//работает
    public void filter_by_form_of_education(String[] args){
        if(args.length == 1){
            String str = args[0];
            if(str.equals("FULL_TIME_EDUCATION")||str.equals("DISTANCE_EDUCATION")||str.equals("EVENING_CLASSES")){
                int sizeOfCollection = Main.collection.size();

                if(sizeOfCollection==0){
                    System.out.println("В коллекции нет объектов.");
                }
                for(int b=1; b<=sizeOfCollection;b++){
                    StudyGroup example = Main.collection.poll();
                    Main.twoColl.add(example);

                    if(example != null ){
                        if(str.equals(String.valueOf(example.getFormOfEducation()))) {

                            System.out.println("Номер iD: " + example.getId());
                            System.out.println("Имя группы: " + example.getNameG());
                            System.out.println("Координата x: "+ example.getCoordinates().getX());
                            System.out.println("Координата y: "+ example.getCoordinates().getY());
                            System.out.println("Дата: " + example.getCreationDate());
                            System.out.println("Колличество студентов: " + example.getStudentsCount());
                            System.out.println("Форма обучения: " + example.getFormOfEducation());
                            System.out.println("Сместр: " + example.getSemesterEnum());
                            System.out.println("Имя студента: " + example.getGroupAdmin().getName());
                            System.out.println("iD паспорта: " + example.getGroupAdmin().getPassportID());
                            System.out.println("Цвет глаз: " + example.getGroupAdmin().getEyeColor());
                            System.out.println("Цвет волос: " + example.getGroupAdmin().getHairColor());
                            System.out.println("Национальность: " + example.getGroupAdmin().getNationality());
                        }
                    }
                }
                Main.collection.addAll(Main.twoColl);
                Main.twoColl.clear();

                String nameHistory = "filter_by_form_of_education";
                Main.saveHistory.add(nameHistory);
            }
        }else System.out.println("У этой команды должен быть один аргумент. Повторите попытку.");
    }

    @Annotation(name = "history")//работает
    public void history(String[] args){
        if (args.length != 0){

            System.out.println("У этой команды нет аргумента, попробуйте ещё раз.\n "  +
                    "Подсказка: Используйте команду help, которая выводит справку по доступным командам.");

        }else {
            if(Main.saveHistory.size()!=0) {
                if (Main.saveHistory.size()<15){
                    System.out.println("Вы ввели меньше 15 команд до этого. Часть команд, которая была введена: ");
                    for (String element : Main.saveHistory) {
                        System.out.println(element);
                    }
                }else {
                    int size = Main.saveHistory.size();
                    System.out.println(Main.saveHistory.subList(size-14,size));
                }
            }else System.out.println("Ранее не было введено ни одной команды. ");
            String nameHistory = "history";
            Main.saveHistory.add(nameHistory);
        }
    }
}
