package lol.arch.punishmenthelper.commands;

import lol.arch.punishmenthelper.config.Config;
import lol.arch.punishmenthelper.guis.PunishGUI;
import lol.arch.punishmenthelper.utils.Text;
import lol.arch.punishmenthelper.utils.commands.Command;
import lol.arch.punishmenthelper.utils.commands.CommandArgs;
import lol.arch.punishmenthelper.utils.commands.CommandArguments;
import lol.arch.punishmenthelper.utils.exceptions.GuiButtonException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PunishCommand extends Command {
    @CommandArgs(name = "punish", permission = "punish.use")
    public void execute(CommandArguments command) {
        Player sender = command.getPlayer();
        String toPunish = command.getArgs().length != 0 ? command.getArgs()[0] : null;

        if (toPunish == null) {
            sender.sendMessage(Text.message("&cUsage: &7/punish <player>"));
            return;
        }

        try {
            new PunishGUI(sender, toPunish).open(sender);
        } catch (GuiButtonException e) {
            sender.sendMessage(Config.ERROR_GENERIC.toFormattedString());
            e.printStackTrace();
        }
    }
}
