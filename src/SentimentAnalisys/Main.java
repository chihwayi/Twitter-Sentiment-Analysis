package SentimentAnalisys;

import DataManager.TwitterManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.TwitterException;

/**
 *
 * @author gina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException, TwitterException{
        TwitterManager twitterManager = new TwitterManager();  
        twitterManager.performQuery("Arsenal"); 
    }
    
    /**
     * Train example function
     */
    public static void train(){
        try {
            Trainer t = new Trainer();
            t.Train();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Clissify example function
     * @param str   Text to classify
     * @return Category name
     */
    public static String classify(String str){
        SentimentClassifier sc = new SentimentClassifier();
        return sc.classify(str);
    }
    
}
