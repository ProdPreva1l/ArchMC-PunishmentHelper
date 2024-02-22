package lol.arch.punishmenthelper.utils.gui;

import java.util.List;

public interface PaginatedInv {
    int page();
    int maxItemsPerPage();
    List<String> list();
}
