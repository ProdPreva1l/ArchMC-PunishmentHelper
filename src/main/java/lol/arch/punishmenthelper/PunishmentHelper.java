package lol.arch.punishmenthelper;

import fr.mrmicky.fastinv.FastInvManager;
import info.preva1l.CacheHandler;
import info.preva1l.CollectionHelper;
import info.preva1l.SimpleMongoHelper;
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

    @Getter private SimpleMongoHelper simpleMongoHelper;
    @Getter private CacheHandler cacheHandler;
    @Getter private CollectionHelper collectionHelper;
    @Getter private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        FastInvManager.register(this);

        loadFiles();
        loadExtras();
        loadCommands();
        loadDatabase();

        getLogger().info("Loaded commands:");
        commandManager.getLoadedCommands().forEach(c -> getLogger().info(c.getAssigned().name()));
    }

    @Override
    public void onDisable() {
        FastInvManager.closeAll();
    }

    private void loadFiles() {
        configFile = new BasicConfig(this, "config.yml");
        menusFile = new BasicConfig(this, "menus.yml");
        bansFile = new BasicConfig(this, "bans.yml");
        kicksFile = new BasicConfig(this, "kicks.yml");
        mutesFile = new BasicConfig(this, "mutes.yml");
        warnsFile = new BasicConfig(this, "warns.yml");

        Config.loadDefault();
        Menus.loadDefault();
    }

    private void loadExtras() {
        this.commandManager = new CommandManager(this);
    }

    private void loadDatabase() {
        this.simpleMongoHelper = new SimpleMongoHelper(
                Config.MONGO_HOST.toString(),
                Config.MONGO_PORT.toInteger(),
                Config.MONGO_USERNAME.toString(),
                Config.MONGO_PASSWORD.toString(),
                Config.MONGO_DB.toString(),
                "admin");
        this.cacheHandler = new CacheHandler(simpleMongoHelper);
        this.collectionHelper = new CollectionHelper(simpleMongoHelper, cacheHandler);
        if (collectionHelper.getCollection("punishment_handler") == null) {
            collectionHelper.createCollection("punishment_handler");
        }
    }

    private void loadCommands() {
        new PunishCommand();
        new ReloadCommand();
    }


    public static PunishmentHelper i() {
        return instance;
    }
}
