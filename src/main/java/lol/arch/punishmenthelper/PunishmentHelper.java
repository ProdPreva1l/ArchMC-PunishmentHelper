package lol.arch.punishmenthelper;

import fr.mrmicky.fastinv.FastInvManager;
import lol.arch.punishmenthelper.commands.PunishCommand;
import lol.arch.punishmenthelper.commands.ReloadCommand;
import lol.arch.punishmenthelper.config.Config;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.BasicConfig;

import lol.arch.punishmenthelper.utils.commands.CommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PunishmentHelper extends JavaPlugin {
    private static PunishmentHelper instance;

    @Getter private static BasicConfig configFile;
    @Getter private static BasicConfig menusFile;
    @Getter private static BasicConfig bansFile;
    @Getter private static BasicConfig kicksFile;
    @Getter private static BasicConfig mutesFile;
    @Getter private static BasicConfig warnsFile;

    @Getter private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        FastInvManager.register(this);

        loadFiles();
        loadExtras();
        loadCommands();

        getLogger().info("Loaded commands:");
        commandManager.getLoadedCommands().forEach(c -> getLogger().info(c.getAssigned().name()));
    }

    @Override
    public void onDisable() {
        FastInvManager.closeAll();
    }

    private void loadFiles() {
        this.configFile = new BasicConfig(this, "config.yml");
        this.menusFile = new BasicConfig(this, "menus.yml");
        this.bansFile = new BasicConfig(this, "bans.yml");
        this.kicksFile = new BasicConfig(this, "kicks.yml");
        this.mutesFile = new BasicConfig(this, "mutes.yml");
        this.warnsFile = new BasicConfig(this, "warns.yml");

        Config.loadDefault();
        Menus.loadDefault();
    }

    private void loadExtras() {
        this.commandManager = new CommandManager(this);
    }

    private void loadCommands() {
        new PunishCommand();
        new ReloadCommand();
    }


    public static PunishmentHelper i() {
        return instance;
    }
}
