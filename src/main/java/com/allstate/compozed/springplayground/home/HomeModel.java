package com.allstate.compozed.springplayground.home;

/**
 * Created by localadmin on 7/17/17.
 */
final class HomeModel {

    private final String message;

    HomeModel(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
