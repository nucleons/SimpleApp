package com.example.aditya.simpleapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aditya.simpleapp.R;
import com.example.aditya.simpleapp.helper.ImageLoader;
import com.example.aditya.simpleapp.model.Content;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aditya on 13/9/16.
 *
 * Item list adapter
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder>{

    private List<Content> contents;
    private ContentItemListener listener;

    public ListingAdapter(List<Content> contents,ContentItemListener contentItemListener){
        this.contents = contents;
        this.listener = contentItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_item, null);
        ListingItemHolder holder = new ListingItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListingItemHolder listingHolder = (ListingItemHolder) holder;
        if(listingHolder!=null){
            listingHolder.setContent(getItem(position));
        }
    }

    @Override
    public int getItemCount() {
        if(contents!=null){
            return contents.size();
        }
        return 0;
    }

    public Content getItem(int position){
        if(contents!=null && position<contents.size()){
            return contents.get(position);
        }
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class ListingItemHolder extends ViewHolder{

        @Bind(R.id.li_im_image)
        ImageView vImage;

        @Bind(R.id.li_tv_title)
        TextView vTitle;

        @Bind(R.id.li_tv_description)
        TextView vDescription;

        public ListingItemHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(getItem(getAdapterPosition()));
                    }
                }
            });
        }

        public void setContent(Content item){
            if(item!=null){
                if(item.getImageUrl()!=null && !item.getImageUrl().isEmpty()){
                    ImageLoader.getInstance().loadImage(item.getImageUrl(),vImage);
                }
                if(item.getDescription()!=null){
                    vDescription.setText(item.getDescription());
                }
                if(item.getTitle()!=null){
                    vTitle.setText(item.getTitle());
                }
            }
        }
    }

    public interface ContentItemListener{
        void onItemClick(Content content);
    }
}
