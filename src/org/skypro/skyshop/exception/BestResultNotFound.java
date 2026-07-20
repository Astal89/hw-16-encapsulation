package org.skypro.skyshop.exception;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchString) {
        super("Элемента удовлетворяющего строке поиска '" + searchString + "' не найдено.");
    }
 }
