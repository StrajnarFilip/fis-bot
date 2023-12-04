package fis.bot.handlers;

import fis.bot.common.RoleManagement;
import fis.bot.entity.ReactionRoleEntity;
import java.util.logging.Logger;
import org.javacord.api.entity.message.Reaction;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

public class ReactionRoleRemovalHandler implements ReactionRemoveListener {
  private static Logger logger = Logger.getLogger("ReactionRoleRemovalHandler");

  private static void handleRemoval(
      Server server, long messageId, Reaction eventReaction, User user) {
    RoleManagement.findReactionRoleMessageForMessage(
        messageId,
        reactionRoleMessage -> {
          for (ReactionRoleEntity reactionRole : reactionRoleMessage.reactionRoles) {
            if (eventReaction.getEmoji().equalsEmoji(reactionRole.reaction)) {
              RoleManagement.findRoleByName(
                  server, reactionRole.role, role -> role.removeUser(user));
            }
          }
        });
  }

  @Override
  public void onReactionRemove(ReactionRemoveEvent event) {
    event
        .getReaction()
        .ifPresent(
            reaction ->
                event
                    .getServer()
                    .ifPresent(
                        server ->
                            event
                                .getUser()
                                .ifPresent(
                                    user -> {
                                      logger.info(
                                          user.getName()
                                              + " wants to remove the role: "
                                              + event.getEmoji());
                                      handleRemoval(server, event.getMessageId(), reaction, user);
                                    })));
  }
}
