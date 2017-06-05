package pe.edu.upc.catchup.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.activities.ArticlesActivity;
import pe.edu.upc.catchup.models.Source;

/**
 * Created by proyecto on 10/04/2017.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    private List<Source> sources;
    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_source,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SourcesAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(sources.get(position).getName());
        holder.descriptionTextView.setText(sources.get(position).getDescription());
        holder.sourceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp
                        .getInstance()
                        .setCurrentSource(sources.get(position));
                v.getContext().startActivity(
                        new Intent(
                            v.getContext(),
                            ArticlesActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView sourceCardView;
        TextView nameTextView;
        TextView descriptionTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            sourceCardView = (CardView) itemView.findViewById(R.id.sourceCardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
