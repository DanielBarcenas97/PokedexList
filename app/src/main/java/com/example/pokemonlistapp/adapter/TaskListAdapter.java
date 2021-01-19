package com.example.pokemonlistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonlistapp.R;
import com.example.pokemonlistapp.callback.ActionCallback;
import com.example.pokemonlistapp.database.TaskItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {

    Context context;
    List<TaskItem> data;
    ActionCallback.TaskItemClick callback;

    public TaskListAdapter(Context context, List<TaskItem> data, ActionCallback.TaskItemClick callback) {
        this.context = context;
        this.data = data;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskListViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {

        holder.taskName.setText(data.get(position).name);
        holder.time.setText(String.format(context.getString(R.string.skill), data.get(position).time));
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.clickItem(data.get(position), view);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, context.getString(R.string.toast_id) + data.get(position).tid,Toast.LENGTH_LONG).show();
            }
        });

        holder.taskType.setText(String.format(context.getString(R.string.type), data.get(position).type));


        switch (data.get(position).type){
            case "acero":
            case "steel":
                holder.imageType.setImageResource(R.drawable.acero);
                break;
            case "agua":
            case "water":
                holder.imageType.setImageResource(R.drawable.agua);
                break;
            case "dragon":
                holder.imageType.setImageResource(R.drawable.dragon);
                break;
            case "bicho":
            case "bug":
                holder.imageType.setImageResource(R.drawable.bicho);
                break;
            case "fuego":
            case "fire":
                holder.imageType.setImageResource(R.drawable.fuego);
                break;
            case "fantasma":
            case "ghost":
                holder.imageType.setImageResource(R.drawable.fantasma);
                break;
            case "electrico":
            case "electric":
                holder.imageType.setImageResource(R.drawable.electrico);
                break;
            case "psiquico":
            case "psychic":
                holder.imageType.setImageResource(R.drawable.psiquico);
                break;
            case "planta":
            case "plant":
                holder.imageType.setImageResource(R.drawable.planta);
                break;
            case "roca":
            case "rock":
                holder.imageType.setImageResource(R.drawable.roca);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TaskListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_name)
        TextView taskName;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.task_type)
        TextView taskType;

        @BindView(R.id.iv_more)
        ImageView more;

        @BindView(R.id.image_type)
        ImageView imageType;


        public TaskListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
