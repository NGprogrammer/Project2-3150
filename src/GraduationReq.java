/**
 * Brandon Ng
 * CISC 3150
 * Project 2
 * Java Code Program
 * Google Code Jam Graduation Requirement
 */
import java.util.*;
import java.io.*;
import java.math.*;

public class GraduationReq {
	final static String inputFile = "A-small-practice.in";     
    int size;	
    long X, Y;
    long[] arr1, arr2, arr3;
    long ans = 0;

    long numEven(long A, long B) {
        A++; B--;
        if (A%2 != 0) A++;
        if (B%2 != 0) B--;
        if (A > B) return 0;
        return (B - A) / 2 + 1;
    }

    void test(long ID) {
        List<Long> block = new ArrayList<Long>();
        
        for (int i=0;i<size;i++) {
            long travelTime = arr2[i] -arr1[i];
            if (travelTime < 0) 
            	travelTime += Y;
            
            long startID = mod(arr1[i] + arr3[i]);

            long meet = ID - startID;
        
            if (meet<0) 
            	meet += Y;
            if (meet<=2 * travelTime) {
                block.add(2 * arr3[i]+meet);
            }
            if (meet+Y<=2 * travelTime) {
                block.add(2 * arr3[i]+meet+Y);
            }
        }
        block.add(-1L);
        block.add(2*X+1);
        Collections.sort(block);
        for (int i=0;i+1<block.size();i++){
        	ans = Math.max(ans, numEven(block.get(i), block.get(i+1)));
        }
    }

    long mod(long X) {
        return (X%Y+Y)%Y;
    }
    void solve(Scanner scan, PrintWriter pw) {
        size = scan.nextInt();
        X = scan.nextLong();
        Y = scan.nextLong();
        arr1 = new long[size];
        arr2 = new long[size];
        arr3 = new long[size];
        
        for (int i=0;i<size;i++) {
            arr1[i] = scan.nextLong() - 1;
            arr2[i] = scan.nextLong() - 1;
            arr3[i] = scan.nextLong();
        }

        for (int i=0; i<size; i++) {
            long startID = mod(arr1[i] + arr3[i]);
            long travelTime = arr2[i] - arr1[i];
            
            if (travelTime < 0) 
            	travelTime += Y;
            
            long finishTime = arr3[i] + travelTime;
            long finishID = mod(arr2[i] + finishTime);
            for (int delta = -1; delta <= 1; delta++) {
                test(mod(startID + delta));
                test(mod(finishID + delta));
            }
        }
        test(0);
        pw.println(Math.max(0, ans - 1));
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new FileReader(inputFile)); 
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt")); 
        int cases = scan.nextInt();
        for (int i=0;i<cases;i++) {
            System.out.println("Processing test case " + (i+1));
            pw.print("Case #" + (i+1) + ": ");
            new GraduationReq().solve(scan, pw);
        }
        pw.flush();
        pw.close();
        scan.close();
    }
}