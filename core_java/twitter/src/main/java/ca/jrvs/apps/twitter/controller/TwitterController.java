package ca.jrvs.apps.twitter.controller;

import static ca.jrvs.apps.twitter.util.TweetUtil.createTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Controller
public class TwitterController {

  final Logger logger = LoggerFactory.getLogger(TwitterController.class);

  private static final String COORD_SEP = ":";
  private static final String COMMA = ", ";

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  public Tweet postTweet(String[] args) {

    if (args.length != 3) {
      logger.info("invalid # of arguments in postTweet");
      throw new IllegalArgumentException(
          "Usage: TwitterCLIApp post 'tweet_text' 'latitude:longitude'");
    }

    String tweet = args[1];
    String coordinates = args[2];
    String[] coordinatesArray = coordinates.split(COORD_SEP);

    if (coordinatesArray.length != 2 || StringUtils.isEmpty(tweet)) {
      logger.info("error in coordinate formatting in postTweet");
      throw new IllegalArgumentException("Coordinate formatting incorrect \n"
          + "Usage: TwitterCLIApp post 'tweet_text' 'latitude:longitude'");
    }

    Double latitude = null;
    Double longitude = null;
    try {
      latitude = Double.parseDouble(coordinatesArray[0]);
      longitude = Double.parseDouble(coordinatesArray[1]);
    } catch (Exception e) {
      logger.info("error in coordinate formatting in postTweet");
      throw new IllegalArgumentException("Coordinate formatting incorrect \n"
          + "Usage: TwitterCLIApp post 'tweet_text' 'latitude:longitude'", e);
    }

    Tweet postTweet = createTweet(tweet, longitude, latitude);
    return service.postTweet(postTweet);
  }

  public Tweet showTweet(String[] args) {
    if (args.length != 2) {
      logger.info("wrong number of arguments for showTweet");
      throw new IllegalArgumentException("Usage: TwitterCLIApp show id");
    }
    String[] arguments = args[2].split(COMMA);
    return service.showTweet(args[1], arguments);
  }

  public List<Tweet> deleteTweets(String[] args) {
    if (args.length != 2) {
      logger.info("wrong number of arguments for deleteTweets");
      throw new IllegalArgumentException("Usage: TwitterCLIApp delete id, id2, id3...");
    }
    String[] ids = args[1].split(COMMA);
    return service.deleteTweets(ids);
  }
}


