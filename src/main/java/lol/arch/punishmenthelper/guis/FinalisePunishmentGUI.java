package lol.arch.punishmenthelper.guis;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.config.Config;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.database.OffenseHandler;
import lol.arch.punishmenthelper.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class FinalisePunishmentGUI extends FastInv {
    public FinalisePunishmentGUI(PunishmentType punishmentType, BasicConfig config, String reasonRaw, String target) {
        super(27, Menus.CONFIRM_GUI_TITLE.toFormattedString());


        UUID targetUUID = Bukkit.getOfflinePlayer(target).getUniqueId();
        CompletableFuture<Integer> future = OffenseHandler.getPlayerPunishment(targetUUID, reasonRaw);
        int offense = future.join();

        boolean addNewPunishment;
        if (config.getString(reasonRaw + ".offence-" + offense + ".reason").equalsIgnoreCase(reasonRaw + ".offence-" + offense + ".reason")) {
            offense -= 1;
            addNewPunishment = false;
        } else {
            addNewPunishment = true;
        }

        String reason = config.getString(reasonRaw + ".offence-" + offense + ".reason");
        String length = config.getString(reasonRaw + ".offence-" + offense + ".length");
        boolean silent = config.getBoolean(reasonRaw + ".offence-" + offense + ".silent");


        setItems(new int[]{0, 1, 2, 9, 10, 11, 18, 19, 20},
                new ItemBuilder(Material.REDSTONE_BLOCK)
                        .name(Menus.CANCEL_BUTTON_NAME.toFormattedString())
                        .lore(Menus.CANCEL_BUTTON_LORE.toLore()).build(), e -> {
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(Config.PUNISH_CANCEL.toFormattedString());
                    Sound.fail(((Player) e.getWhoClicked()));
                });

        setItems(new int[]{3, 4, 5, 12, 13, 14, 21, 22, 23},
                new ItemBuilder(Material.THIN_GLASS)
                        .name(" ")
                        .lore(Text.message("&8mc.arch.lol")).build());

        setItems(new int[]{6, 7, 8, 15, 16, 17, 24, 25, 26},
                new ItemBuilder(Material.EMERALD_BLOCK)
                        .name(Menus.CONFIRM_BUTTON_NAME.toFormattedString())
                        .lore(Menus.CONFIRM_BUTTON_LORE.toLore()).build(), e -> {
                    e.getWhoClicked().sendMessage(Config.PUNISHMENTS_LOADING.toFormattedString());
                });

            setItems(new int[]{6, 7, 8, 15, 16, 17, 24, 25, 26},
                    new ItemBuilder(Material.EMERALD_BLOCK)
                            .name(Menus.CONFIRM_BUTTON_NAME.toFormattedString())
                            .lore(Menus.CONFIRM_BUTTON_LORE.toLore()).build(), e -> {
                        e.getWhoClicked().closeInventory();
                        ((Player) e.getWhoClicked()).performCommand(
                                new CommandBuilder()
                                        .type(punishmentType)
                                        .player(target)
                                        .reason(reason)
                                        .length(length)
                                        .silent(silent)
                                        .build());
                        TaskManager.Async.run(PunishmentHelper.i(), () -> {
                            if (addNewPunishment) OffenseHandler.addPlayerPunishment(targetUUID, reasonRaw);
                        });
                        Sound.levelUp(((Player) e.getWhoClicked()));
                    });
    }
}
