package apiTest.starWarsApiTests;

import framework.Logger;
import framework.apiSpecifications.Specification;
import model.starWars.Planet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@Test
public class SWPlanetApiTests {

    private static final String URI_ROOT = "https://swapi.dev/api/";

    @Test
    public void getSWDessertPlanetCountTest(){
        Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest");
        Specification.setupSpecification(Specification.requestSpec(URI_ROOT), Specification.responseSpecCustom(200));

        List<Planet> planets = given().when().get("planets/")
                .then().log().all()
                .extract().jsonPath().getList("results", Planet.class);
        Logger.getInstance().info(String.format("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest.apiConnect: %splanets/", URI_ROOT));
        Logger.getInstance().info("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest.apiConnect.OK: 200");
        List<Planet> desertPlanets = planets.stream().filter(x->x.getTerrain().contains("desert")).toList();

        for (int i =2; i<7; i++)
        {
            planets = given().when().get("planets/?page="+i)
                    .then().log().all()
                    .extract().jsonPath().getList("results", Planet.class);

            Logger.getInstance().info(String.format("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest.apiConnect: %splanets/?page="+i, URI_ROOT));
            Logger.getInstance().info("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest.apiConnect.OK: 200");
            desertPlanets = Stream.concat(desertPlanets.stream(), planets.stream().filter(x->x.getTerrain().contains("desert"))).collect(Collectors.toList());
        }

        Assert.assertEquals(desertPlanets.size(), 12);
        Assert.assertEquals(desertPlanets.get(0).getName(), "Tatooine");
    }

    @Test
    public void getSWPlanetsPageTest()
    {
        Logger.getInstance().logTestName("Test: apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetsPageTest");
        Specification.setupSpecification(Specification.requestSpec(URI_ROOT), Specification.responseSpecCustom(200));

        Integer planetCount  = given().when().get("planets/")
                .then().log().all()
                .extract().jsonPath().get("count");
        String previousPage =  given().when().get("planets/")
                .then().log().all()
                .extract().jsonPath().get("previous");
        String nextPage =  given().when().get("planets/")
                .then().log().all()
                .extract().jsonPath().get("next");
        List<Planet> planets = given().when().get("planets/")
                .then().log().all()
                .extract().jsonPath().getList("results", Planet.class);

        Logger.getInstance().info(String.format("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest.apiConnect: %splanets/", URI_ROOT));
        Logger.getInstance().info("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetCountTest.apiConnect.OK: 200");

        CheckPlanetGetResponse(1, previousPage, nextPage,planets, planetCount);

        for(int i = 2; i<7; i++)
        {
            Logger.getInstance().info("Current Planets page: "+URI_ROOT+"planets/?page="+i);
            planetCount  = given().when().get("planets/?page="+i)
                    .then().log().all()
                    .extract().jsonPath().get("count");

            previousPage =  given().when().get("planets/?page="+i)
                    .then().log().all()
                    .extract().jsonPath().get("previous");

            nextPage =  given().when().get("planets/?page="+i)
                    .then().log().all()
                    .extract().jsonPath().get("next");

            planets = given().when().get("planets/?page="+i)
                    .then().log().all()
                    .extract().jsonPath().getList("results", Planet.class);

            CheckPlanetGetResponse(i, previousPage, nextPage,planets, planetCount);
        }
    }

    private void CheckPlanetGetResponse(Integer pageNumber, String previousPageUri, String nextPageUri,
                                       List<Planet> planets, Integer planetCount)
    {
        Assert.assertEquals(planetCount, 60);
        Assert.assertNotNull(planets);
        Assert.assertEquals(planets.size(), 10);

        if (pageNumber == 1)
        {
            Assert.assertNull(previousPageUri);
            Assert.assertEquals(nextPageUri, URI_ROOT+"planets/?page="+(pageNumber+1));
        }
        else if (pageNumber>1 && pageNumber<6){
            Assert.assertEquals(previousPageUri, URI_ROOT+"planets/?page="+(pageNumber-1));
            Assert.assertEquals(nextPageUri, URI_ROOT+"planets/?page="+(pageNumber+1));
        }
        else if (pageNumber == 6)
        {
            Assert.assertEquals(previousPageUri, URI_ROOT+"planets/?page="+(pageNumber-1));
            Assert.assertNull(nextPageUri);
        }
        else {
            Logger.getInstance().error("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetsPageTest.CheckPlanetGetResponse: Incorrect page number.");
            throw new RuntimeException("apiTest.starWarsApiTests.SWPlanetApiTests.getSWPlanetsPageTest.CheckPlanetGetResponse: Incorrect page number.");
        }
    }
}
