package fis.bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReactionRoleEntity {
  @JsonProperty("reaction")
  public String reaction;

  @JsonProperty("role")
  public String role;
}
