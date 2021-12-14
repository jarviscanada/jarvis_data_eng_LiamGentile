package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "indices",
    "screenName",
    "id",
    "idStr"
})

public class UserMention {

  @JsonProperty("name")
  private String name;

  @JsonProperty("indices")
  private List<Integer> indices;

  @JsonProperty("screenName")
  private String screenName;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("idStr")
  private String idStr;

  @JsonProperty("name")
  public String getName() {
    return name;
  }
  @JsonProperty("name")
  public void setName() {
    this.name = name;
  }

  @JsonProperty("indices")
  public List<Integer> getIndices() {
    return indices;
  }
  @JsonProperty("indices")
  public void setIndices() {
    this.indices = indices;
  }

  @JsonProperty("screenName")
  public String getScreenName() {
    return screenName;
  }
  @JsonProperty("screenName")
  public void setScreenName() {
    this.screenName = screenName;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }
  @JsonProperty("id")
  public void setId() {
    this.id = id;
  }

  @JsonProperty("idStr")
  public String getIdStr() {
    return idStr;
  }
  @JsonProperty("idStr")
  public void setIdStr() {
    this.idStr = idStr;
  }
}
