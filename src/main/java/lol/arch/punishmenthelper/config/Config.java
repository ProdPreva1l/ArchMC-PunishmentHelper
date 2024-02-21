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
    ERROR_PLAYER_NOT_FOUND("errors.player-not-found", "&7{0} &chas never joined before!"),
    ERROR_NO_PERMISSION("errors.no-permission", "&cInsufficient Permission!"),
    ERROR_PLAYER_NONPUNISHABLE("errors.cant-punish", "&cYou cannot punish &7{0}&c!"),
    ERROR_GENERIC("errors.generic", "&cAn error occurred! &7(Contact the Dev Team)");

    @Getter private final String path;
    @Getter private final Object defaultValue;

    public String toString() {
        String str = PunishmentHelper.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = PunishmentHelper.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = PunishmentHelper.i().getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = PunishmentHelper.i().getConfigFile().getStringList(path);
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
        BasicConfig configFile = PunishmentHelper.i().getConfigFile();

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
