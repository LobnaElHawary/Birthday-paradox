import java.util.Arrays;

public class BirthdayParadox {

/* Random generator */
private static java.util.Random generator = new java.util.Random();

private static boolean[] set;

/* The function runExperiments runs the series of experiments, and stores the result into a Statistics object. 
 * The parameter range is the size of the set from which random number are drawn. The parameter numberOfRuns is the number 
 * of experiments to run. The function returns
 
a reference to a Statistics instance that holds the result of the experiment (min, max, average, stdev).
*/

public static Statistics runExperiments(int range, int numberOfRuns)
{ 
	
    Statistics S = new Statistics (numberOfRuns);
	set = new boolean[range];
	
	for(int i = 0; i < numberOfRuns; i++)
        S.updateStatistics(oneRun(range));
        
    return S; 
}


/* Runs a single experiment. The parameter range defines the size of the set from which the numbers are drawn. 
 * The method returns the number of random draws in the set that the method underwent before drawing an element of 
 * the set for the second time
*/
private static int oneRun(int range) {
	
	Arrays.fill(set, false);
	int numOfDraws = 0;
	
	//keep drawing until we finish the range
    while (true) 
    {
		
		int randomNum = generator.nextInt(range); //random num between 0 -> range 
        numOfDraws++;
        
		if(set[randomNum] == false)
			set[randomNum] = true; // to indicate that it was drawn
        
        //then repetition was found
        else 
            return numOfDraws;
    }
	
}

	
public static void main(String[] args) {
	int range = 0;
	int numberOfRuns = 0;
        
    //no inputs
    if (args.length == 0)
    {
        range = 365;
        numberOfRuns = 50;
        System.out.print("Range is: " + range + "\n" + "Number of runs is: " + numberOfRuns + "\n"); 
    }
    
    //just the range inserted, so default #ofRuns
    else if (args.length == 1)
    {
        numberOfRuns = 50;
        System.out.print("Number of runs is: " + numberOfRuns + "\n"); 

        try {
            range = Integer.parseInt(args[0]);
        }
        
        catch (NumberFormatException e) {
            System.out.print("Enter valid numbers only, please!\n");
        }
    }

    //both inserted
    else if (args.length == 2)
    {
        try {
            range = Integer.parseInt(args[0]);
            numberOfRuns = Integer.parseInt(args[1]);
        }

        catch (NumberFormatException e) {
            System.out.print("Enter valid numbers only, please!\n");
        }
    }

    //more than 2 inserted; take first 2, warn user
    else 
    {
        try {
            range = Integer.parseInt(args[0]);
            numberOfRuns = Integer.parseInt(args[1]);
            System.out.print("Please note that more than 2 variables were entered, the first 2 have been taken for the range and #ofruns respectively, but check your variables in case you made a writing error!"); 
        }

        catch (NumberFormatException e) {
            System.out.print("Enter valid numbers only, please!\n");
        }
    }
    
    Statistics finalS = new Statistics(numberOfRuns);
    finalS = runExperiments(range, numberOfRuns);
    System.out.println(finalS);
	
} 
}
