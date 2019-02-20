/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfparser;

/**
 *
 * @author northernpike
 */
public class DfEntry {
    private String filesystem;
    private Long kBlocks;
    private Long used;
    private Long available;
    private int usePercentage;
    private String mountedOn;

    public DfEntry(String filesystem, Long kBlocks, Long used, Long available, int usePercentage, String mountedOn) {
        this.filesystem = filesystem;
        this.kBlocks = kBlocks;
        this.used = used;
        this.available = available;
        this.usePercentage = usePercentage;
        this.mountedOn = mountedOn;
    }

    @Override
    public String toString() {
        return filesystem + "  " + kBlocks + "  " + used + "  " + available + "  " + usePercentage + "%  " + mountedOn; 
    }
    
    
    
    
}
