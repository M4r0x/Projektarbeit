package com.m4r0x;

import com.m4r0x.enums.FahrzeugTyp;
import com.m4r0x.exceptions.InvalidInputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalInteraction {
    String herstellername;
    int kilometerstand;
    float verbrauchtPro100km;
    double maximalerTankinhalt;
    double basisMietpreis;
    double mietpreisProTag;
    boolean leihstatus;
    short leihdauerInTagen;
    FahrzeugTyp fahrzeugTyp;
    float tankinhalt;
    Fahrzeug ausgewähltesFahrzeug;

    public void start() throws InvalidInputException {
        int prompt = 0;
        int amountOfArguments;
        Scanner userInput = new Scanner(System.in);
        CSVManager csvManager = new CSVManager();
        System.out.println("Hallo willkommen zu der Fahrzeugverwaltung!");
        System.out.println("Was wollen sie tun? Geben sie die Zahl des jeweiligen Befehls ein um fortzufahren.");
        Dialog dialog = new Dialog(new String[]{"Auto hinzufügen", "Autos auflisten", "Auto auswählen"});
        int check = 0;
        while (true) {
            try {
                dialog.display();
                switch (userInput.nextInt()) {
                    case 0:
                        csvManager.createCSVFile(receiveCarFromUser(0));
                        check=0;
                        break;
                    case 1:
                        printOutArrayList(csvManager.createArrayListFromCSV());
                        check=1;
                        break;
                    case 2:
                        ausgewähltesFahrzeug = selectCar(csvManager.createArrayListFromCSV());
                        check=0;
                        break;
                    default:
                        check=0;
                        throw new InputMismatchException();
                }
                if (check == 0) {
                    break;
                }
            } catch(InputMismatchException | IOException e) {
                System.out.println("Die Eingabe ist nicht valide.");
                userInput.nextLine();
            }
        }
//        System.out.println("_________________________________________________________________________________________________________");
//        System.out.println("| [1]Auto löschen [2]Auto Attribute auflisten [3]reguläre Methoden auswählen [4] |");
//        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
//
//        System.out.println("________________________________________________________________________________________");
//        System.out.println("| [1]datenAusgeben [2]tankstatus [3]berechneMietpreis [4]autoBuchen [5]fahren [6]tanken |");
//        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
//

    }

    public Fahrzeug receiveCarFromUser(int stage) throws InvalidInputException {
        Scanner userInput = new Scanner(System.in);

        switch (stage) {
            case 0:
                try {
                    System.out.println("Herstellername: (String)");
                    herstellername = userInput.next();
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(0);
                    break;
                }
            case 1:
                try {
                    System.out.println("Kilometerstand: (int)");
                    kilometerstand = userInput.nextInt();
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(1);
                    break;
                }
            case 2:
                try {
                    System.out.println("VerbrauchtPro100km: (float)");
                    verbrauchtPro100km = Float.parseFloat(userInput.next());
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(2);
                    break;
                }
            case 3:
                try {
                    System.out.println("MaximalerTankinhalt: (double)");
                    maximalerTankinhalt = Double.parseDouble(userInput.next());
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(3);
                    break;
                }
            case 4:
                try {
                    System.out.println("BasisMietpreis: (double)");
                    basisMietpreis = Double.parseDouble(userInput.next());
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(4);
                    break;
                }
            case 5:
                try {
                    System.out.println("MietpreisProTag: (double)");
                    mietpreisProTag = Double.parseDouble(userInput.next());
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(5);
                    break;
                }
            case 6:
                try {
                    System.out.println("Leihstatus: (boolean)");
                    leihstatus = Boolean.parseBoolean(userInput.next());
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(6);
                    break;
                }
            case 7:
                try {
                    System.out.println("LeihdauerInTagen: (short)");
                    leihdauerInTagen = Short.parseShort(userInput.next());
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(7);
                    break;
                }
            case 8:
                try {
                    System.out.println("FahrzeugTyp: (LUXUS,MITTELKLASSE,KLEINWAGEN)");
                    fahrzeugTyp = FahrzeugTyp.valueOf(userInput.next());
                } catch (Exception e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(8);
                    break;
                }
            case 9:
                try {
                    System.out.println("Tankinhalt: (float)");
                    tankinhalt = Float.parseFloat(userInput.next());
                    System.out.println("Das Fahrzeug wurde erfolgreich erstellt!");
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Die Eingabe ist nicht valide.");
                    receiveCarFromUser(8);
                    break;
                }
            default:
                System.out.println("Error invalide Stage");
                break;
        }
        return new Fahrzeug(herstellername, kilometerstand, verbrauchtPro100km, maximalerTankinhalt, basisMietpreis, mietpreisProTag, leihstatus, leihdauerInTagen, fahrzeugTyp, tankinhalt);
    }
    public Fahrzeug selectCar(ArrayList<Fahrzeug> fahrzeugArrayList) {
        Scanner userInput = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        result.append("Dies sind die momentan verfügbaren Fahrzeuge: ");
        String[] fahrzeugNamen = new String[fahrzeugArrayList.size()];
        int index=0;
        int selectedCar;
        for(Fahrzeug fahrzeug : fahrzeugArrayList) {
            fahrzeugNamen[index] = fahrzeug.getHerstellername();
            index++;
        }
        Dialog dialog = new Dialog(fahrzeugNamen);
        dialog.display();
        result.setLength(0); // clear string builder
        System.out.println("Bitte wählen sie ein Fahrzeug aus.");
        while (true) {
            try {
                selectedCar = userInput.nextInt();
                result.append("Sie haben ");
                result.append(fahrzeugArrayList.get(selectedCar).getHerstellername());
                result.append(" ausgewählt.");
                System.out.println(result);
                return fahrzeugArrayList.get(selectedCar);
            } catch(Exception e) {
                System.out.println("Die Eingabe ist nicht valide.");
                userInput.nextLine();
            }
        }
    }
    public void printOutArrayList(ArrayList<Fahrzeug> fahrzeugArrayList) {
        for(Fahrzeug fahrzeug : fahrzeugArrayList) {
            System.out.println(fahrzeug);
        }
    }

}
