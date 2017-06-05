package pe.edu.upc.catchup.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by proyecto on 17/04/2017.
 */

public class Article {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private Source source;

    public String getAuthor() {
        return author;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Article setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public Article setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Article setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public Article setSource(Source source) {
        this.source = source;
        return this;
    }

    public static Article build(JSONObject jsonArticle, Source source) {
        if(jsonArticle == null) return null;
        if(source == null) return null;
        Article article = new Article();
        try {
            article.setAuthor(jsonArticle.getString("author"))
                    .setTitle(jsonArticle.getString("title"))
                    .setDescription(jsonArticle.getString("description"))
                    .setUrl(jsonArticle.getString("url"))
                    .setUrlToImage(jsonArticle.getString("urlToImage"))
                    .setPublishedAt(jsonArticle.getString("publishedAt"))
                    .setSource(source);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return article;
    }

    public static List<Article> build(JSONArray jsonArticles, Source source) {
        if(jsonArticles == null) return null;
        if(source == null) return null;
        int length = jsonArticles.length();
        List<Article> articles = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                articles.add(Article.build(jsonArticles.getJSONObject(i), source));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }
}
