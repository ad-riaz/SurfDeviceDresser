package ru.surf.exceptions;

public class WrongCredentialsJsonPathException extends RuntimeException {

    public WrongCredentialsJsonPathException(String credentialsJsonPath) {
        super("Не удалось прочитать файл с кредами. Проверь правильность указанного пути к файлу в файле application.properties: " + credentialsJsonPath);
    }
}
