/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package fis.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class App {

    public static void main(String[] args) {
        String botToken = System.getenv("DISCORD_TOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(botToken).setAllIntents().login().join();
    }
}