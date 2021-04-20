import java.util.Arrays;
 
//----------for graphing----------
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;  
//----------for graphing----------
public class BirthdayParadox extends JFrame {

/* Random generator */
private static java.util.Random generator = new java.util.Random();
private static boolean[] set;
private static final long serialVersionUID = 1L;  //for graph

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
private static int oneRun(int range){
    
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
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();    
    int startRange = 0, endRange = 0;
    int numberOfRuns = 0;
        
    //no user inputs
    if (args.length == 0)
    {
        startRange = 100;
        endRange = 10000;
        numberOfRuns = 1000;
    }
 
    //all 3 inserted
    else if (args.length == 3)
    {
        try {
            startRange = Integer.parseInt(args[0]);
            endRange = Integer.parseInt(args[1]);
 
            if (startRange > endRange)
            {
                System.out.print("The starting range can't be larger than the end range!");
                System.exit(0);
            }
 
            numberOfRuns = Integer.parseInt(args[2]);
        }
 
        catch (NumberFormatException e) {
            System.out.print("Enter valid numbers only, please!");
        }
    }
 
    //not 3 or 0 inserted
    else 
    {
        System.out.print("Enter the 3 fields or no fields, please!");
        System.exit(0);
    }
 
    
    Statistics finalS = new Statistics(numberOfRuns);
    for (int i = startRange; i <= endRange; i = i + startRange)
    {
        finalS = runExperiments(i, numberOfRuns);
        System.out.println(finalS);
        dataset.addValue(finalS.standardDeviation(), "Standard deviation","Range" + i);
        dataset.addValue(finalS.average(), "Mean","Range" + i);
    }
    
    SwingUtilities.invokeLater(() -> {  
     	//BirthdayParadox example = new BirthdayParadox("Mean and standard deviation");
     	
     	JFreeChart chart = ChartFactory.createLineChart("Mean and standard deviation", "Run" , "", dataset);
        ChartPanel panel = new ChartPanel(chart);
        ApplicationFrame frame = new ApplicationFrame("Mean and standard deviation");
     	frame.setContentPane(panel);
     	frame.pack();
     	frame.setVisible(true);
     });
  
 
} 
}