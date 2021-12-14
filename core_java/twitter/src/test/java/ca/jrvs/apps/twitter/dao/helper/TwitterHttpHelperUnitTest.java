package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperUnitTest {

  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");

  @Test
  public void httpPost() throws Exception {
    HttpHelper httpHelperP = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    HttpResponse response = httpHelperP.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Testing-httpPost-method"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws Exception {
    HttpHelper httpHelperG = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    HttpResponse response = httpHelperG.httpGet(new URI("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=liam_gentile"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}