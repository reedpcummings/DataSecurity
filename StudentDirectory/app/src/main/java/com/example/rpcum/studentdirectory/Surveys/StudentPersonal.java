package com.example.rpcum.studentdirectory.Surveys;

public class StudentPersonal {

    //fields
    private String username;
    private String read;
    private String movies;
    private String hookup;
    private String sports;
    private String workout;
    private String hiking;
    private String religious;
    private String socialMedia;
    private String drink;
    private String smoke;
    private String music;

    //constructors
    public StudentPersonal() {}

    public StudentPersonal(String username, String read, String movies, String hookup, String sports, String workout, String hiking,
                           String religious, String socialMedia, String drink, String smoke, String music) {
        this.username = username;
        this.read = read;
        this.movies = movies;
        this.hookup = hookup;
        this.sports = sports;
        this.workout = workout;
        this.hiking = hiking;
        this.religious = religious;
        this.socialMedia = socialMedia;
        this.drink = drink;
        this.smoke = smoke;
        this.music = music;

    }


    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getHookup() {
        return hookup;
    }

    public void setHookup(String hookup) {
        this.hookup = hookup;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }
    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }


    public String getHiking() {
        return hiking;
    }

    public void setHiking(String hiking) {
        this.hiking = hiking;
    }

    public String getReligious() {
        return religious;
    }

    public void setReligious(String religious) {
        this.religious = religious;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
