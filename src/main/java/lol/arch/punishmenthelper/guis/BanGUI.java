package lol.arch.punishmenthelper.guis;

import fr.mrmicky.fastinv.FastInv;
import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.Sound;
import lol.arch.punishmenthelper.utils.exceptions.GuiButtonException;
import lol.arch.punishmenthelper.utils.gui.PaginatedInv;
import org.bukkit.entity.Player;

import java.util.List;

public class BanGUI extends FastInv implements PaginatedInv {
    private final Player player;
    private final int page;
    public BanGUI(Player sender, String target, int page) {
        super(27, Menus.BAN_GUI_TITLE.toFormattedString());
        this.player = sender;
        this.page = page;

        try {
            getPaginatedItemsMap(this, PunishmentHelper.i().getBansFile()).forEach((index, itemStack) -> setItem(index, itemStack, e -> {
                Sound.click((Player) e.getWhoClicked());

            }));
        } catch (GuiButtonException e) { e.printStackTrace(); }


    }

    @Override
    public int page() {
        return page;
    }

    @Override
    public int maxItemsPerPage() {
        return 18;
    }

    @Override
    public List<String> list() {
        return PunishmentHelper.i().getBansFile().getStringList("reasons");
    }
}
