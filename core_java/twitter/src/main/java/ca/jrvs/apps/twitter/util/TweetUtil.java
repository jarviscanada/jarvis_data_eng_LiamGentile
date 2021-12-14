package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

  public class TweetUtil {

    public static Tweet createTweet(String text, Double lon, Double lat) {

      final Logger logger = LoggerFactory.getLogger(TweetUtil.class);

      Coordinates geoCoordinates = new Coordinates();
      Tweet tweet = new Tweet();

      try {
        geoCoordinates.setCoordinates(Arrays.asList(lon, lat));
        geoCoordinates.setType("Point");
        tweet.setText(text);
        tweet.setCoordinates(geoCoordinates);
      } catch (Exception ex) {
        logger.info("failed to create tweet from TweetUtil");
        logger.error(ex.getMessage());
      }
      return tweet;
    }
  }

