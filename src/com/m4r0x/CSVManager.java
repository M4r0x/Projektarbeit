package com.m4r0x;

import com.m4r0x.enums.FahrzeugTyp;
import com.m4r0x.exceptions.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CSVManager {

    public void createCSVFile(Fahrzeug fahrzeug) throws IOException {
        FileWriter fileWriter = new FileWriter(".\\src\\com\\m4r0x\\objects\\FahrzeugListe.csv", true);
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
        File file = new File(".\\src\\com\\m4r0x\\objects\\FahrzeugListe.csv");
        Scanner tokenCounter = new Scanner(file);
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
        return fahrzeugArrayList;
    }
}
