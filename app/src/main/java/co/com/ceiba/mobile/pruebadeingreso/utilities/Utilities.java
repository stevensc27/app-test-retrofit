package co.com.ceiba.mobile.pruebadeingreso.utilities;

public class Utilities {
    public static final String DB = "androidDB";
    public static final String USERS = "users";
    public static final String USERS_ID = "id";
    public static final String USERS_NAME = "name";
    public static final String USERS_USERNAME = "user_name";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_STREET = "street";
    public static final String USERS_SUITE = "suite";
    public static final String USERS_CITY = "city";
    public static final String USERS_ZIPCODE = "zipcode";
    public static final String USERS_GEO_LAT = "geo_lat";
    public static final String USERS_GEO_LNG = "geo_lng";
    public static final String USERS_PHONE = "phone";
    public static final String USERS_WEBSITE = "website";
    public static final String USERS_COMPANY = "company";
    public static final String USERS_CATCH_PHRASE = "catch_phrase";
    public static final String USERS_BS = "bs";
    public static final String CREATE_USERS = "CREATE TABLE "+USERS+" ("+USERS_ID+ " INTEGER NOT NULL PRIMARY KEY, " +
            USERS_NAME+ " TEXT, " +
            USERS_USERNAME+ " TEXT, " +
            USERS_EMAIL+ " TEXT, " +
            USERS_SUITE+ " TEXT, " +
            USERS_STREET+ " TEXT, " +
            USERS_CITY+ " TEXT, " +
            USERS_ZIPCODE+ " TEXT, " +
            USERS_GEO_LAT+ " REAL, " +
            USERS_GEO_LNG+ " REAL, " +
            USERS_PHONE+ " TEXT, " +
            USERS_WEBSITE+ " TEXT, " +
            USERS_COMPANY+ " TEXT, " +
            USERS_CATCH_PHRASE+ " TEXT, " +
            USERS_BS+ " TEXT)";

    /*public static final String POSTS = "posts";
    public static final String POSTS_ID = "id";
    public static final String POSTS_USER_ID = "user_id";
    public static final String POSTS_TITLE = "title";
    public static final String POSTS_BODY = "body";
    public static final String CREATE_POSTS = "CREATE TABLE "+POSTS+" ("+POSTS_ID+ " INTEGER NOT NULL PRIMARY KEY, " +
            POSTS_USER_ID+ " INTEGER, " +
            POSTS_TITLE+ " TEXT, " +
            POSTS_BODY+ " TEXT, " +
            " FOREIGN KEY ("+POSTS_USER_ID+") REFERENCES "+USERS+"("+USERS_ID+"))";*/
}
