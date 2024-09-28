/**
 * Main application class for the Country Information App using JavaFX.
 * Initializes the user interface and sets up event handlers for searching countries by name, language, or currency.
 * Utilizes {@link CountryService} for data retrieval and displays search results in a TableView.
 */

package com.countries.display;

import com.countries.info.domain.CountryDTO;
import com.countries.info.service.CountryService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CountryInfoApp extends Application
{

  private CountryService countryService = new CountryService();

  private TextField searchByNameField = new TextField();
  private TextField searchByLanguageField = new TextField();
  private TextField searchByCurrencyField = new TextField();
  private TableView<CountryDTO> countryTable = new TableView<>();

  private ListView<String> searchHistoryListView = new ListView<>();
  private ObservableList<String> searchHistoryList = FXCollections.observableArrayList();

  public static void main(String[] args)
  {
    launch(args);
  }

  /**
   * The start method is an overridden method from the JavaFX Application class.
   * It is the entry point for launching the JavaFX application and initializing the main GUI.
   *
   * @param primaryStage The primary stage for the application, representing the main window.
   *                     It is automatically created by the JavaFX framework.
   * @throws Exception If an exception occurs during the initialization of the JavaFX application.
   *
   * @see javafx.application.Application
   */
  @Override
  public void start(Stage primaryStage)
  {
    primaryStage.setTitle("Country Information App");

    GridPane grid = new GridPane();
    grid.setHgap(20);
    grid.setVgap(20);
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setAlignment(Pos.CENTER);

    Label searchByNameLabel = new Label("Search by Name:");
    Label searchByLanguageLabel = new Label("Search by Language:");
    Label searchByCurrencyLabel = new Label("Search by Currency:");

    Button searchByNameButton = new Button("Search");
    Button searchByLanguageButton = new Button("Search");
    Button searchByCurrencyButton = new Button("Search");

    TextArea resultTextArea = new TextArea();
    resultTextArea.setEditable(false);

    searchByNameButton.setOnAction(e -> searchByName());
    searchByLanguageButton.setOnAction(e -> searchByLanguage());
    searchByCurrencyButton.setOnAction(e -> searchByCurrency());

    searchHistoryListView.setItems(searchHistoryList);
    searchHistoryListView.setOnMouseClicked(e -> handleSearchHistoryClick());

    grid.add(searchByNameLabel, 0, 0);
    grid.add(searchByNameField, 1, 0);
    grid.add(searchByNameButton, 2, 0);

    grid.add(searchByLanguageLabel, 0, 1);
    grid.add(searchByLanguageField, 1, 1);
    grid.add(searchByLanguageButton, 2, 1);

    grid.add(searchByCurrencyLabel, 0, 2);
    grid.add(searchByCurrencyField, 1, 2);
    grid.add(searchByCurrencyButton, 2, 2);

    grid.add(searchHistoryListView, 0, 3, 3, 1);
    grid.add(countryTable, 0, 4, 3, 1);
    searchHistoryListView.setMaxSize(300, 300);

    // Creating table columns
    TableColumn<CountryDTO, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<CountryDTO, String> capitalColumn = new TableColumn<>("Capital");
    capitalColumn.setCellValueFactory(new PropertyValueFactory<>("capital"));

    TableColumn<CountryDTO, String> language = new TableColumn<>("Language");
    language.setCellValueFactory(new PropertyValueFactory<>("language"));

    TableColumn<CountryDTO, String> currencyColumn = new TableColumn<>("Currency");
    currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

    TableColumn<CountryDTO, Long> populationColumn = new TableColumn<>("Population");
    populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));

    TableColumn<CountryDTO, String> continentColumn = new TableColumn<>("Continent");
    continentColumn.setCellValueFactory(new PropertyValueFactory<>("continent"));

    countryTable.getColumns().add(nameColumn);
    countryTable.getColumns().add(capitalColumn);
    countryTable.getColumns().add(language);
    countryTable.getColumns().add(currencyColumn);
    countryTable.getColumns().add(populationColumn);
    countryTable.getColumns().add(continentColumn);

    ObservableList<CountryDTO> countries = FXCollections.observableArrayList(
            getAllCountries()
    );

    RowConstraints tableViewRow = new RowConstraints();
    tableViewRow.setVgrow(Priority.ALWAYS);
    grid.getRowConstraints().add(tableViewRow);

    countryTable.setMinWidth(725);
    countryTable.setItems(countries);

    Scene scene = new Scene(grid, 800, 600);
    primaryStage.setScene(scene);

    primaryStage.show();
  }

  private List<CountryDTO> getAllCountries()
  {
    List<CountryDTO> countryDTOS = new ArrayList<>();
    try
    {
      countryDTOS = countryService.getAllCountries();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      displayError(e);
    }
    return countryDTOS;
  }

  
  /**
   * Private method to perform a country search by name based on the input in the search field.
   * It retrieves the country name from the "searchByNameField," calls the corresponding method
   * in the CountryService to fetch the search result, and updates both the result table and search history.
   *
   * The method is triggered by the user clicking the "Search" button for searching by country name.
   * If an exception occurs during the search process, it is caught and displayed as an error.
   *
   * @see #getCountryByName(String)
   * @see #updateResultAndHistory(List)
   */
  private void searchByName() {
      // Extracting the country name from the search field
      String countryName = searchByNameField.getText();

      // Fetching the search result based on the country name
      List<CountryDTO> result = getCountryByName(countryName);

      // Updating the result table and search history with the obtained result
      updateResultAndHistory(result);
  }

  /**
   * Private method to retrieve a list of countries by name from the CountryService.
   * It invokes the corresponding method in the CountryService, passing the provided country name,
   * and captures the result. If an exception occurs during the service call, it is caught,
   * printed to the console, and an error alert is displayed using the displayError method.
   *
   * @param countryName The name of the country to be used as a search parameter.
   * @return A List of CountryDTO objects representing the search result.
   *
   * @see CountryService#getCountryByName(String)
   * @see #displayError(Exception)
   */
  private List<CountryDTO> getCountryByName(String countryName) {
      // Initializing an empty list to store the search result
      List<CountryDTO> countryDTOS = new ArrayList<>();

      try {
          // Attempting to fetch the search result from the CountryService
          countryDTOS = countryService.getCountryByName(countryName);
      } catch (Exception e) {
          // Catching and printing any exceptions that occur during the service call
          e.printStackTrace();
          // Displaying an error alert to the user
          displayError(e);
      }

      // Returning the obtained list of CountryDTO objects
      return countryDTOS;
  }


  /**
   * Private method to perform a country search by language based on the input in the language search field.
   * It retrieves the language from the "searchByLanguageField," calls the corresponding method
   * in the CountryService to fetch the search result, and updates both the result table and search history.
   *
   * The method is triggered by the user clicking the "Search" button for searching by language.
   * If an exception occurs during the search process, it is caught and displayed as an error.
   *
   * @see #getCountriesByLanguage(String)
   * @see #updateResultAndHistory(List)
   */
  private void searchByLanguage() {
      // Extracting the language from the search field
      String language = searchByLanguageField.getText();

      // Fetching the search result based on the language
      List<CountryDTO> result = getCountriesByLanguage(language);

      // Updating the result table and search history with the obtained result
      updateResultAndHistory(result);
  }


  /**
   * Private method to retrieve a list of countries by language from the CountryService.
   * It invokes the corresponding method in the CountryService, passing the provided language,
   * and captures the result. If an exception occurs during the service call, it is caught,
   * printed to the console, and an error alert is displayed using the displayError method.
   *
   * @param language The language to be used as a search parameter.
   * @return A List of CountryDTO objects representing the search result.
   *
   * @see CountryService#getCountriesByLanguage(String)
   * @see #displayError(Exception)
   */
  private List<CountryDTO> getCountriesByLanguage(String language) {
      // Initializing an empty list to store the search result
      List<CountryDTO> countryDTOS = new ArrayList<>();

      try {
          // Attempting to fetch the search result from the CountryService
          countryDTOS = countryService.getCountriesByLanguage(language);
      } catch (Exception e) {
          // Catching and printing any exceptions that occur during the service call
          e.printStackTrace();
          // Displaying an error alert to the user
          displayError(e);
      }

      // Returning the obtained list of CountryDTO objects
      return countryDTOS;
  }

  /**
   * Private method to perform a country search by currency based on the input in the currency search field.
   * It retrieves the currency from the "searchByCurrencyField," calls the corresponding method
   * in the CountryService to fetch the search result, and updates both the result table and search history.
   *
   * The method is triggered by the user clicking the "Search" button for searching by currency.
   * If an exception occurs during the search process, it is caught and displayed as an error.
   *
   * @see #getCountriesByCurrency(String)
   * @see #updateResultAndHistory(List)
   */
  private void searchByCurrency() {
      // Extracting the currency from the search field
      String currency = searchByCurrencyField.getText();

      // Fetching the search result based on the currency
      List<CountryDTO> result = getCountriesByCurrency(currency);

      // Updating the result table and search history with the obtained result
      updateResultAndHistory(result);
  }


  /**
   * Private method to retrieve a list of countries by currency from the CountryService.
   * It invokes the corresponding method in the CountryService, passing the provided currency,
   * and captures the result. If an exception occurs during the service call, it is caught,
   * printed to the console, and an error alert is displayed using the displayError method.
   *
   * @param currency The currency to be used as a search parameter.
   * @return A List of CountryDTO objects representing the search result.
   *
   * @see CountryService#getCountriesByCurrency(String)
   * @see #displayError(Exception)
   */
  private List<CountryDTO> getCountriesByCurrency(String currency) {
      // Initializing an empty list to store the search result
      List<CountryDTO> countryDTOS = new ArrayList<>();

      try {
          // Attempting to fetch the search result from the CountryService
          countryDTOS = countryService.getCountriesByCurrency(currency);
      } catch (Exception e) {
          // Catching and printing any exceptions that occur during the service call
          e.printStackTrace();
          // Displaying an error alert to the user
          displayError(e);
      }

      // Returning the obtained list of CountryDTO objects
      return countryDTOS;
  }

  /**
   * Private method to update the result table and search history based on the provided list of CountryDTO objects.
   * It takes the search result, updates the result table with the new data using JavaFX ObservableList,
   * and adds the search query to the search history. The method also ensures that the search history is
   * limited to the last 5 queries.
   *
   * Additionally, the method resets the search fields after updating the UI.
   *
   * @param result A List of CountryDTO objects representing the search result to be displayed.
   *
   * @see #getCountriesByCurrency(String)
   * @see #getCountriesByLanguage(String)
   * @see #getCountryByName(String)
   */
  private void updateResultAndHistory(List<CountryDTO> result) {
      // Creating an ObservableList to store the search result
      ObservableList<CountryDTO> countries = FXCollections.observableArrayList(result);

      // Setting the search result to the country table
      countryTable.setItems(countries);

      // Building the search query based on the populated search fields
      String searchQuery = "";
      if (!searchByNameField.getText().isEmpty()) {
          searchQuery = "Name: " + searchByNameField.getText();
      }

      if (!searchByLanguageField.getText().isEmpty()) {
          searchQuery = "Language: " + searchByLanguageField.getText();
      }

      if (!searchByCurrencyField.getText().isEmpty()) {
          searchQuery = "Currency: " + searchByCurrencyField.getText();
      }

      // Adding the search query to the search history if it is not already present
      if (!searchHistoryList.contains(searchQuery)) {
          searchHistoryList.add(searchQuery);
      }

      // Ensuring that the search history is limited to the last 5 queries
      if (searchHistoryList.size() > 5) {
          searchHistoryList.remove(0);
      }

      // Resetting the search fields
      searchByCurrencyField.setText("");
      searchByNameField.setText("");
      searchByLanguageField.setText("");
  }


  /**
   * Private method to handle the user's click on an item in the search history list view.
   * It retrieves the selected search history item, extracts the search parameters, and updates
   * the corresponding search fields based on the type of search (Name, Language, or Currency).
   * After updating the search fields, it triggers the respective search method to refresh the results.
   *
   * The method is called when the user clicks on an item in the search history list view.
   *
   * @see #searchByName()
   * @see #searchByLanguage()
   * @see #searchByCurrency()
   */
  private void handleSearchHistoryClick() {
      // Retrieving the selected search history item
      String selectedSearch = searchHistoryListView.getSelectionModel().getSelectedItem();

      // Checking if a search history item is selected
      if (selectedSearch != null) {
          // Splitting the search history item into individual search parameters
          String[] searchParams = selectedSearch.split(", ");

          // Iterating through each search parameter
          for (String param : searchParams) {
              if (param.startsWith("Name: ")) {
                  // Extracting the name parameter and triggering the search by name
                  searchByNameField.setText(param.substring("Name: ".length()));
                  searchByName();
              } else if (param.startsWith("Language: ")) {
                  // Extracting the language parameter and triggering the search by language
                  searchByLanguageField.setText(param.substring("Language: ".length()));
                  searchByLanguage();
              } else if (param.startsWith("Currency: ")) {
                  // Extracting the currency parameter and triggering the search by currency
                  searchByCurrencyField.setText(param.substring("Currency: ".length()));
                  searchByCurrency();
              }
          }
      }
  }



  /**
   * Private method to display an error alert to the user with details of the exception.
   * It creates an Alert of type ERROR, sets the title to "Error," and populates the content with
   * the provided exception's message. The alert is then displayed and waits for user acknowledgment.
   *
   * The method is used to inform the user about any unexpected errors that occur during the application's execution.
   *
   * @param ex The Exception object containing details about the error.
   *
   * @see Alert
   * @see Alert.AlertType#ERROR
   */
  private void displayError(Exception ex) {
      // Creating an Alert of type ERROR
      Alert alert = new Alert(Alert.AlertType.ERROR);

      // Setting the title of the error alert
      alert.setTitle("Error");

      // Setting the header text of the error alert
      alert.setHeaderText("An error occurred");

      // Populating the content of the error alert with details from the provided exception
      alert.setContentText("Details: " + ex.getMessage());

      // Displaying the error alert and waiting for user acknowledgment
      alert.showAndWait();
  }

}