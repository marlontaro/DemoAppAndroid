package pe.edu.upc.catchup.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.models.Article;

public class ArticleActivity extends AppCompatActivity {
    ANImageView pictureANImageView;
    TextView    titleTextView;
    TextView    descriptionTextView;
    Article     article;
    ImageButton browserImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        article = CatchUpApp.getInstance().getCurrentArticle();
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        pictureANImageView = (ANImageView) findViewById(R.id.pictureANImageView);
        pictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        pictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        pictureANImageView.setImageUrl(article.getUrlToImage());
        titleTextView.setText(article.getTitle());
        descriptionTextView.setText(article.getDescription());
        browserImageButton = (ImageButton) findViewById(R.id.browserImageButton);
        browserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browerIntent = new Intent(Intent.ACTION_VIEW);
                browerIntent.setData(Uri.parse(article.getUrl()));
                startActivity(browerIntent);
            }
        });

    }

}
