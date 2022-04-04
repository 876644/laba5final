package com.company;

import com.company.classes.StudyGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static PriorityQueue<StudyGroup> collection = new PriorityQueue<>();//Создала коллекцию

    public static List<Long> AlliD = new ArrayList<>();
    public static List<StudyGroup> twoColl = new ArrayList<>();
    public static List<String> saveHistory = new ArrayList<>();

    public static File file = new File(System.getenv("LABA"));

    public static void main(String[] args) {
        AlliD.clear();
        if (file.length() == 0) {
            System.out.println("Файл пуст.");
        } else FileCreate.readFile();


        //noinspection InfiniteLoopStatement
        while (true) {
            constructor.mm();
        }
    }
}
