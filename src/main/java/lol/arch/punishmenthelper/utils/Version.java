package lol.arch.punishmenthelper.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class Version {
    public boolean isBefore(String version) {
        String serverVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        int serverMinor = Integer.parseInt(serverVersion.replace("1_", "").replaceAll("_R\\d", "").replace("v", ""));
        int targetMinor = Integer.parseInt(version.replace("1.", ""));
        return serverMinor < targetMinor;
    }
}
