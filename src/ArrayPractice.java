import edu.duke.*;
import java.io.File;
import java.util.Random;

public class ArrayPractice {

    public void simpleSimulate(int rolls){
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;

        for(int i=0; i<rolls; i++){
            int d1 = rand.nextInt(6) +1;
            int d2 = rand.nextInt(6) +1;
            if (d1+d2 == 2){
                twos+=1;
            }
            else if (d1+d2 == 12){
                twelves+=1;
            }
        }
        System.out.println("2: "+twos+"\n12: "+twelves);
    }


    public void simulate(int rolls){
        Random rand = new Random();
        int[] counts = new int[13];

        for(int i=0; i<rolls; i++){
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;
            int mysum = d1+d2;
            counts[mysum] += 1;
        }
        for(int i=2; i<13; i++){
            System.out.println(i+": "+counts[i]);
        }
    }





    public static void main(String[] args) {
        ArrayPractice ap = new ArrayPractice();
        // ap.simpleSimulate(10000);
        ap.simulate(10000);
    }


    
}
