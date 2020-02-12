package com.alimasanov.appgeo.view.listActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;

import com.alimasanov.appgeo.adapters.ListAdapter;
import com.alimasanov.appgeo.R;
import com.alimasanov.appgeo.db.Entity;
import com.alimasanov.appgeo.view.mapsActivity.MapsActivity;

import java.util.List;
import java.util.Objects;

public class ListActivity extends AppCompatActivity {

    PresenterListActivity presenter;
    RecyclerView recyclerView;
    ListAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.list);
        intent = new Intent(this, MapsActivity.class);

        presenter = new PresenterListActivity(this);
        presenter.loadData();
    }

    public void showLocation(List<Entity> entities) {
        adapter = new ListAdapter(entities, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, Entity entity) {
                boolean expanded = entity.getExpanded();
                entity.setExpanded(!expanded);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onButtonMapClickListener(int position, Entity entity) {
                intent.putExtra("lat", entity.getLatitude())
                        .putExtra("lon", entity.getLongitude());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) Objects.requireNonNull(recyclerView.getItemAnimator())).setSupportsChangeAnimations(false);
    }
}
