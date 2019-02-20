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
public class Main {
    
    public static void main(String[] args) {
        DfParser parser = new DfParser();
        parser.initialize();
        UI ui = new UI(parser);
        ui.startUI();
    }
    
    
    
}
