package pe.edu.upc.catchup;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import pe.edu.upc.catchup.models.Article;
import pe.edu.upc.catchup.models.Source;
import pe.edu.upc.catchup.network.NewsApi;

/**
 * Created by proyecto on 10/04/2017.
 */

public class CatchUpApp extends Application {
    // Singleton Pattern Implementation
    private static CatchUpApp instance;
    NewsApi newsApi = new NewsApi();

    public CatchUpApp() {
        super();
        instance = this;
    }

    public static CatchUpApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

    // Delegate Pattern Implementation

    public void setCurrentSource(Source source) {
        newsApi.setCurrentSource(source);
    }

    public Source getCurrentSource() {
        return newsApi.getCurrentSource();
    }

    public void setCurrentArticle(Article article) {
        newsApi.setCurrentArticle(article);
    }

    public Article getCurrentArticle() {
        return newsApi.getCurrentArticle();
    }

}
