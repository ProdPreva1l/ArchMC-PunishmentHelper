package lol.arch.punishmenthelper.utils.gui;

import fr.mrmicky.fastinv.ItemBuilder;
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
                        .name(Text.message("&c&l\u2717 Close"))
                        .lore(Text.message("&7Click to close the menu")).build();
            case NEXT_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Text.message("&a&lNext Page"))
                        .lore(Text.message("&7Click to go to the next page")).build();
            case PREVIOUS_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Text.message("&c&lPrevious Page"))
                        .lore(Text.message("&7Click to go to the next page")).build();
            case GENERIC:
                throw new GuiButtonException("Attempted to create a button with type \"GENERIC\" without required params.");
            default:
                throw new GuiButtonException("Invalid Button Type.");
        }
    }
    public ItemStack constructButton(GuiButtonType type, Material material, String name, List<String> lore) throws GuiButtonException {
        switch (type) {
            case CLOSE:
                return new ItemBuilder(Material.BARRIER)
                        .name(Text.message("&c&l\u2717 Close"))
                        .lore(Text.message("&7Click to close the menu")).build();
            case NEXT_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Text.message("&c&lNext Page"))
                        .lore(Text.message("&7Click to go to the next page")).build();
            case PREVIOUS_PAGE:
                return new ItemBuilder(Material.ARROW)
                        .name(Text.message("&c&lPrevious Page"))
                        .lore(Text.message("&7Click to go to the next page")).build();
            case GENERIC:
                return new ItemBuilder(material)
                        .name(name)
                        .lore(lore).build();
            default:
                throw new GuiButtonException("Invalid Button Type.");
        }
    }
}
