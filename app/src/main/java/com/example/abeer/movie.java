package com.example.abeer;

/**
 * Created by ABeeR on 20-Apr-16.
 */
public class movie {

    private String poster_path;
    private String overview;
    private String original_title;
    private String release_date;
    private Double popularity;
    private Double vote_average;
    private String backdrop_path;
    private int vote_count;
    private int id;
    private String key;
    private String author_review;
    private String url_review;
    private String content_review;

    public String getContent_review() {
        return content_review;
    }

    public void setContent_review(String content_review) {
        this.content_review = content_review;
    }

    public String getAuthor_review() {
        return author_review;
    }

    public void setAuthor_review(String author_review) {
        this.author_review = author_review;
    }

    public String getUrl_review() {
        return url_review;
    }

    public void setUrl_review(String url_review) {
        this.url_review = url_review;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getRelease_date() {

        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getPopularity() {

        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

}
