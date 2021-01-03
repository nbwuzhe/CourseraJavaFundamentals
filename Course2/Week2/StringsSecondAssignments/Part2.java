
/**
 * Write a description of Part2 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int currIndex = 0;
        int encounterNum = 0;
        while (currIndex != -1) {
            currIndex = stringb.indexOf(stringa);
            
            if (currIndex != -1) {
                stringb = stringb.substring(currIndex + stringa.length());
                encounterNum ++;
            }
        }
        return encounterNum;
    }
    
    public void testHowMany() {
        if (howMany("GAA", "ATGAACGAATTGAATC") != 3) {
            System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
            System.out.println("Test 1 failed!");
        }
        
        if (howMany("AA", "ATAAAA") != 2) {
            System.out.println(howMany("AA", "ATAAAA"));
            System.out.println("Test 2 failed!");
        }
        
        System.out.println("End of testHowMany.");
    }
}
