package pos_microservice_api_gateway.util;

public final class Constants {


    public static final String AUTH = "/auth";
    public static final String SIGNUP = "/sign-up";
    public static final String LOGIN = "/login";

    public static final String API = "/api";

    public static final String USER = "/user";
    public static final String GET_USER_BY_TOKEN = "/user/token";
    public static final String GET_ALL_USERS = "/user";
    public static final String GET_USER_BY_USERNAME = "/user/search/{username}";
    public static final String USERNAME_GET_ROLE = "/user/role/{role}";
    public static final String GET_USER_BY_ID = "/user/{id}";

    public static final String GATEWAY = "/gateway";

    //API INMUEBLES
    public static final String INMUEBLE = "/inmueble";
    public static final String INMUEBLES = "/inmuebles";
    public static final String GET_INMUEBLE_BY_ID = "/inmueble/{inmuebleId}";

    //API COMPRAS
    public static final String COMPRA = "/compra";
    public static final String FIND_ALL_COMPRAS = "/compras";
    public static final String GET_COMPRA_BY_ID = "/compra/{compraId}";

}
