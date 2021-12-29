package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hashtags",
    "userMentions"
})
public class Entities {

  @JsonProperty("hashtags")
  private List<Hashtag> hashtags;

  @JsonProperty("userMentions")
  private List<UserMention> userMentions;

  @JsonProperty("hashtags")
  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  @JsonProperty("hashtags")
  public void setHashtags() {
    this.hashtags = hashtags;
  }

  @JsonProperty("userMentions")
  private List<UserMention> getUserMentions() {
    return userMentions;
  }

  @JsonProperty("userMentions")
  private void setUserMentions() {
    this.userMentions = userMentions;
  }

}
