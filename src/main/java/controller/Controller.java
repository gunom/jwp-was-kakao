package controller;

import webserver.request.Request;
import webserver.response.Response;

public interface Controller {

    Response doGet(Request request);
    Response doPost(Request request);
}
