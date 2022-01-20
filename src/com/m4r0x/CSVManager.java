package com.m4r0x;

import com.m4r0x.enums.FahrzeugTyp;
import com.m4r0x.exceptions.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVManager {
String filePath = ".\\src\\com\\m4r0x\\objectsStorage\\FahrzeugListe.csv";
String temp;

    public void createCSVFile(Fahrzeug fahrzeug) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
                result.append(fahrzeug.getHerstellername());
                result.append(",");
                result.append(fahrzeug.getKilometerstand());
                result.append(",");
                result.append(fahrzeug.getVerbrauchtPro100km());
                result.append(",");
                result.append(fahrzeug.getMaximalerTankinhalt());
                result.append(",");
                result.append(fahrzeug.getBasisMietpreis());
                result.append(",");
                result.append(fahrzeug.getMietpreisProTag());
                result.append(",");
                result.append(fahrzeug.isLeihstatus());
                result.append(",");
                result.append(fahrzeug.getLeihdauerInTagen());
                result.append(",");
                result.append(fahrzeug.getFahrzeugTyp());
                result.append(",");
                result.append(fahrzeug.getTankinhalt());
                result.append(newLine);
                printWriter.print(result);
                printWriter.close();
    }
    public ArrayList<Fahrzeug> createArrayListFromCSV() throws FileNotFoundException, InvalidInputException {
        File file = new File(filePath);
        ArrayList<Fahrzeug> fahrzeugArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(file).useDelimiter(",");
        String herstellername;
        int kilometerstand;
        float verbrauchtPro100km;
        double maximalerTankinhalt;
        double basisMietpreis;
        double mietpreisProTag;
        boolean leihstatus;
        short leihdauerInTagen;
        FahrzeugTyp fahrzeugTyp;
        scanner.nextLine(); // skip first line since its just the categories
        float tankinhalt;
        while(scanner.hasNextLine()) {
            String[] currentLineAsTokenArray = scanner.nextLine().split(",");
            herstellername = currentLineAsTokenArray[0];
            kilometerstand = Integer.parseInt(currentLineAsTokenArray[1]);
            verbrauchtPro100km = Float.parseFloat(currentLineAsTokenArray[2]);
            maximalerTankinhalt = Double.parseDouble(currentLineAsTokenArray[3]);
            basisMietpreis = Double.parseDouble(currentLineAsTokenArray[4]);
            mietpreisProTag = Double.parseDouble(currentLineAsTokenArray[5]);
            leihstatus = Boolean.parseBoolean(currentLineAsTokenArray[6]);
            leihdauerInTagen = Short.parseShort(currentLineAsTokenArray[7]);
            fahrzeugTyp = FahrzeugTyp.valueOf(currentLineAsTokenArray[8]);
            tankinhalt = Float.parseFloat(currentLineAsTokenArray[9]);
            fahrzeugArrayList.add(new Fahrzeug(herstellername,kilometerstand,verbrauchtPro100km,maximalerTankinhalt,basisMietpreis,mietpreisProTag,leihstatus,leihdauerInTagen, fahrzeugTyp,tankinhalt));
        }
        scanner.close();
        return fahrzeugArrayList;
    }
    //creates an identical version of the current csv without the line of the specified car object's contents
    public void deleteCar(Fahrzeug fahrzeug) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath,false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        File file = new File(filePath);
        Scanner scanner = new Scanner(file).useDelimiter(",");
        StringBuilder newFileContent = new StringBuilder();
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            System.out.println("Test");
            temp = scanner.nextLine();
            if (!temp.contains(fahrzeug.getHerstellername())) {
                newFileContent.append(temp);
            }
        }
        printWriter.print(newFileContent.toString());
        printWriter.close();
        fileWriter.close();
        scanner.close();
        System.out.println(fahrzeug.getHerstellername()+" wurde erfolgreich gelöscht!");
    }
}
