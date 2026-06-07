package test;

import collection.CustomArrayList;
import io.StreamFill;
import filler.*;
import model.Car;

import java.io.File;
import java.util.Scanner;

public class FillerTest {
    public static void main(String[] args) {
        System.out.println("TEST: random filler");

        RandomFiller rF = new RandomFiller();
        CustomArrayList<Car> randomList = new CustomArrayList<>();
        randomList = rF.fill(5);
        System.out.println("Generated 5 objects: ");
        for (int i = 0; i < randomList.size(); i++) {
            System.out.println(i + ": " + randomList.get(i));
        }

        System.out.println("TEST: manual filler");

        Scanner sc = new Scanner(System.in);
        ManualFiller mF = new ManualFiller(sc);
        CustomArrayList<Car> manualList = new CustomArrayList<>();
        manualList = mF.fill(5);
        System.out.println("Created 5 objects with parameters from keyboard: ");
        for (int i = 0; i < manualList.size(); i++) {
            System.out.println(i + ": " + manualList.get(i));
        }

        System.out.println("TEST: file filler");

        File file = new File("data/cars.csv");
        FileFiller fF = new FileFiller(file);
        CustomArrayList<Car> fileList = new CustomArrayList<>();
        fileList = fF.fill(5);
        System.out.println("Created 5 objects with parameters from file: ");
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println(i + ": " + fileList.get(i));
        }
    }
}
