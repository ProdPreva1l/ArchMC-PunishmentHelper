package lol.arch.punishmenthelper.utils;

public class CommandBuilder {
    private String command = "litebans:{0} {1} {2} {3}";
    public CommandBuilder() {
    }
    public CommandBuilder(PunishmentType type) {
        switch (type) {
            case WARN:
                command = command.replace("{0}","warn");
            case MUTE:
                command = command.replace("{0}","mute {4}");
            case KICK:
                command = command.replace("{0}","kick {4}");
            case BAN:
                command = command.replace("{0}","ban {4}");
        }
    }
    public CommandBuilder type(PunishmentType type) {
        switch (type) {
            case WARN:
                command = command.replace("{0}","warn");
            case MUTE:
                command = command.replace("{0}","mute {4}");
            case KICK:
                command = command.replace("{0}","kick {4}");
            case BAN:
                command = command.replace("{0}","ban {4}");
        }
        return this;
    }

    public CommandBuilder player(String playerName) {
        command = command.replace("{1}", playerName);
        return this;
    }

    public CommandBuilder silent(boolean bool) {
        if (bool) {
            command = command.replace("{2}", "-s");
        } else {
            command = command.replace("{2}", "-p");
        }
        return this;
    }

    public CommandBuilder reason(String reason) {
        command = command.replace("{3}", reason);
        return this;
    }

    public CommandBuilder length(String length) {
        if (length.equalsIgnoreCase("perm")) {
            command = command.replace("{4}", "");
            return this;
        }
        command = command.replace("{4}", length);
        return this;
    }

    public String build() {
        command = command.replace("{4}", "1d");
        command = command.replace("{3}", "No Reason Specified.");
        command = command.replace("{2}", "-s");
        command = command.replace("{1}", "NoPlayer");
        return this.command;
    }
}
