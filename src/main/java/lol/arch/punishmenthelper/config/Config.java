package lol.arch.punishmenthelper.config;

import com.google.common.collect.ImmutableList;
import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.utils.BasicConfig;
import lol.arch.punishmenthelper.utils.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public enum Config {
    MONGO_HOST("mongo.host", "127.0.0.1"),
    MONGO_PORT("mongo.port", "27017"),
    MONGO_USERNAME("mongo.username", "username"),
    MONGO_PASSWORD("mongo.password", "password"),
    MONGO_DB("mongo.database", "database"),
    MONGO_AUTH_DB("mongo.auth-database", "authDatabase"),
    ERROR_NO_PERMISSION("errors.no-permission", "&cInsufficient Permission!"),
    ERROR_GENERIC("errors.generic", "&cAn error occurred! &7(Contact the Dev Team)"),
    PUNISH_SUCCESS("messages.successful-punishment", ""),
    PUNISH_CANCEL("messages.punishment-cancelled", "&cPunishment Cancelled"),
    PUNISHMENTS_LOADING("messages.checking-punishments", "&cPlease wait checking previous punishments...");

    @Getter private final String path;
    @Getter private final Object defaultValue;

    public String toString() {
        String str = PunishmentHelper.getConfigFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = PunishmentHelper.getConfigFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = PunishmentHelper.getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = PunishmentHelper.getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return Text.lore((List<String>) defaultValue);
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return Text.lore(str);
    }

    public boolean toBoolean() {
        return Boolean.parseBoolean(toString());
    }

    public int toInteger() {
        return Integer.parseInt(toString());
    }

    public double toDouble() {
        return Double.parseDouble(toString());
    }

    public static void loadDefault() {
        BasicConfig configFile = PunishmentHelper.getConfigFile();

        for (Config config : Config.values()) {
            String path = config.getPath();
            String str = configFile.getString(path);
            if (str.equals(path)) {
                configFile.getConfiguration().set(path, config.getDefaultValue());
            }
        }

        configFile.save();
        configFile.load();
    }

}
