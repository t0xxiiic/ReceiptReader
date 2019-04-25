package com.company;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    //All variables go here:
    public static String file;
    public static ArrayList<String> time = new ArrayList<>();
    public static ArrayList<String> amount = new ArrayList<>();
    public static String startDate = "01-09", endDate = "02-09";
    public static ArrayList<Double> cleanAmounts = new ArrayList<>();


    public static void main(String[] args) {
        file = openFile();
        getReceipts(file);

        System.out.println(amount.size());
        System.out.println(time.size());
        convertStringToDouble(amount);

        System.out.println("  Bills For " + startDate + " are: \n" + "````````````````````````");

        for(int i = 0; i < time.size(); i++){
            System.out.println("Amount: " + amount.get(i) + " @ " + time.get(i));
        }

        System.out.println("Clean amounts are: ");
        for(double i : cleanAmounts){
            System.out.print(i + ", ");
        }
        System.out.println();

        System.out.println("  Bills For " + startDate + " are: \n" + "````````````````````````");
        for(int i = 0; i < time.size(); i++){
            System.out.println("Amount("+ (i+1) + "): " + cleanAmounts.get(i) + "лв. " + "@ " + time.get(i) + "ч.");
        }

        writeReceiptsToFile();
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

    public static void getReceipts(String file) {

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

    public static void writeReceiptsToFile(){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Receipts.txt", true));
            bufferedWriter.newLine();
            bufferedWriter.write("Receipts for " + startDate + " are: ");
            bufferedWriter.newLine();
            bufferedWriter.write("````````````````````````");
            bufferedWriter.newLine();
            for(int i = 0; i < time.size(); i++){
                bufferedWriter.write("Amount("+ (i+1) + "): " + cleanAmounts.get(i) + "лв. " + "@ " + time.get(i) + "ч.");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void convertStringToDouble(ArrayList<String> amount){
        char[] container;
        int counter = 0;

        for(int i = 0; i < amount.size(); i++){
            container = amount.get(i).toCharArray();
            for(int j = 0; j < 4; j++){
                if(container[j] == '.'){
                    counter++;
                }
            }
            cleanAmounts.add(Double.parseDouble(amount.get(i).substring(counter)));
            counter = 0;
        }
    }
}


