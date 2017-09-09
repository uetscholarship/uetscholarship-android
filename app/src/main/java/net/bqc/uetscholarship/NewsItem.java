package net.bqc.uetscholarship;

/**
 * Created by BQC-PC on 9/9/2017.
 */

public class NewsItem {

    private String title;
    private String body;
    private String link;

    public NewsItem(String title, String body, String link) {
        this.title = title;
        this.body = body;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
