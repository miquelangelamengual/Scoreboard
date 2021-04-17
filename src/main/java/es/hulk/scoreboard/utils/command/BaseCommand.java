package es.hulk.scoreboard.utils.command;

import es.hulk.scoreboard.Score;

/**
 * The type Base command.
 */
public abstract class BaseCommand {

    /**
     * The Plugin.
     */
    public Score plugin = Score.getInstance();

    /**
     * Instantiates a new Base command.
     */
    public BaseCommand() {
        this.plugin.getCommandFramework().registerCommands(this);
    }

    /**
     * On command.
     *
     * @param cmd the cmd
     */
    public abstract void onCommand(CommandArgs cmd);
}
