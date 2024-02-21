package lol.arch.punishmenthelper.guis;

import fr.mrmicky.fastinv.FastInv;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.Sound;
import lol.arch.punishmenthelper.utils.exceptions.GuiButtonException;
import lol.arch.punishmenthelper.utils.gui.GuiButtonType;
import lol.arch.punishmenthelper.utils.gui.GuiHelper;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class PunishGUI extends FastInv {
    private final Player sender;
    private final String target;
    public PunishGUI(Player sender, String target) throws GuiButtonException {
        super(InventoryType.HOPPER, Menus.MAIN_GUI_TITLE.toFormattedString());

        this.sender = sender;
        this.target = target;

        placeButtons();
    }

    private void placeButtons() throws GuiButtonException {
        setItem(0, GuiHelper.constructButton(GuiButtonType.CLOSE), e -> e.getWhoClicked().closeInventory());

        setItem(1, GuiHelper.constructButton(GuiButtonType.GENERIC,
                Material.EXPLOSIVE_MINECART,
                Menus.BAN_BUTTON_NAME.toFormattedString(),
                Menus.BAN_BUTTON_LORE.toLore()), e -> {
            new BanGUI(sender, target, 0).open(sender);
            Sound.click(sender);
        });

        setItem(2, GuiHelper.constructButton(GuiButtonType.GENERIC,
                Material.REDSTONE_BLOCK,
                Menus.KICK_BUTTON_NAME.toFormattedString(),
                Menus.KICK_BUTTON_LORE.toLore()));

        setItem(3, GuiHelper.constructButton(GuiButtonType.GENERIC,
                Material.SIGN,
                Menus.MUTE_BUTTON_NAME.toFormattedString(),
                Menus.MUTE_BUTTON_LORE.toLore()));

        setItem(4, GuiHelper.constructButton(GuiButtonType.GENERIC,
                Material.THIN_GLASS,
                Menus.WARN_BUTTON_NAME.toFormattedString(),
                Menus.WARN_BUTTON_LORE.toLore()));
    }

}
