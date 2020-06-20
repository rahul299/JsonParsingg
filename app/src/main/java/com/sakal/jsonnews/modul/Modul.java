package com.sakal.jsonnews.modul;

public class Modul {

    private String Id, Names, Title,Description,Url,UrlToImage,PublishedAt,Content;

    public Modul(String id, String names, String title, String description, String url,
                 String urlToImage, String publishedAt, String content)
    {
        Id = id;
        Names = names;
        this.Title = title;
        this.Description = description;
        this.Url = url;
        this.UrlToImage = urlToImage;
        this.PublishedAt = publishedAt;
        this.Content = content;

    }

    public String getId() {
        return Id;
    }

    public String getNames() {
        return Names;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getUrl() {
        return Url;
    }

    public String getUrlToImage() {
        return UrlToImage;
    }

    public String getPublishedAt() {
        return PublishedAt;
    }

    public String getContent() {
        return Content;
    }
}
