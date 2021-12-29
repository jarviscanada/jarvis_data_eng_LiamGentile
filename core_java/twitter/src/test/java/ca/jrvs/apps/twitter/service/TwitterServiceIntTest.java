package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {

  private CrdDao dao;
  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(
        CONSUMER_KEY,
        CONSUMER_SECRET,
        ACCESS_TOKEN,
        TOKEN_SECRET);

    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {
    Tweet tweet = new Tweet();
    Coordinates coordinates = new Coordinates();
    List<Double> geoAddress = new ArrayList<>();
    geoAddress.add(0,45d);
    geoAddress.add(1, -45d);
    coordinates.setCoordinates(geoAddress);
    tweet.setCoordinates(coordinates);
    tweet.setText("this is an integration test");
    Tweet tweetPost = service.postTweet(tweet);
  }

  @Test
  public void showTweet() {
    String id = "1470472420372979716";
    Tweet tweet = service.showTweet(id, null);
  }

  @Test
  public void deleteTweets() {
    String [] ids = new String[] { "1470472420372979716", "1470426108462092290" };
    List<Tweet> tweets = service.deleteTweets(ids);
  }
}
