package dfparser;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author northernpike
 */

public class DfParser {
    
    private ArrayList<DfEntry> entries;
    private int lineIndex;
    
    public DfParser() {
        this.entries = new ArrayList<>();
        this.lineIndex = 0;
    }
    
    public void initialize() {
        String line = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("df");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            br.readLine(); //skipping header
            
            while (true) {
                line = br.readLine();
                if (line == null ) {
                    break;
                }
                this.CreateEntry(line);
            }
            process.waitFor();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("The app was unable to initilize. This program works only on linux computer.");
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }
    
    private String[] validate(String line) {
        line = line.replace("%", "");
        line = line.replaceAll("( +)"," ").trim();
        String[] data = line.split(" ");
        return data;
    }
 
    public void CreateEntry(String line) {
        String[] data = this.validate(line);
        try {
            DfEntry entry = new DfEntry(data[0], Long.parseLong(data[1]), Long.parseLong(data[2]), Long.parseLong(data[3]), Integer.parseInt(data[4]), data[5]);
            entries.add(entry);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("There was problem with creating one of the DF entries containing following information " + line + ". It was not added to list.");
        }        
    }
    
    public void removeEntry() {
        if (0 <= lineIndex && lineIndex < entries.size()) {
            entries.remove(lineIndex);
            if (lineIndex == entries.size()) {
                this.selectNextEntry();
            }
        } else {
            System.out.println("Could not remove the selected line");
        }
    }
    
    public void selectNextEntry() {
        if (lineIndex < entries.size() - 1 ) {
            lineIndex ++;
        } else {
            System.out.println("There is no next line. Starting from start of list.");
            lineIndex = 0;            
        }
    }

    public ArrayList<DfEntry> getEntries() {
        return entries;
    }
    
    public boolean entriesIsEmpty() {
        return entries.isEmpty();
    }

    public int getLineIndex() {
        return lineIndex;
    }
}