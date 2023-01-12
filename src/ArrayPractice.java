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
                twos++;
            }
            else if (d1+d2 == 12){
                twelves++;
            }
        }
        System.out.println("2: "+twos+"\n 12:"+twelves);
    }

    public static void main(String[] args) {
        ArrayPractice ap = new ArrayPractice();
        ap.simpleSimulate(7);
    }

    
}
