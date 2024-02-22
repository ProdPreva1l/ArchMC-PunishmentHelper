package lol.arch.punishmenthelper.config;

import com.google.common.collect.ImmutableList;
import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.utils.BasicConfig;
import lol.arch.punishmenthelper.utils.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public enum Menus {
    MAIN_GUI_TITLE("main.title", "&8[&4!&8] &c&lPunish"),
    BAN_GUI_TITLE("ban.title", "&8[&4!&8] &c&lPunish &8\u00BB &4Ban"),
    KICK_GUI_TITLE("kick.title", "&8[&4!&8] &c&lPunish &8\u00BB &4Kick"),
    MUTE_GUI_TITLE("mute.title", "&8[&4!&8] &c&lPunish &8\u00BB &4Mute"),
    WARN_GUI_TITLE("warn.title", "&8[&4!&8] &c&lPunish &8\u00BB &4Warn"),
    CONFIRM_GUI_TITLE("confirm.title", "&8[&4!&8] &c&lPunish &8\u00BB &aConfirm?"),
    BAN_BUTTON_NAME("main.ban.name", "&4&lBan"),
    BAN_BUTTON_LORE("main.ban.lore", Collections.singletonList("&7Click to ban the user.")),
    MUTE_BUTTON_NAME("main.mute.name", "&7&lMute"),
    MUTE_BUTTON_LORE("main.mute.lore", Collections.singletonList("&7Click to mute the user.")),
    KICK_BUTTON_NAME("main.kick.name", "&c&lKick"),
    KICK_BUTTON_LORE("main.kick.lore", Collections.singletonList("&7Click to kick the user.")),
    WARN_BUTTON_NAME("main.warn.name", "&a&lWarn"),
    WARN_BUTTON_LORE("main.warn.lore", Collections.singletonList("&7Click to warn the user.")),
    BACK_BUTTON_NAME("all.back.name", "&cGo Back"),
    BACK_BUTTON_LORE("all.back.lore", Collections.singletonList("&7Click to go back")),
    PREVIOUS_BUTTON_NAME("all.back.name", "&cGo Back"),
    PREVIOUS_BUTTON_LORE("all.back.name", Collections.singletonList("&7Click to go back")),
    NEXT_BUTTON_NAME("all.back.lore", "&aNext Page"),
    NEXT_BUTTON_LORE("all.back.lore", Collections.singletonList("&7Click to go back"))
    ;

    @Getter private final String path;
    @Getter private final Object defaultValue;

    public String toString() {
        String str = PunishmentHelper.getMenusFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = PunishmentHelper.getMenusFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = PunishmentHelper.getMenusFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = PunishmentHelper.getMenusFile().getStringList(path);
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
        BasicConfig configFile = PunishmentHelper.getMenusFile();

        for (Menus config : Menus.values()) {
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
