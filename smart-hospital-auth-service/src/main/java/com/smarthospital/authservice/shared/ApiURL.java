package com.smarthospital.authservice.shared;



public class ApiURL {
    public final static String BASE_URL = "/api";
    public final static String USER_SIGN_IN = BASE_URL + "/user/signin";
    public final static String USER_SIGN_UP = BASE_URL + "/user/signup";
    public final static String CHANGE_PASSWORD = BASE_URL + "/change_password";
    public final static String FORGET_PASSWORD = BASE_URL + "/forget_password";
    public final static String CODE_GENERATE = BASE_URL + "/code_generate";
    public final static String USER_SAVE_BUG=BASE_URL+"/user/bug/add";
    public final static String ADMIN_SIGN_UP=BASE_URL+"/admin/signup";
    public final static String ADMIN_GET_BUG=BASE_URL+"/admin/bug/get";
}
