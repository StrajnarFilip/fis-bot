package fis.bot.common;

import fis.bot.App;
import fis.bot.entity.ReactionRoleEntity;
import fis.bot.entity.ReactionRoleMessageEntity;
import java.util.List;
import java.util.function.Consumer;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;

public final class RoleManagement {

  private RoleManagement() {}

  public static void findRoleByName(Server server, String roleName, Consumer<Role> consumeRole) {
    server.getRoles().stream()
        .filter(role -> role.getName().equalsIgnoreCase(roleName))
        .findFirst()
        .ifPresent(consumeRole);
  }

  public static void findReactionRoleMessageForMessage(
      long messageId, Consumer<ReactionRoleMessageEntity> reactionRoleMessageConsumer) {
    List<ReactionRoleMessageEntity> reactionRoleMessages =
        App.getConfiguration().reactionRoleMessages;
    reactionRoleMessages.stream()
        .filter(
            reactionRoleMessage ->
                reactionRoleMessage.getMessageId().equalsIgnoreCase(String.valueOf(messageId)))
        .findFirst()
        .ifPresent(reactionRoleMessageConsumer);
  }

  public static void findRoleForReaction(
      List<ReactionRoleEntity> reactionRoles, Emoji reaction, Consumer<String> roleConsumer) {
    reactionRoles.stream()
        .filter(reactionRole -> reaction.equalsEmoji(reactionRole.reaction))
        .findFirst()
        .map(reactionRole -> reactionRole.role)
        .ifPresent(roleConsumer);
  }
}
