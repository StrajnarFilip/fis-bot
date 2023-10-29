package fis.bot.handlers;

import fis.bot.common.RoleManagement;
import fis.bot.entity.ReactionRoleMessageEntity;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

import java.util.List;

public class ReactionRoleHandler implements ReactionAddListener {
    private static void handleRoleAssignment(ReactionAddEvent event, ReactionRoleMessageEntity reactionRoleMessage) {
        List<String> roleNames = reactionRoleMessage.reactionRoles.stream()
                .map(reactionRole -> reactionRole.role).toList();

        event.getServer().ifPresent(server -> event.getUser().ifPresent(user -> {

            // If only one role can be chosen, remove the other ones.
            if (reactionRoleMessage.onlyOne) {
                for (String roleName :
                        roleNames) {
                    removeRole(server, user, roleName);
                }
            }

            // Assign the corresponding role.
            RoleManagement.findRoleForReaction(
                    reactionRoleMessage.reactionRoles,
                    event.getEmoji(),
                    roleToAssign -> assignRole(server, user, roleToAssign));
        }));
    }

    private static void assignRole(Server server, User user, String roleToAdd) {
        RoleManagement.findRoleByName(server, roleToAdd, role -> role.addUser(user));
    }

    private static void removeRole(Server server, User user, String roleToRemove) {
        RoleManagement.findRoleByName(server, roleToRemove, role -> role.removeUser(user));
    }


    @Override
    public void onReactionAdd(ReactionAddEvent event) {
        RoleManagement.findReactionRoleMessageForMessage(event.getMessageId(),
                reactionRoleMessage -> handleRoleAssignment(event, reactionRoleMessage));
    }
}
