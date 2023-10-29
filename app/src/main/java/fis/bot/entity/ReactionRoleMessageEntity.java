package fis.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReactionRoleMessageEntity {
    @JsonProperty("message-link")
    public String messageLink;

    @JsonProperty("only-one")
    public boolean onlyOne;

    @JsonProperty("reaction-roles")
    public List<ReactionRoleEntity> reactionRoles;

    @JsonIgnore
    public String getServerId() {
        String[] parts = messageLink.split("/");
        return parts[4];
    }

    @JsonIgnore
    public String getChannelId() {
        String[] parts = messageLink.split("/");
        return parts[5];
    }

    @JsonIgnore
    public String getMessageId() {
        String[] parts = messageLink.split("/");
        return parts[6];
    }
}
