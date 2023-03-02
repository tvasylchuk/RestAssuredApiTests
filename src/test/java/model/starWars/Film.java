package model.starWars;

import java.util.ArrayList;

public class Film {
    private final String title;
    private final Integer episode_id;
    private final String opening_crawl;
    private final String director;
    private final String producer;
    private final String release_date;
    private final ArrayList<String> characters;
    private final ArrayList<String> planets;
    private final ArrayList<String> starships;
    private final ArrayList<String> vehicles;
    private final ArrayList<String> species;
    private final String created;
    private final String edited;
    private final String url;

    public Film(String title, Integer episode_id, String opening_crawl, String director, String producer, String release_date,
                ArrayList<String> characters, ArrayList<String> planets, ArrayList<String> starships, ArrayList<String> vehicles,
                ArrayList<String> species, String created, String edited, String url) {
        this.title = title;
        this.episode_id = episode_id;
        this.opening_crawl = opening_crawl;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.vehicles = vehicles;
        this.species = species;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public ArrayList<String> getPlanets() {
        return planets;
    }

    public ArrayList<String> getStarships() {
        return starships;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }
}
