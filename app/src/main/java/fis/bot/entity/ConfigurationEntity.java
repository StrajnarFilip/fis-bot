package fis.bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ConfigurationEntity {
  @JsonProperty("bot-token")
  public String botToken;

  @JsonProperty("welcome-channel-id")
  public String welcomeChannelId;

  @JsonProperty("roles-channel-id")
  public String rolesChannelId;

  @JsonProperty("reaction-role-messages")
  public List<ReactionRoleMessageEntity> reactionRoleMessages;
}
