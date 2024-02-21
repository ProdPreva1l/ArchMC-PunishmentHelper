package lol.arch.punishmenthelper.commands;

import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.utils.Text;
import lol.arch.punishmenthelper.utils.commands.CommandArgs;
import lol.arch.punishmenthelper.utils.commands.CommandArguments;
import org.bukkit.entity.Player;

public class ReloadCommand {
    @CommandArgs(name = "punishmenthelper-reload", aliases = {"ph-reload"}, permission = "punish.reload", inGameOnly = false, async = true)
    public void execute(CommandArguments command) {
        Player sender = command.getPlayer();
        PunishmentHelper.i().getMenusFile().load();
        PunishmentHelper.i().getConfigFile().load();
        PunishmentHelper.i().getBansFile().load();
        PunishmentHelper.i().getKicksFile().load();
        PunishmentHelper.i().getWarnsFile().load();
        PunishmentHelper.i().getMutesFile().load();

        sender.sendMessage(Text.message("&aConfig Reloaded!"));
    }
}
