package co.com.test.mobile.retrofit.models;

public class Addresses {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geos geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geos getGeo() {
        return geo;
    }

    public void setGeo(Geos geo) {
        this.geo = geo;
    }
}
