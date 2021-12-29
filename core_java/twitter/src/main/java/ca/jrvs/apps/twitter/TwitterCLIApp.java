package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  final Logger logger = LoggerFactory.getLogger(TwitterCLIApp.class);

  private Controller controller;

  @Autowired
  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtil.toJson(tweet, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Couldn't convert tweet to string", e);
    }
  }

  public void run(String[] args) throws JsonProcessingException {
    if (args.length < 2) {
      logger.info("incorrect number of arguments");
      throw new IllegalArgumentException("USAGE: TwitterCLIAPP post|show|delete [options]");
    }
    switch (args[0]) {
      case "post":
        printTweet(controller.postTweet(args));
        break;
      case "show":
        printTweet(controller.showTweet(args));
        break;
      case "delete":
        controller.deleteTweet(args).forEach(this::printTweet);
        break;
      default:
        throw new IllegalArgumentException("USAGE: TwitterCLIAPP post|show|delete [options]");

    }
  }

  public static void main(String[] args) {

    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    CrdDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = (Controller) new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);

    try {
      app.run(args);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("couldn't convert tweet to string");
    }


  }


}
