package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {

  private TwitterDao dao;

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
  }


  @Test
  public void create() throws Exception {
    String hashTag = "#twitterapi";
    String text = "this is an integration test" + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;

    Tweet tweet = TweetUtil.createTweet(text, lat, lon);
    Tweet tweetPosted = dao.create(tweet);

    assertEquals(text, tweetPosted.getText());
    assertNotNull(tweetPosted.getCoordinates());
    assertEquals(2, tweetPosted.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweetPosted.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, tweetPosted.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(tweetPosted.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void findById() {
    String id = "1470426108462092290";
    Tweet find = dao.findById(id);
  }

  @Test
  public void deleteById() {
    String id = "1468968755204014088";
    Tweet delete = dao.deleteById(id);
  }

}
