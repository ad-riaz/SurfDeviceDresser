package ru.surf.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import ru.surf.exceptions.WrongCredentialsJsonPathException;
import ru.surf.exceptions.WrongSpreadsheetIdException;
import ru.surf.model.SceneProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


public class GoogleSheetsReader {
    private static SceneProperties sceneProperties = SceneProperties.getInstance();
    private static AppPropertiesReader propertiesReader = AppPropertiesReader.getInstance();

    private static final String APPLICATION_NAME = "SurfDeviceDresser";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = propertiesReader.readProperty("credentialsJsonPath", "credentials.json");

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = null;

        try {
            in = Files.newInputStream(Paths.get(CREDENTIALS_FILE_PATH));
        } catch (IOException e) {
            throw new WrongCredentialsJsonPathException(CREDENTIALS_FILE_PATH);
        }

        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(1337).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static ValueRange getValues(String sheetName, String rangeStart, String rangeEnd) throws IOException, GeneralSecurityException, WrongSpreadsheetIdException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = sceneProperties.getSpreadsheetId();

        if (spreadsheetId.isEmpty()) {
            throw new WrongSpreadsheetIdException();
        }

        ValueRange response = null;
        String range = "";
        if (sheetName.contains(" ")) {
            sheetName = "'" + sheetName + "'";
        }

        if ((rangeStart == null || rangeStart.isEmpty()) || (rangeEnd == null || rangeEnd.isEmpty())) {
            range = sheetName;
        } else {
            range = sheetName + "!" + rangeStart + ":" + rangeEnd;
        }

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        return response;
    }
}