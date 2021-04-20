/**
 * The class Statistics accumulates the results of the experiments. It knows
 * ahead of time how many experiments will be run, and provides at the end the
 * min, the max, the average and the standard deviation for the data.
 *
 * This class should not use classes such as Array, Lists etc. to store the
 * data, only primitive types and java arrays.
 *
 *
 */

public class Statistics {
    // ADD HERE INSTANCE VARIABLES DECLARATION
    public int min, max, numOfRuns, count;
    public double sum;

    int[] tempArray;

    public Statistics(int numberOfRuns)
    {
        min = 2147000000;
        max = 0;
        count = 0;
        sum = 0.0;
        numOfRuns = numberOfRuns;
        tempArray = new int[numOfRuns];
    }


    /* Updates statistics after one experiment. This method cannot be
    called more times than the parameter that was passed in the
    constructor. If it is, an error message should be printed and no
    change should occur. The param value the result returned from a new
    experiment
    */
    public void updateStatistics(int value)
    {
        //print error msg here
        //this could be done with a try and catch instead, but the description from the skeleton code
        //suggests it should be a simple if statement. Please contact us if a try and catch is required!
        if (count > numOfRuns)
           System.out.print("Exceeded maximum number of experiments, array will not get updated."); 

        else 
        {
            tempArray[count] = value; 
            count++;

            if (value < min)
                min = value;
            else if (value > max)
                max = value;
            else 
                ;   

            sum += value;
        }
    }


    /* The function returns the current average of the values passed to
    the method updateStatistic
    */
    public double average()
    {
        double avg;
        avg = sum/(double)count;
        
        return avg;
    }

    /* The function returns the current standard deviation of the values
    passed to the method updateStatistic
    */
    public double standardDeviation()
    {
        double std_dev = 0, avg = this.average();

        for (int i = 0; i < numOfRuns; i++)
            std_dev += Math.pow(tempArray[i] - avg, 2);

        return Math.sqrt(std_dev/(double)count);
    }



    /* this function returns the complete statistics information:
    current minimum, maximum, average, and stdev. For the last two, only
    two digit decimals are printed. The toString function will define how
    an object will be printed out if you use something like
    System.out.println(objectName). It simply returns a string back
    describing the text that will be printed out.
    */
    public String toString()
    {
        double avg = this.average(), std_dev = this.standardDeviation();
        
        String temp = "Minimum: " + Integer.toString(min) + "\t - \tMaximum: " + Integer.toString(max) + 
        ". \nAverage: " + String.format("%.2f", avg) + 
        "\t - \tStandard Deviation: " + String.format("%.2f", std_dev) + ". \n";

        return temp;
    }
}