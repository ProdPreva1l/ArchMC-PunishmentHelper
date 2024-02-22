package lol.arch.punishmenthelper.guis;

import fr.mrmicky.fastinv.FastInv;
import lol.arch.punishmenthelper.PunishmentHelper;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.BasicConfig;
import lol.arch.punishmenthelper.utils.PunishmentType;
import lol.arch.punishmenthelper.utils.Sound;
import lol.arch.punishmenthelper.utils.exceptions.GuiButtonException;
import lol.arch.punishmenthelper.utils.gui.GuiButtonType;
import lol.arch.punishmenthelper.utils.gui.GuiHelper;
import lol.arch.punishmenthelper.utils.gui.PaginatedInv;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReasonGUI extends FastInv implements PaginatedInv {
    private final int page;
    private int index = 0;
    private final String target;
    private final FastInv previousGUI;
    private final PunishmentType punishmentType;

    public ReasonGUI(PunishmentType punishmentType, String target, int page, FastInv previousGUI) {
        super(27, Menus.valueOf(punishmentType + "_GUI_TITLE").toFormattedString());
        this.page = page;
        this.target = target;
        this.previousGUI = previousGUI;
        this.punishmentType = punishmentType;


        BasicConfig config = getConfig();

        try {
            for (int i = 0; i < maxItemsPerPage(); i++) {
                index = maxItemsPerPage() * page() + i;
                if (index >= list().size()) break;
                String reason = list().get(index);
                String itemName = config.getString(reason + ".gui-name");
                List<String> itemLore = config.getStringList(reason + ".gui-lore");
                Material material = Material.valueOf(config.getString(reason + ".gui-item"));
                setItem(index, GuiHelper.constructButton(GuiButtonType.GENERIC, material, itemName, itemLore), e -> {
                    Sound.click((Player) e.getWhoClicked());
                    new FinalisePunishmentGUI(punishmentType, config, reason, target).open((Player) e.getWhoClicked());
                });
            }

            placeNavButtons();
        } catch (GuiButtonException e) { e.printStackTrace(); }
    }

    private void placeNavButtons() throws GuiButtonException {
        // Close button
        setItem(getInventory().getSize() - 5, GuiHelper.constructButton(GuiButtonType.CLOSE), e -> {
            Sound.click((Player) e.getWhoClicked());
            e.getWhoClicked().closeInventory();
        });

        // Back Button
        setItem(getInventory().getSize() - 6, GuiHelper.constructButton(GuiButtonType.GENERIC, Material.FEATHER, Menus.BACK_BUTTON_NAME.toFormattedString(), Menus.BACK_BUTTON_LORE.toLore()),e -> {
            Sound.click((Player) e.getWhoClicked());
            previousGUI.open((Player) e.getWhoClicked());
        });

        // Pagination Buttons

        if (page() > 0) {
            setItem(getInventory().getSize() - 9, GuiHelper.constructButton(GuiButtonType.PREVIOUS_PAGE), e -> {
                new ReasonGUI(punishmentType, target, page - 1, previousGUI).open((Player) e.getWhoClicked());
            });
        }
        if (list().size() >= index + 2) {
            setItem(getInventory().getSize() - 1, GuiHelper.constructButton(GuiButtonType.NEXT_PAGE), e -> {
                Sound.click((Player) e.getWhoClicked());
                new ReasonGUI(punishmentType, target, page + 1, previousGUI).open((Player) e.getWhoClicked());
            });
        }
    }

    private BasicConfig getConfig() {
        switch (punishmentType) {
            case KICK:
                return PunishmentHelper.getKicksFile();
            case MUTE:
                return PunishmentHelper.getMutesFile();
            case WARN:
                return PunishmentHelper.getWarnsFile();
            default:
                return PunishmentHelper.getBansFile();
        }
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
        return new ArrayList<>(getConfig().getConfiguration().getKeys(false));
    }
}
