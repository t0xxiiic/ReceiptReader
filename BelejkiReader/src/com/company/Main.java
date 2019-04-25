package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    //All variables go here:
    public static String file;
    public static ArrayList<String> time = new ArrayList<>();
    public static ArrayList<String> amount = new ArrayList<>();
    public static String startDate = "09-06", endDate = "10-06";


    public static void main(String[] args) {
        file = openFile();
        convertFile(file);

        System.out.println(amount.size());
        System.out.println(time.size());

        System.out.println("  Bills For " + startDate + " are: \n" + "````````````````````````");

        for(int i = 0; i < time.size(); i++){
            System.out.println("Amount: " + amount.get(i) + " @ " + time.get(i));
        }
    }

    //All methods are located under here:
    public static String openFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");

        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);

        String file = dialog.getFile();
        String directory = dialog.getDirectory();

        System.out.println(directory + file + " chosen.");

        return directory + file;
    } //Opens a File Dialog to choose a file and then returns its path.

    public static void convertFile(String file) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String bufferedReaderString, container;

            while ((bufferedReaderString = bufferedReader.readLine()) != null) {
                if(bufferedReaderString.contains(endDate)){
                    break;
                }
                if (bufferedReaderString.contains("Обща сума")) {
                    container = bufferedReaderString.substring(25);
                    //System.out.println(container);
                    amount.add(container);
                } else if (bufferedReaderString.contains(startDate)) {
                    container = bufferedReaderString.substring(27);
                    //System.out.println(container);
                    time.add(container);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


