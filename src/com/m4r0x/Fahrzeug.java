package com.m4r0x;
import com.m4r0x.enums.FahrzeugTyp;
import com.m4r0x.exceptions.*;

import java.lang.reflect.Field;

public class Fahrzeug {

    private String herstellername;
    private int kilometerstand;
    private float verbrauchtPro100km;
    private double maximalerTankinhalt;
    private double basisMietpreis;
    private double mietpreisProTag;
    private boolean leihstatus;
    private short leihdauerInTagen;
    private FahrzeugTyp fahrzeugTyp;
    private float tankinhalt;
    private float aufschlag;
    private String kennzeichen;

    public Fahrzeug(String herstellername, int kilometerstand, float verbrauchtPro100km, double maximalerTankinhalt, double basisMietpreis, double mietpreisProTag, boolean leihstatus, short leihdauerInTagen, FahrzeugTyp fahrzeugTyp, float tankinhalt) throws InvalidInputException {
        if (kilometerstand < 0) throw new InvalidInputException("Kilometerstand darf nicht negativ sein");
        if (verbrauchtPro100km < 0) throw new InvalidInputException("VerbrauchtPro100km darf nicht negativ sein");
        if (basisMietpreis < 0) throw new InvalidInputException("BasisMietpreis darf nicht negativ sein");
        if (mietpreisProTag < 0) throw new InvalidInputException("MietpreisProTag darf nicht negativ sein");
        if (maximalerTankinhalt < 0) throw new InvalidInputException("MaximalerTankinhalt darf nicht negativ sein");
        if (leihdauerInTagen < 0) throw new InvalidInputException("LeihdauerInTagen darf nicht negativ sein");
        if (tankinhalt < 0) throw new InvalidInputException("Tankinhalt darf nicht negativ sein");
        this.herstellername = herstellername;
        this.kilometerstand = kilometerstand;
        this.verbrauchtPro100km = verbrauchtPro100km;
        this.maximalerTankinhalt = maximalerTankinhalt;
        this.basisMietpreis = basisMietpreis;
        this.mietpreisProTag = mietpreisProTag;
        this.leihstatus = leihstatus;
        this.leihdauerInTagen = leihdauerInTagen;
        this.fahrzeugTyp = fahrzeugTyp;
        this.tankinhalt = tankinhalt;
        aufschlag = fahrzeugTyp == FahrzeugTyp.LUXUS ? 1.3f : fahrzeugTyp == FahrzeugTyp.MITTELKLASSE ? 1.1f : 1f;
    }


    public void tanken(double BenzinInLiter) throws RentedException, TankCapException {
        if(!leihstatus) throw new RentedException("Du darfst das Auto nicht fahren ohne es gemietet zu haben");
        if (BenzinInLiter + tankinhalt > maximalerTankinhalt) throw new TankCapException("Du darfst das Fahrzeug nicht übertanken. Der maximale Tankinhalt ist: "+ maximalerTankinhalt);
        tankinhalt += BenzinInLiter;
    }

    public void fahren(double zuFahrendeKm) throws InvalidInputException, RentedException, TankCapException {
        if(!leihstatus) throw new RentedException("Du darfst das Auto nicht fahren ohne es gemietet zu haben");
        if(0 > zuFahrendeKm) throw new InvalidInputException("Die zu fahrenden Kilometer dürfen nicht kleiner als 0 sein");
        if(zuFahrendeKm/100*verbrauchtPro100km>tankinhalt) throw new TankCapException("Du hast nicht genug Benzin, um das Auto die angegebene Anzahl an Kilometer zu fahren");
        kilometerstand += zuFahrendeKm;
        tankinhalt -= zuFahrendeKm/100* verbrauchtPro100km;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // in case the code gets run under linux /n wouldn't work so its safer to get line.seperator from System
        String newLine = System.getProperty("line.separator");
        for (Field field : this.getClass().getDeclaredFields()) {
            result.append("  ");
                result.append( field.getName() );
                result.append(": ");
                //gets value of privat field from current object and appends it to current Line
            try {
                result.append( field.get(this) );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            result.append(newLine);
        }
        return result.toString();
    }

    public boolean tankstatus() {
        if (tankinhalt >= maximalerTankinhalt/2) return true;
        return false;
    }

    public double berechneMietpreis () throws RentedException {
        if (!leihstatus) throw new RentedException("Die Berechnung kann nur durchgeführt werden, wenn das Fahrzeug vermietet ist.");
        return aufschlag*(basisMietpreis+mietpreisProTag*leihdauerInTagen);
    }

    public void autoBuchen (short leihdauerInTagen, String kennzeichen) throws InvalidInputException {
        if (leihstatus) return;
        if(!kennzeichen.matches("^[a-zA-Z]{1,3}-[a-zA-Z]{1,2} \\d{1,4}$")) throw new InvalidInputException("Invalides Kennzeichen"); // checks if its a valid german license plate
        leihstatus = true;
        this.kennzeichen = kennzeichen;
        this.leihdauerInTagen = leihdauerInTagen;
    }

    public String getHerstellername() {
        return herstellername;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public float getVerbrauchtPro100km() {
        return verbrauchtPro100km;
    }

    public double getMaximalerTankinhalt() {
        return maximalerTankinhalt;
    }

    public double getBasisMietpreis() {
        return basisMietpreis;
    }

    public double getMietpreisProTag() {
        return mietpreisProTag;
    }

    public boolean isLeihstatus() {
        return leihstatus;
    }

    public short getLeihdauerInTagen() {
        return leihdauerInTagen;
    }

    public FahrzeugTyp getFahrzeugTyp() {
        return fahrzeugTyp;
    }

    public float getTankinhalt() {
        return tankinhalt;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public float getAufschlag() {
        return aufschlag;
    }
}
