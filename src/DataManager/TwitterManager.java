package DataManager;

import SentimentAnalisys.SentimentClassifier;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author gina
 *
 */
public class TwitterManager {

    SentimentClassifier sentClassifier;
    int LIMIT = 500; //the number of retrieved tweets
    ConfigurationBuilder cb;
    Twitter twitter;
    public String text, sent, name = "";

    public TwitterManager() {
        cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("inGLvTbOEtJdzmopJ71HK15Le");
        cb.setOAuthConsumerSecret("LPHGTUaU86Ns9L0xjWZMRgE46koCEZeDVm7fJqjn3gD7TPdTC5");
        cb.setOAuthAccessToken("930707574-WPNifofVo9TY4z9YMmotIAiXF1xwyx3jHKMrtQqv");
        cb.setOAuthAccessTokenSecret("ZDSkGybEKxOPwa6GcaMNBavR8R1BBZLhvLFPNPxvIMtd7");
        twitter = new TwitterFactory(cb.build()).getInstance();
        sentClassifier = new SentimentClassifier();
    }

    public void performQuery(String inQuery) throws InterruptedException, IOException, TwitterException {
        Query query = new Query(inQuery);
        query.setCount(100);
        try {
            int count = 0;
            QueryResult r;
            do {
                r = twitter.search(query);
                ArrayList ts = (ArrayList) r.getTweets();

                for (int i = 0; i < ts.size() && count < LIMIT; i++) {
                    count++;
                    Status t = (Status) ts.get(i);
                    text = t.getText();
                    //System.out.println("Text: " + text);
                    name = t.getUser().getScreenName();
                    //System.out.println("User: " + name);
                    sent = sentClassifier.classify(t.getText());
                    //System.out.println("Sentiment: " + sent);
                }
            } while ((query = r.nextQuery()) != null && count < LIMIT);
        } catch (TwitterException te) {
            //System.out.println("Couldn't connect: " + te);
            JOptionPane.showMessageDialog(null, "Couldn't connect: " + te);
        }
    }

}
