package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "createdAt",
    "id",
    "idStr",
    "text",
    "entities",
    "coordinates",
    "retweetCount",
    "favouriteCount",
    "favourited",
    "retweeted"
})

public class Tweet {

  @JsonProperty("createdAt")
  private String createdAt;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("idStr")
  private String idStr;

  @JsonProperty("text")
  private String text;

  @JsonProperty("entities")
  private Entities entities;

  @JsonProperty("coordinates")
  private Coordinates coordinates;

  @JsonProperty("retweetCount")
  private Integer retweetCount;

  @JsonProperty("favouriteCount")
  private Integer favouriteCount;

  @JsonProperty("favourited")
  private Boolean favourited;

  @JsonProperty("retweeted")
  private Boolean retweeted;

  @JsonProperty("createdAt")
  public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("createdAt")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("idStr")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("idStr")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("retweetCount")
  public Integer getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweetCount")
  public void setRetweetCount(Integer retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonProperty("favouriteCount")
  public Integer getFavouriteCount() {
    return favouriteCount;
  }

  @JsonProperty("favouriteCount")
  public void setFavouriteCount(Integer favouriteCount) {
    this.favouriteCount = favouriteCount;
  }

  @JsonProperty("favourited")
  public Boolean getFavourited() {
    return favourited;
  }

  @JsonProperty("favourited")
  public void setFavourited(Boolean favourited) {
    this.favourited = favourited;
  }

  @JsonProperty("retweeted")
  public Boolean getRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }
}
