package es.hulk.scoreboard.command;

import es.hulk.scoreboard.utils.Utils;
import es.hulk.scoreboard.utils.command.BaseCommand;
import es.hulk.scoreboard.utils.command.Command;
import es.hulk.scoreboard.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class ScoreboardCommand extends BaseCommand {

    @Command(name = "scoreboard")
    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        if (cmd.getSender() instanceof Player) {
            if (player.hasPermission("scoreboard.command.reload")) {
                player.sendMessage(Utils.color("&a/scoreboard reload &7- &fReloads the scoreboard"));
                return;
            }
            player.sendMessage(Utils.color("&aScoreboard plugin created by Hulk#0226"));
        }
    }
}
