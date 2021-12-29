package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  final Logger logger = LoggerFactory.getLogger(TwitterService.class);

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  private void validatePostTweet(Tweet tweet) {
    final Integer maxTextLen = 140;
    final Integer minGeoCoord = -90;
    final Integer maxGeoCoord = 90;
    String textBody = tweet.getText();
    Double longitude = tweet.getCoordinates().getCoordinates().get(0);
    Double latitude = tweet.getCoordinates().getCoordinates().get(1);

    if (textBody.length() > maxTextLen) {
      logger.info("exceeded permitted tweet text length");
      throw new IllegalArgumentException("max 140 characters allowed");
    } else if (longitude > maxGeoCoord || longitude < minGeoCoord) {
      logger.info("outside permitted longitude bounds");
      throw new IllegalArgumentException("range is -90 to 90");
    } else if (latitude > maxGeoCoord || latitude < minGeoCoord) {
      logger.info("outside permitted latitude bounds");
      throw new IllegalArgumentException("range is -90 to 90");
    }

  }

  private void validateId(String id) {
    try {
      Long.parseLong(id);
    } catch (NumberFormatException e) {
      logger.error(e.getMessage());
      throw new IllegalArgumentException("invalid tweet id");
    }
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateId(id);
    return (Tweet) dao.findById(id);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();

    try {
      Arrays.stream(ids).forEach(this::validateId);
      Arrays.stream(ids).forEach(id -> tweets.add((Tweet) dao.deleteById(id)));
    } catch (Exception e) {
      logger.error(e.getMessage());
      logger.info("could not delete list of tweets");
    }
    return tweets;
  }

}
