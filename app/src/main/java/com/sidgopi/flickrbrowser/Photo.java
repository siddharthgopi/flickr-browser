package com.sidgopi.flickrbrowser;

/**
 * Created by CouponDunia on 22/06/16.
 */
public class Photo {

    String title;
    String link;
    String author;
    String tags;
    String imageLocation;
    String description;


    public Photo(String title, String link, String author, String tags, String imageLocation, String description) {

        this.title = title;
        this.link = link;
        this.author = author;
        this.description = description;
        this.imageLocation = imageLocation;
        this.tags = tags;

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getTags() {
        return tags;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getDescription() {
        return description;
    }
}
