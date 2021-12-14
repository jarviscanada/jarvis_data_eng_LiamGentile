package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Repository
public class TwitterDao implements CrdDao<Tweet, String> {

  final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    Integer status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        logger.error(e.getMessage());
      }
      throw new RuntimeException("Unexpected HTTP status" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    String jsonStr;

    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new RuntimeException("Couldn't convert entity to String", e);

    }

    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new RuntimeException("Unable to convert JSON to object", e);
    }
    return tweet;
  }

  @Override
  public Tweet create(Tweet tweet) {
    URI uri;

    PercentEscaper percentEscaper = new PercentEscaper("", false);
    List<Double> globalAddress = tweet.getCoordinates().getCoordinates();

    String uriGlobal = "status" + EQUAL + percentEscaper.escape(tweet.getText())
        + AMPERSAND
        + "longitude" + EQUAL + globalAddress.get(0)
        + AMPERSAND
        + "latitude" + EQUAL + globalAddress.get(1);

    uri = URI.create(API_BASE_URI
        + POST_PATH
        + QUERY_SYM
        + uriGlobal);

    HttpResponse response = httpHelper.httpPost(uri);

    try {
      return parseResponseBody(response, HTTP_OK);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException("invalid tweet input", e);
    }
  }

  @Override
  public Tweet findById(String id) {
    URI uri;

    try {
      String findPath = API_BASE_URI
          + SHOW_PATH
          + QUERY_SYM
          + "id"
          + EQUAL
          + id;

      uri = new URI(findPath);
    } catch (URISyntaxException e) {
      logger.error(e.getMessage());
      throw new IllegalArgumentException("invalid id", e);
    }
    HttpResponse response = httpHelper.httpGet(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String id) {
    URI uri;

    try {
      String findPath = API_BASE_URI
          + DELETE_PATH
          + "/"
          + id
          + ".json";

      uri = new URI(findPath);
    } catch (URISyntaxException e) {
      logger.error(e.getMessage());
      throw new IllegalArgumentException("invalid id", e);
    }
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }


}
