package com.m4r0x;
import com.m4r0x.exceptions.InvalidInputException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvalidInputException, IOException, InvocationTargetException, NoSuchMethodException {

        //auto1.autoBuchen((short) 100,"AB-sa 1243"); // 3 (Buchstaben für Name von ort) stellig 2 (Random Zeichen) stellig 4 stellig (Zahl von Ort)
        TerminalInteraction terminal = new TerminalInteraction();
        terminal.start();



    }
}
