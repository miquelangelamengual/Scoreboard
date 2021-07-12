package es.hulk.scoreboard.command;

import es.hulk.scoreboard.utils.Utils;
import es.hulk.scoreboard.utils.command.BaseCommand;
import es.hulk.scoreboard.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class ScorboardCommand extends BaseCommand {

    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        if (cmd.getSender() instanceof Player) {
            if (player.hasPermission("scoreboard.command.reload")) {
                player.sendMessage(Utils.color("&a/scoreboard reload &7- &fReloads the scoreboard"));
            } else {
                player.sendMessage(Utils.color("&aScoreboard plugin created by Hulk#0226"));
            }
        }
    }
}
