package model.starWars;

import java.util.List;

public class Planet {
    private final String name;
    private final String rotation_period;
    private final String orbital_period;
    private final String diameter;
    private final String climate;
    private final String gravity;
    private final String terrain;
    private final String surface_water;
    private final String population;
    private final List<String> residents;
    private final List<String> films;
    private final String created;
    private final String edited;
    private final String url;

    public Planet(String name, String rotation_period, String orbital_period, String diameter, String climate,
                  String gravity, String terrain, String surface_water, String population, String created, String edited, String url,
                  List<String> residents, List<String> films) {
        this.name = name;
        this.rotation_period = rotation_period;
        this.orbital_period = orbital_period;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.surface_water = surface_water;
        this.population = population;
        this.residents = residents;
        this.films = films;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public void addResidents(String resident) {
        this.residents.add(resident);
    }

    public void addFilm(String film) {
        this.films.add(film);
    }

    public String getName() {
        return name;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public String getPopulation() {
        return population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public List<String> getFilms() {
        return films;
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
