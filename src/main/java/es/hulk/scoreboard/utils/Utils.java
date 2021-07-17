package es.hulk.scoreboard.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
