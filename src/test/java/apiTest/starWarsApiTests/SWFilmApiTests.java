package apiTest.starWarsApiTests;

import framework.Logger;
import framework.apiSpecifications.Specification;
import model.starWars.Character;
import model.starWars.Film;
import model.starWars.Planet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;

@Test
public class SWFilmApiTests {

    private static final String URI_ROOT = "https://swapi.dev/api/";

    @Test
    public void getSWFilmsCountTest()
    {
        try
        {
            Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWFilmApiTests.getSWFilmsCountTest");
            Specification.setupSpecification(Specification.requestSpec(URI_ROOT), Specification.responseSpecCustom(200));
            Integer count = given().when().get("films/")
                    .then().log().all()
                    .extract().body().jsonPath().get("count");

            Logger.getInstance().info("apiTest.starWarsApiTests.getCountSWFilmsTest.apiResponse: 200");
            Logger.getInstance().info("apiTest.starWarsApiTests.getCountSWFilmsTest.apiResponse.count: "+count);

            List<Film> films = given().when().get("films/")
                    .then().log().all()
                    .extract().body().jsonPath().getList("results", Film.class);

            Logger.getInstance().info("apiTest.starWarsApiTests.getCountSWFilmsTest.apiResponse: 200");
            Logger.getInstance().info("apiTest.starWarsApiTests.getCountSWFilmsTest.apiResponse.films.size: "+films.size());

            Assert.assertEquals(count, films.size());
        }
        catch(Exception exception)
        {
            Logger.getInstance().error(exception.getMessage());
            Logger.getInstance().error(Arrays.toString(exception.getStackTrace()));
        }
    }

    @Test
    public void getSWFilmsSortingOrderTest()
    {
        try
        {
            Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWFilmApiTests.getSWFilmsSortingOrderTest");
            Specification.setupSpecification(Specification.requestSpec(URI_ROOT), Specification.responseSpecCustom(200));
            List<Film> films = given().when().get("films/")
                    .then().log().all()
                    .extract().body().jsonPath().getList("results", Film.class);

            Logger.getInstance().info("apiTest.starWarsApiTests.getCountSWFilmsTest.apiResponse: 200");

            List<Film> filmSortedByReleaseDate =  films.stream().sorted(Comparator.comparing(Film::getRelease_date))
                    .toList();

            for(int i=0; i<films.size(); i++)
            {
                Assert.assertEquals(films.get(i), filmSortedByReleaseDate.get(i));
            }

        }
        catch(Exception exception)
        {
            Logger.getInstance().error(exception.getMessage());
            Logger.getInstance().error(Arrays.toString(exception.getStackTrace()));
        }
    }

    @Test (dataProvider = "episode_id")
    public void getSWFilmsUriTest(Integer episodeId)
    {
        try
        {
            Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWFilmApiTests.getSWFilmsPlanetUriTest");
            List<Film> films = given().when().get(URI_ROOT+"films/")
                    .then().statusCode(200).log().all()
                    .extract().body().jsonPath().getList("results", Film.class);

            Logger.getInstance().info("apiTest.starWarsApiTests.getCountSWFilmsTest.apiResponse: 200");

            List<Film> filmFilteredByEpisode =  films.stream().filter(x->x.getEpisode_id().equals(episodeId))
                    .toList();

            Assert.assertEquals(filmFilteredByEpisode.size(), 1);
            Logger.getInstance().info("Film Api: "+filmFilteredByEpisode.get(0).getUrl());
            Film filmByUri = given().when().get(filmFilteredByEpisode.get(0).getUrl())
                    .then().statusCode(200).log().all()
                    .extract().body().as(Film.class);

            Assert.assertEquals(filmFilteredByEpisode.get(0).getEpisode_id(), filmByUri.getEpisode_id());
        }
        catch(Exception exception)
        {
            Logger.getInstance().error(exception.getMessage());
            Logger.getInstance().error(Arrays.toString(exception.getStackTrace()));
        }
    }

    public void getSWFilmCharacterUriTest()
    {
        Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWFilmApiTests.getSWFilmCharacterUriTest");
        Film film = given().when().get(URI_ROOT+"films/1/")
                .then().statusCode(200).log().all()
                .extract().body().as(Film.class);

        for (String characterUri: film.getCharacters()) {
            Logger.getInstance().info("Character Api: "+characterUri);
            Character character = given().when().get(characterUri)
                    .then().statusCode(200).log().all()
                    .extract().body().as(Character.class);
            Assert.assertTrue(character.getFilms().stream().anyMatch(x->x.equals(URI_ROOT+"films/1/")));
        }
    }

    public void getSWFilmPlanetUriTest()
    {
        Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWFilmApiTests.getSWFilmCharacterUriTest");
        Film film = given().when().get(URI_ROOT+"films/1/")
                .then().statusCode(200).log().all()
                .extract().body().as(Film.class);

        for (String planetUri: film.getPlanets()) {
            Planet planet = given().when().get(planetUri)
                    .then().statusCode(200).log().all()
                    .extract().body().as(Planet.class);
            Assert.assertTrue(planet.getFilms().stream().anyMatch(x->x.equals(URI_ROOT+"films/1/")));
        }
    }

    @DataProvider(name="episode_id")
    public Object[][] userUpdate()
    {
        return new Object[][] {
                new Object[] {1},
                new Object[]{2},
                new Object[] {3},
                new Object[] {4},
                new Object[] {5},
                new Object[] {6},
        };
    }
}
