package com.coherentsolutions.training.aqa.java.web.nikonova.parser;

import com.coherentsolutions.training.aqa.java.web.nikonova.shop.Cart;

import java.io.File;

public interface Parser {

    void writeToFile(Cart cart);
    Cart readFromFile(File file);
}
