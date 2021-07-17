package es.hulk.scoreboard.command;

import es.hulk.scoreboard.Scoreboard;
import es.hulk.scoreboard.utils.Utils;
import es.hulk.scoreboard.utils.command.BaseCommand;
import es.hulk.scoreboard.utils.command.Command;
import es.hulk.scoreboard.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class ReloadCommand extends BaseCommand {

    private Scoreboard plugin = Scoreboard.getInstance();

    @Command(name = "scoreboard.reload", permission = "scoreboard.command.reload")
    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        plugin.getMainConfig().reload();
        player.sendMessage(Utils.color(plugin.getMainConfig().getString("FILE-RELOAD")));
    }
}
