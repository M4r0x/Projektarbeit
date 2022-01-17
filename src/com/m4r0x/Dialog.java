package com.m4r0x;

public class Dialog {
    private String[] arguments;
    char verticalBorderChar;
    char topHorizontalBorderChar;
    char bottomHorizontalBorderChar;
    char emptySpaceFillerChar;

    public Dialog(String[] arguments, char verticalBorderChar, char topHorizontalBorderChar, char bottomHorizontalBorderChar, char emptySpaceFillerChar) {
        this.arguments = arguments;
        this.verticalBorderChar = verticalBorderChar;
        this.topHorizontalBorderChar = topHorizontalBorderChar;
        this.bottomHorizontalBorderChar = bottomHorizontalBorderChar;
        this.emptySpaceFillerChar = emptySpaceFillerChar;
    }

    public Dialog(String[] arguments) {
        this.arguments = arguments;
        verticalBorderChar = '|';
        topHorizontalBorderChar = '_';
        bottomHorizontalBorderChar = '¯';
        emptySpaceFillerChar = ' ';
    }

    public int display() {
        StringBuilder contentRow = new StringBuilder();
        StringBuilder topHorizontalBorder = new StringBuilder();
        StringBuilder bottomHorizontalBorder = new StringBuilder();
        short currentIndex = 0;
        contentRow.append(verticalBorderChar);
        for(String argument : arguments) {
            contentRow.append(emptySpaceFillerChar);
            contentRow.append("[");
            contentRow.append(currentIndex);
            contentRow.append("]");
            contentRow.append(argument);
            currentIndex++;
        }
        contentRow.append(emptySpaceFillerChar);
        contentRow.append(verticalBorderChar);
        for(int i=0; i<contentRow.length();i++) {
            topHorizontalBorder.append(topHorizontalBorderChar);
            bottomHorizontalBorder.append(bottomHorizontalBorderChar);
        }
        System.out.println(topHorizontalBorder);
        System.out.println(contentRow);
        System.out.println(bottomHorizontalBorder);
        return arguments.length;
    }
}
