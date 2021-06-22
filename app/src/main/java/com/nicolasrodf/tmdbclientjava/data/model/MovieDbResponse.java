package com.nicolasrodf.tmdbclientjava.data.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDbResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> movies = new ArrayList<>();
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Creator<MovieDbResponse> CREATOR = new Creator<MovieDbResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDbResponse createFromParcel(android.os.Parcel in) {
            return new MovieDbResponse(in);
        }

        public MovieDbResponse[] newArray(int size) {
            return (new MovieDbResponse[size]);
        }

    }
            ;

    protected MovieDbResponse(android.os.Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movies, (com.nicolasrodf.tmdbclientjava.data.model.Movie.class.getClassLoader()));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MovieDbResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(movies);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}