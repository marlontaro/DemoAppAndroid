package pe.edu.upc.catchup.network;

import pe.edu.upc.catchup.models.Article;
import pe.edu.upc.catchup.models.Source;

/**
 * Created by proyecto on 10/04/2017.
 */

public class NewsApi {
    public static String SOURCES_URL = "https://newsapi.org/v1/sources";
    public static String ARTICLES_URL = "https://newsapi.org/v1/articles";
    private Source currentSource;
    private Article currentArticle;

    public Source getCurrentSource() {
        return currentSource;
    }

    public void setCurrentSource(Source currentSource) {
        this.currentSource = currentSource;
    }

    public Article getCurrentArticle() {
        return currentArticle;
    }

    public void setCurrentArticle(Article currentArticle) {
        this.currentArticle = currentArticle;
    }
}
