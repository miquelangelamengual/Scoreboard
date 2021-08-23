package es.hulk.scoreboard.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendConsole(String text) {
        Bukkit.getServer().getConsoleSender().sendMessage(text);
    }

}
