
package dfparser;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author northernpike
 */
public class DfParserTest {
    DfParser parser;
    String first;
    String second;
    String third;
    String fourth;
    String fifth;
        
    @Before
    public void setUp() {
        parser = new DfParser();
        first = "/dev/loop5        178688   178688         0 100% /snap/spotify/32";
        second = "/dev/sda1         523248     7340    515908   2% /boot/efi";
        third = "tmpfs             804660       52    804608   1% /run/user/1000";
        fourth = "udev             3993544        0   3993544   0% /dev";
        fifth  = " /dev/sda2      114288232 62033112  46426564  58% / ";
    }
    
    public String removeExtra(String line) {
        line = line.replace("%", "");
        line = line.replaceAll("( +)","").trim();
        return line;
    }
    
    @Test
    public void testCreateEntryWithValidData() {
        parser.CreateEntry(first);
        parser.CreateEntry(second);
        assertEquals(2, parser.getEntries().size());
        assertTrue(removeExtra(parser.getEntries().get(0).toString()).equals(removeExtra(first)));
        assertTrue(removeExtra(parser.getEntries().get(1).toString()).equals(removeExtra(second)));
    }
    
    @Test
    public void testCreateEntryWithFiveSpacesButNotValidData() {
        parser.CreateEntry("this string has five spaces surely");
        assertEquals(0, parser.getEntries().size());
    }
    
    public void createFiveEntries() {
        parser.CreateEntry(first);
        parser.CreateEntry(second);
        parser.CreateEntry(third);
        parser.CreateEntry(fourth);
        parser.CreateEntry(fifth);
    }
    
    public void goToLastIndex() {
        for (int i = 0; i < 4; i++) {
            parser.selectNextEntry();
        }        
    }


    @Test
    public void testRemoveEntryWhenThereAreEntries() {
        createFiveEntries();
        assertEquals(parser.getLineIndex(), 0);
        parser.removeEntry();
        assertEquals(parser.getEntries().size(), 4);
        assertFalse(removeExtra(parser.getEntries().get(0).toString()).equals(removeExtra(first)));
        assertTrue(removeExtra(parser.getEntries().get(0).toString()).equals(removeExtra(second)));
        assertEquals(parser.getLineIndex(), 0);
    }

    @Test
    public void testRemoveEntryWhenThereAreEntriesAndIndexIsPointingToLastEntryOfList() {
        createFiveEntries();
        goToLastIndex();
        assertEquals(parser.getLineIndex(), 4);
        parser.removeEntry();
        assertEquals(parser.getEntries().size(), 4);
        assertTrue(removeExtra(parser.getEntries().get(3).toString()).equals(removeExtra(fourth)));
        assertEquals(parser.getLineIndex(), 0);
    }


    @Test
    public void testRemoveEntryWhenThereAreNoEntries() {
        parser.removeEntry();
        assertEquals(parser.getLineIndex(), 0);
        assertTrue(parser.entriesIsEmpty());
    }    
    
    @Test
    public void testSelectNextEntryWhenThereAreEntries() {
        createFiveEntries();
        parser.selectNextEntry();
        assertEquals(parser.getLineIndex(), 1);
    }
    
    @Test
    public void testSelectNextEntryWhenThereAreNoEntries() {
        parser.selectNextEntry();
        assertEquals(parser.getLineIndex(), 0);
        assertTrue(parser.entriesIsEmpty());
    }
    
    
    @Test
    public void testSelectNextEntryWhenThereAreEntriesAndIndexIsPointingToLastEntryOfList() {
        createFiveEntries();
        goToLastIndex();
        assertEquals(parser.getLineIndex(), 4);
        parser.selectNextEntry();
        assertEquals(parser.getLineIndex(), 0);
    }
}
