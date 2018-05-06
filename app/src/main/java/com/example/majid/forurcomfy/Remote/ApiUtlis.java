package com.example.majid.forurcomfy.Remote;

public class ApiUtlis {

    private ApiUtlis() {}

    public static final String BASE_URL = "https://node-practice0208.herokuapp.com";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
    public static RegisterService getRegisterService() {

        return RetrofitClient.getClient(BASE_URL).create(RegisterService.class);
    }
}
