package lol.arch.punishmenthelper.commands;

import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.utils.Text;
import lol.arch.punishmenthelper.utils.commands.Command;
import lol.arch.punishmenthelper.utils.commands.CommandArgs;
import lol.arch.punishmenthelper.utils.commands.CommandArguments;
import org.bukkit.entity.Player;

public class ReloadCommand extends Command {
    @CommandArgs(name = "punishmenthelper-reload", aliases = {"ph-reload"}, permission = "punish.reload", inGameOnly = false, async = true)
    public void execute(CommandArguments command) {
        Player sender = command.getPlayer();
        PunishmentHelper.getMenusFile().load();
        PunishmentHelper.getConfigFile().load();
        PunishmentHelper.getBansFile().load();
        PunishmentHelper.getKicksFile().load();
        PunishmentHelper.getWarnsFile().load();
        PunishmentHelper.getMutesFile().load();

        sender.sendMessage(Text.message("&aConfig Reloaded!"));
    }
}
