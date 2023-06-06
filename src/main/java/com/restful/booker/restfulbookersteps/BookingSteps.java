package com.restful.booker.restfulbookersteps;

import com.restful.booker.constants.Endpoints;
import com.restful.booker.model.BookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * Created By Kashyap patel
 */
public class BookingSteps {

    @Step("Creating token for booking")
    public ValidatableResponse createToken(String username, String password) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(username, password);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(bookingPojo)
                .post(Endpoints.CREATE_TOKEN)
                .then().log().all().statusCode(200);
    }

    @Step("Creating booking with firstname : {0}, lastname : {1}, totalprice : {2}, depositpaid : {3}, additionalneeds : {4}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo1(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(bookingPojo)
                .post(Endpoints.ALL_BOOKING)
                .then().log().all().statusCode(200);
    }

    @Step("Getting the booking information with firstName : {0}")
    public ValidatableResponse getBookingByFirstName(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .header("Connection", "keep-alive")
                .get(Endpoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(200);

    }

    @Step("Updating booking information with id : {0} ")
    public ValidatableResponse updatBooking(int id) {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Abcd");
        bookingPojo.setTotalprice(123);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .header("Cookie", "token=abc123")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", id)
                .when()
                .body(bookingPojo)
                .patch(Endpoints.UPDATE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(200);
    }

    @Step("Deleting booking information with bookingId : {0}")
    public ValidatableResponse deleteBooking(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .header("Cookie", "token=abc123")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", id)
                .when()
                .delete(Endpoints.DELETE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(201);
    }
    @Step("Getting booking information with bookingId : {0}")
    public ValidatableResponse getStudentById(int id){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .header("Cookie", "token=abc123")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", id)
                .when()
                .get(Endpoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(404);

    }
}
