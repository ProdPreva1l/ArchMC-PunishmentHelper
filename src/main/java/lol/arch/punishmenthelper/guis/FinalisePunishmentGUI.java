package lol.arch.punishmenthelper.guis;

import fr.mrmicky.fastinv.FastInv;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.PunishmentType;

public class FinalisePunishmentGUI extends FastInv {
    private PunishmentType punishmentType;
    public FinalisePunishmentGUI(PunishmentType punishmentType, String target, FastInv previousGUI) {
        super(27, Menus.CONFIRM_GUI_TITLE.toFormattedString());
        this.punishmentType = punishmentType;


    }
}
