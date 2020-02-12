package com.alimasanov.appgeo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alimasanov.appgeo.R;
import com.alimasanov.appgeo.db.Entity;

import java.util.Collections;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Entity> list;
    private OnItemClickListener listener;

    public ListAdapter(List<Entity> list, OnItemClickListener listener) {
        this.list = list;
        Collections.reverse(this.list);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Entity entity = list.get(position);

        if (entity.getExpanded() == null) {
            entity.setExpanded(false);
        }

        boolean expanded = entity.getExpanded();
        holder.subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
        if(!expanded) holder.indicator.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            else holder.indicator.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
        holder.bind(entity, listener, position);

        holder.tvBirthday.setText(entity.getDate());
        holder.tvFio.setText(entity.getFio());
        holder.tvCity.setText(entity.getCity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFio;
        TextView tvBirthday;
        TextView tvCity;
        ImageView indicator;
        Button buttonMap;

        View subItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFio = itemView.findViewById(R.id.tv_fio);
            tvBirthday = itemView.findViewById(R.id.tv_birthday);
            tvCity = itemView.findViewById(R.id.tv_city);
            indicator = itemView.findViewById(R.id.indicator);
            buttonMap = itemView.findViewById(R.id.button_get_map);

            subItem = itemView.findViewById(R.id.sub_list);
        }

        void bind(final Entity entity, final OnItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(position, entity);
                }
            });
            buttonMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonMapClickListener(position, entity);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position, Entity entity);
        void onButtonMapClickListener(int position, Entity entity);
    }
}
