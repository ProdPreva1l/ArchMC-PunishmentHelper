package lol.arch.punishmenthelper.utils.gui;

import fr.mrmicky.fastinv.ItemBuilder;
import lol.arch.punishmenthelper.config.Menus;
import lol.arch.punishmenthelper.utils.Text;
import lol.arch.punishmenthelper.utils.exceptions.GuiButtonException;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;


@UtilityClass
public class GuiHelper {
    public ItemStack constructButton(GuiButtonType type) throws GuiButtonException {
        switch (type) {
            case CLOSE:
                return new ItemBuilder(Material.BARRIER)
                        .name(Menus.CLOSE_BUTTON_NAME.toFormattedString())
                        .lore(Menus.CLOSE_BUTTON_LORE.toLore()).build();
            case NEXT_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Menus.NEXT_BUTTON_NAME.toFormattedString())
                        .lore(Menus.NEXT_BUTTON_LORE.toLore()).build();
            case PREVIOUS_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Menus.PREVIOUS_BUTTON_NAME.toFormattedString())
                        .lore(Menus.PREVIOUS_BUTTON_LORE.toLore()).build();
            case GENERIC:
                throw new GuiButtonException("Attempted to create a button with type \"GENERIC\" without required params.");
            default:
                throw new GuiButtonException("Invalid Button Type.");
        }
    }
    public ItemStack constructButton(GuiButtonType type, Material material, String name, List<String> lore) throws GuiButtonException {
        switch (type) {
            case BACK:
                return new ItemBuilder(Material.FEATHER)
                        .name(Menus.BACK_BUTTON_NAME.toFormattedString())
                        .lore(Menus.BACK_BUTTON_LORE.toLore()).build();
            case CLOSE:
                return new ItemBuilder(Material.BARRIER)
                        .name(Menus.CLOSE_BUTTON_NAME.toFormattedString())
                        .lore(Menus.CLOSE_BUTTON_LORE.toLore()).build();
            case NEXT_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Menus.NEXT_BUTTON_NAME.toFormattedString())
                        .lore(Menus.NEXT_BUTTON_LORE.toLore()).build();
            case PREVIOUS_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Menus.PREVIOUS_BUTTON_NAME.toFormattedString())
                        .lore(Menus.PREVIOUS_BUTTON_LORE.toLore()).build();
            case GENERIC:
                return new ItemBuilder(material)
                        .name(name)
                        .lore(lore).build();
            default:
                throw new GuiButtonException("Invalid Button Type.");
        }
    }
}
