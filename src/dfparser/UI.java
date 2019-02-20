/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfparser;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author northernpike
 */
public class UI {
    
    private DfParser parser;
    private String header;
    
    public UI(DfParser parser) {
        this.parser = parser;
        this.header = "Filesystem 1K-blocks Used Available Use% Mounted on";
    }
    
    public void startUI() {
        Scanner scanner = new Scanner(System.in);
        
        this.printAllRows();
        
        while(true){
            if (parser.entriesIsEmpty()) {
                System.out.println("There are no more rows left. Closing the program as there is nothing you can do.");
                break;
            }
            System.out.println("_____________________________");
            System.out.println("You have currently selected line " + (parser.getLineIndex() + 1) + " with the following data:");
            this.printRow(parser.getLineIndex());
            System.out.println("Write help for commandlist");
            String answer = scanner.nextLine();
            
            if (answer.equals("1")) {
                parser.selectNextEntry();
            } else if (answer.equals("2")) {
                parser.removeEntry();
            } else if (answer.equals("3")) {
                this.printAllRows();
            } else if (answer.equals("exit")) {
                break;
            } else if (answer.equals("help")) {
                System.out.println("Write 1 to show and select next line");
                System.out.println("Write 2 to delete current line and show next line");
                System.out.println("Write 3 to see all lines");
                System.out.println("Write exit to exit");
            }
            System.out.println("#########################################");
        }
    }
    
    public void printRow(int i) {
        System.out.println(header);
        System.out.println(parser.getEntries().get(i));
    }
    
    public void printAllRows() {
        ArrayList<DfEntry> entries = parser.getEntries();
        System.out.println(header);
        for (DfEntry entry : entries) {
            System.out.println(entry);
        }        
        System.out.println("There are " + entries.size() + " entries in the system.");
    }
}
