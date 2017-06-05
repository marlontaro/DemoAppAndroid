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
import pe.edu.upc.catchup.activities.ArticleActivity;
import pe.edu.upc.catchup.models.Article;

/**
 * Created by proyecto on 17/04/2017.
 */

public class ArticlesAdapter extends
        RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> articles;

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_article, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, final int position) {
        holder.pictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.pictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.pictureANImageView.setImageUrl(articles.get(position).getUrlToImage());
        holder.titleTextView.setText(articles.get(position).getTitle());
        holder.articleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentArticle(
                        articles.get(position)
                );
                v.getContext().startActivity(new Intent(
                        v.getContext(), ArticleActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView articleCardView;
        TextView titleTextView;
        ANImageView pictureANImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            pictureANImageView = (ANImageView) itemView.findViewById(R.id.pictureANImageView);
            articleCardView = (CardView) itemView.findViewById(R.id.articleCardView);
        }
    }
}
