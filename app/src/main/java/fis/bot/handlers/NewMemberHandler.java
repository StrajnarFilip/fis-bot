package fis.bot.handlers;

import fis.bot.App;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

public class NewMemberHandler implements ServerMemberJoinListener {
    @Override
    public void onServerMemberJoin(ServerMemberJoinEvent event) {
        String userMention = event.getUser().getMentionTag();
        event.getServer()
                .getTextChannelById(App.getConfiguration().rolesChannelId).ifPresent(rolesChannel -> {
                    String rolesChannelTag = rolesChannel.getMentionTag();
                    event.getServer().getTextChannelById(App.getConfiguration().welcomeChannelId).ifPresent(welcomeChannel -> welcomeChannel.sendMessage(userMention + " V kanalu "
                            + rolesChannelTag
                            + " si lahko nastaviš poljubne vloge. "
                            + "Priporočeno je tudi, da si vzdevek nastaviš (change nickname) na svoje pravo ime."
                    ));
                });
    }
}
