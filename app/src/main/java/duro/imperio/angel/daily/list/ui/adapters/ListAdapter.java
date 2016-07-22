package duro.imperio.angel.daily.list.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import duro.imperio.angel.daily.R;
import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 18/7/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Page> pages;
    private OnItemClickListener onItemClickListener;

    public ListAdapter(List<Page> pages, OnItemClickListener onItemClickListener) {
        this.pages = pages;
        this.onItemClickListener = onItemClickListener;
    }

    public void setPages(List<Page> pages) {
        this.pages.clear();
        this.pages.addAll(pages);
        notifyDataSetChanged();
    }

    public void removePage(Page page){
        this.pages.remove(page);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Page page = pages.get(pages.size() - position-1);
        holder.setContent(page);
        holder.setOnItemClickListener(onItemClickListener, page);
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.container)
        LinearLayout container;
        @Bind(R.id.txtTitle)
        TextView txtTitle;
        @Bind(R.id.txtDate)
        TextView txtDate;
        @Bind(R.id.txtErase)
        TextView txtErase;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setContent(Page page) {
            txtTitle.setText(page.getTitle());
            txtDate.setText(page.getDate());
            if (!page.getSaved()) {
                txtErase.setVisibility(View.VISIBLE);
            }
        }

        void setOnItemClickListener(final OnItemClickListener onItemClickListener, final Page page) {
            container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(page.getPage_id());
                }
            });

            container.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(page);
                    return true;
                }
            });
        }
    }
}
