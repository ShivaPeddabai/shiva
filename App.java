
//Observer pattern program to analyze criccket statistics and data

//Import pakages
import java.util.ArrayList;
import java.util.Iterator;

//Java code To implement observer pattern of Cricket Data For Match T20
//create interface having different  methods
interface Cricket {
    public void regObs(Observer o); // function1 for registeroberevers

    public void notifyObs(); // function2 for notifyingobservers

    public void unregObs(Observer o); // function3 for unregister obsever

}

// create class For cricket data inherit inteface cricket
class CricketData implements Cricket {
    int Totalruns; // variable declare to store total runs
    int Totalwickets; // variable declare to store wickets
    float Totalovers; // variable declare to store overs
    ArrayList<Observer> observerList; // declae array for observerlist

    public CricketData() {
        observerList = new ArrayList<Observer>(); // object
    }

    public void regObs(Observer obs) {
        observerList.add(obs); // add registerobservers
    }

    public void unregObs(Observer obs) {
        observerList.remove(observerList.indexOf(obs)); // remove unregister observers from stadium
    }

    // Notify obsevsers by giving updates.
    public void notifyObs() {
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();) {
            Observer obs = it.next();
            obs.update(Totalruns, Totalwickets, Totalovers);
        }
    }

    // get latest runs
    private int getRuns() {
        // return 200 for simplicity
        return 200;
    }

    // get latest wickets
    private int getWickets() {
        // return 4 for simplicity
        return 4;
    }

    // get latest overs
    private float getOvers() {
        // return 15 for simplicity
        return (float) 15.5;
    }

    // This function is to update displays when data updated
    public void dataupdated() {
        // get latest data
        Totalruns = getRuns();
        Totalwickets = getWickets();
        Totalovers = getOvers();

        notifyObs(); // call notify method
    }
}

// create another interface for when data updates
interface Observer {
    public void update(int runs, int wickets, float overs);
}

// display average score
class AverageScore implements Observer {
    private float rRate; // variable for runrate
    private int pScore; // for predicted score

    public void update(int runs, int wickets, float overs) {
        this.rRate = (float) runs / overs; // calculate runrate
        this.pScore = (int) (this.rRate * 20); // calculate predicted socre.
        displayData(); // call display to display data
    }

    // display method
    public void displayData() {
        System.out.println("\n            T20 Match Average_Score  \n\n" + "Run_Rate: " + rRate
                + "    Predicted_Score: " + pScore);
    }
}

// create current class for cuentscore
class CurrentScore implements Observer {
    private int Totalruns, Totalwickets; // variables
    private float Totalovers;

    // update current score
    public void update(int runs, int wickets, float overs) {
        this.Totalruns = runs;
        this.Totalwickets = wickets;
        this.Totalovers = overs;
        display();
    }

    public void display() {
        System.out.println("\n              T20 Match Current_Score  \n\n" + "Current_Runs: " + Totalruns
                + "      Current_Wickets:" + Totalwickets + "     Current_Overs: " + Totalovers);
    }
}

// main class
public class App {
    public static void main(String[] args) throws Exception { // main function here
        // create objects for testing
        AverageScore aScore = new AverageScore();
        CurrentScore cScore = new CurrentScore();

        // pass the displays to Cricket data
        CricketData cricketData = new CricketData();

        // register display elements
        cricketData.regObs(aScore);
        cricketData.regObs(cScore);

        // call this function when data updated
        cricketData.dataupdated();

        // call to unregobserver to emove
        cricketData.unregObs(aScore);

    }
}