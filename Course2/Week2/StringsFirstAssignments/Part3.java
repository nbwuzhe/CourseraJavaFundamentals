
/**
 * Write a description of Part3 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int index1 = stringb.indexOf(stringa);
        if (index1 == -1){
            return false;
        }
        
        int index2 = stringb.indexOf(stringa, index1 + stringa.length());
        if (index2 == -1){
            return false;
        }
        
        return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        int index1 = stringb.indexOf(stringa);
        if (index1 == -1) {
            return stringb;
        }
        
        index1 += stringa.length();
        return stringb.substring(index1);
    }
    
    public void testing() {
        String a, b, resString;
        boolean res;
        
        a = "a";
        b = "banana";
        res = twoOccurrences (a, b);
        System.out.println("String A = " + a + ", String B = " + b + ", Is Two Occurrences? " + res);
        
        a = "by";
        b = "A story by Abby Long";
        res = twoOccurrences (a, b);
        System.out.println("String A = " + a + ", String B = " + b + " , Is Two Occurrences? " + res);
        
        a = "atg";
        b = "ctgtatgta";
        res = twoOccurrences (a, b);
        System.out.println("String A = " + a + ", String B = " + b + ", Is Two Occurrences? " + res);
        
        a = "a";
        b = "banana";
        resString = lastPart(a, b);
        System.out.println("String A = " + a + ", String B = " + b + ", Last Part is " + resString);
        
        a = "by";
        b = "A story by Abby Long";
        resString = lastPart (a, b);
        System.out.println("String A = " + a + ", String B = " + b + ", Last Part is " + resString);
        
        a = "atg";
        b = "ctgtatgta";
        resString = lastPart (a, b);
        System.out.println("String A = " + a + ", String B = " + b + ", Last Part is " + resString);
        
        a = "zoo";
        b = "forest";
        resString = lastPart (a, b);
        System.out.println("String A = " + a + ", String B = " + b + ", Last Part is " + resString);
    }
}
