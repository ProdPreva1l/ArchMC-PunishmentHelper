package lol.arch.punishmenthelper.utils.gui;

import fr.mrmicky.fastinv.FastInv;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.BasicConfig;
import lol.arch.punishmenthelper.utils.exceptions.GuiButtonException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public interface PaginatedInv {
    int page();
    int maxItemsPerPage();
    List<String> list();

    default HashMap<Integer, ItemStack> getPaginatedItemsMap(FastInv inv, BasicConfig config) throws GuiButtonException {
        int index = 0;
        if (list() == null || list().isEmpty()) return null;

        HashMap<Integer, ItemStack> ret = new HashMap<>();
        for (int i = 0; i < maxItemsPerPage(); i++) {
            index = maxItemsPerPage() * page() + i;
            if (index >= list().size()) break;
            String reason = list().get(index);
            String itemName = config.getString(reason + ".gui-name");
            List<String> itemLore = config.getStringList(reason + ".gui-lore");
            Material material = Material.valueOf(config.getString(reason + ".gui-item"));
            ret.put(index, GuiHelper.constructButton(GuiButtonType.GENERIC, material, itemName, itemLore));
        }
        return ret;
    }
}
