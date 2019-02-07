package world.models;

/**
 * Represents a Country
 */
public class Country {
    /**
     * Country Code
     */
    public String Code;

    /**
     * Country Name
     */
    public String Name;

    /**
     * Continent of country
     */
    public String Continent;

    /**
     * Region of a country
     */
    public String Region;

    /**
     * Surface area of country
     */
    public float SurfaceArea;

    /**
     *
     */
    public int IndepYear;
    public int Population;
    public float LifeExpectancy;
    public float GNP;
    public float GNPOld;
    public String LocalName;
    public String GovernmentForm;
    public String HeadOfSate;
    public String Capital;
    public String Code2;

    private enum Continent {
        Africa,
        Antarctica,
        Asia,
        Europe,
        NorthAmerica,
        Oceania,
        SouthAmerica
    }
}