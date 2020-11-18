package ua.kpi.comsys.iv7112;

public class Movie {
    public String title;
    public String year;
    public String type;
    public String released;
    public String runtime;
    public String genre;
    public String director;
    public String actors;
    public String plot;
    public String country;
    public String awards;
    public String poster;
    public String rating;
    public String production;
    public String language;

    public void setMovie (String title, String year, String type, String released, String runtime, String genre, String director, String actors, String plot, String country, String awards, String poster, String rating, String production, String language) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.rating = rating;
        this.production = production;
        this.language = language;
    }

    public String getTitle(){
        return title;
    }

    public String getYear(){
        return year;
    }

    public String getType(){
        return type;
    }

    public String getReleased(){
        return released;
    }

    public String getRuntime(){
        return runtime;
    }

    public String getGenre(){
        return genre;
    }

    public String getDirector(){
        return director;
    }

    public String getActors(){
        return actors;
    }

    public String getPlot(){
        return plot;
    }

    public String getCountry(){
        return country;
    }

    public String getAwards(){
        return awards;
    }

    public String getPoster(){
        return poster;
    }

    public String getRating(){
        return rating;
    }

    public String getProduction(){
        return production;
    }

    public String getLanguage(){
        return language;
    }
}
