package duro.imperio.angel.daily.list.ui.adapters;

import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 18/7/2016.
 */
public interface OnItemClickListener {
    void onClick(String pageId);
    void onLongClick(Page page);
}
