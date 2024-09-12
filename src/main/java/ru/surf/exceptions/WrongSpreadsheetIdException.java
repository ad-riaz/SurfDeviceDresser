package ru.surf.exceptions;

public class WrongSpreadsheetIdException extends RuntimeException {

    public WrongSpreadsheetIdException() {
        super("Не удалось найти Google таблицу с id, указанным в application.properties. Проверь правильность указанного spreadsheetId и попробуй запустить генерацию заново.");
    }
}
