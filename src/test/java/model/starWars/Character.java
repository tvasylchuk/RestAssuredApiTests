package model.starWars;

import java.util.ArrayList;

public class Character {
    private final String name;
    private final String height;
    private final String mass;
    private final String hair_color;
    private final String skin_color;
    private final String eye_color;
    private final String birth_year;
    private final String gender;
    private final String homeworld;
    private final ArrayList<String> films;
    private final ArrayList<Object> species;
    private final ArrayList<String> vehicles;
    private final ArrayList<String> starships;
    private final String created;
    private final String edited;
    private final String url;

    public Character(String name, String height, String mass, String hair_color, String skin_color, String eye_color, String birth_year,
                     String gender, String homeworld, ArrayList<String> films, ArrayList<Object> species, ArrayList<String> vehicles,
                     ArrayList<String> starships, String created, String edited, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.eye_color = eye_color;
        this.birth_year = birth_year;
        this.gender = gender;
        this.homeworld = homeworld;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public ArrayList<Object> getSpecies() {
        return species;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public ArrayList<String> getStarships() {
        return starships;
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
