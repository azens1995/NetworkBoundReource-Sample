package com.azens1995.offlinefirstarchitecture.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Azens Eklak on 2019-09-12.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class MovieResponse {
    private int page;
    private List<Movie> results;

    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResult;

    public MovieResponse(int page, List<Movie> results, int totalPages, int totalResult) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResult = totalResult;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "page=" + page +
                ", results=" + results +
                ", totalPages=" + totalPages +
                ", totalResult=" + totalResult +
                '}';
    }
}

