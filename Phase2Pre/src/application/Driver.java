package application;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	DistrictTree districtTree = new DistrictTree();
	DistrictTreeNode districtNode = new DistrictTreeNode();
	LocationTree locationTree = new LocationTree();
	MartyrDateTree martyrDateTree = new MartyrDateTree();
	LinkedListMartyr martyrLinkedList = new LinkedListMartyr();
	ComboBox<String> comboBoxForDistricts = new ComboBox<String>();
	ComboBox<String> comboBoxForLocations = new ComboBox<String>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		pane = firstScreen(primaryStage);
		Scene scene = new Scene(pane, 900, 700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Palestinian Martyrs");
		primaryStage.show();

	}

	public Pane firstScreen(Stage stage) {
		Pane pane = new Pane();
		pane.setPrefSize(901, 700);

		Text welcomeText = new Text("Welcome To The Palestinian Martyrs Statistics");
		welcomeText.setFont(new Font("Segoe Print", 20));
		welcomeText.setLayoutX(27);
		welcomeText.setLayoutY(49);
		welcomeText.setWrappingWidth(500);

		ImageView imageView = new ImageView(new Image("file:/C:/Users/ayham/OneDrive/سطح%20المكتب/ayham/martyr.jpg"));
		imageView.setFitHeight(516);
		imageView.setFitWidth(471);
		imageView.setLayoutX(21);
		imageView.setLayoutY(106);
		imageView.setPreserveRatio(true);
		imageView.setPickOnBounds(true);
		Image icon = new Image("file:/C:/Users/ayham/OneDrive/سطح المكتب/ayham/372e4627a5fc83a70a84ea0b4094bc09.jpg");
		stage.getIcons().add(icon);
		Button readFileButton = new Button("Read File");
		readFileButton.setLayoutX(657);
		readFileButton.setLayoutY(124);
		readFileButton.setPrefSize(166, 43);
		readFileButton.setOnAction(e -> {
			try {
				String successMessage = readfile(stage); // Call read file method
				if (successMessage != null && !successMessage.isEmpty()) {
					Text successText = new Text(successMessage);
					successText.setLayoutX(27);
					successText.setLayoutY(650);
					pane.getChildren().add(successText);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button goToDistrictScreenButton = new Button("Go To District Screen");
		goToDistrictScreenButton.setLayoutX(657);
		goToDistrictScreenButton.setLayoutY(213);
		goToDistrictScreenButton.setPrefSize(166, 43);
		goToDistrictScreenButton.setOnAction(e -> {
			secondScreen(stage);
		});

		Button goToLocationScreenButton = new Button("Go To Location Screen");
		goToLocationScreenButton.setLayoutX(657);
		goToLocationScreenButton.setLayoutY(307);
		goToLocationScreenButton.setPrefSize(166, 43);
		goToLocationScreenButton.setOnAction(e -> {
			thirdScreen(stage);
		});

		Button goToMartyrScreenButton = new Button("Go To Martyr Screen");
		goToMartyrScreenButton.setLayoutX(657);
		goToMartyrScreenButton.setLayoutY(396);
		goToMartyrScreenButton.setPrefSize(166, 43);
		goToMartyrScreenButton.setOnAction(e -> {
			fourthScreen(stage);
		});

		Button saveToFileButton = new Button("Save To File");
		saveToFileButton.setLayoutX(657);
		saveToFileButton.setLayoutY(487);
		saveToFileButton.setPrefSize(166, 43);
		saveToFileButton.setOnAction(e -> {
			writeFile(stage);
			try {
				String successMessage = writeFile(stage); // Call read file method
				if (successMessage != null && !successMessage.isEmpty()) {
					Text successText = new Text(successMessage);
					successText.setLayoutX(27);
					successText.setLayoutY(650);
					pane.getChildren().add(successText);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button exitButton = new Button("Exit");
		exitButton.setLayoutX(657);
		exitButton.setLayoutY(580);
		exitButton.setPrefSize(166, 43);
		exitButton.setTextFill(Color.RED);
		exitButton.setFont(new Font(19));
		exitButton.setOnAction(e -> stage.close()); // Close the stage (exit the program)

		pane.getChildren().addAll(welcomeText, imageView, readFileButton, goToDistrictScreenButton,
				goToLocationScreenButton, goToMartyrScreenButton, saveToFileButton, exitButton);

		return pane;
	}

	public Pane secondScreen(Stage stage) {// district screen
		Pane pane = new Pane();
		pane.setPrefSize(900, 700);

		TextArea textArea = new TextArea();
		textArea.setLayoutX(235);
		textArea.setLayoutY(44);
		textArea.setPrefSize(647, 300);

		comboBoxForDistricts.setLayoutX(16);
		comboBoxForDistricts.setLayoutY(50);
		comboBoxForDistricts.setPrefSize(203, 25);

		Button loadLocationButton = new Button("Load District's Location");
		loadLocationButton.setLayoutX(460);
		loadLocationButton.setLayoutY(400);
		loadLocationButton.setPrefSize(200, 36);

		Button nextButton = new Button("Next");
		nextButton.setLayoutX(760);
		nextButton.setLayoutY(370);
		nextButton.setPrefSize(118, 6);
		Button previousButton = new Button("Previous");
		previousButton.setLayoutX(245);
		previousButton.setLayoutY(370);
		previousButton.setPrefSize(118, 6);
		Button insertButton = new Button("Insert");
		insertButton.setLayoutX(28);
		insertButton.setLayoutY(603);
		insertButton.setPrefSize(118, 36);
		Button deleteButton = new Button("Delete");
		deleteButton.setLayoutX(326);
		deleteButton.setLayoutY(603);
		deleteButton.setPrefSize(118, 36);
		Button updateButton = new Button("Update");
		updateButton.setLayoutX(176);
		updateButton.setLayoutY(603);
		updateButton.setPrefSize(118, 36);
		Button goToLocationScreenButton = new Button("Go To Location Screen");
		goToLocationScreenButton.setOnAction(e -> {
			// thirdScreen(stage);
		});
		goToLocationScreenButton.setLayoutX(728);
		goToLocationScreenButton.setLayoutY(639);
		goToLocationScreenButton.setPrefSize(150, 36);
		goToLocationScreenButton.setTextFill(Color.valueOf("#0db136"));

		Button goToMainScreenButton = new Button("Go To Main Screen");
		goToMainScreenButton.setOnAction(e -> {
			Pane mainPane = firstScreen(stage);
			Scene mainScene = new Scene(mainPane, 900, 700);
			stage.setScene(mainScene);
			stage.setTitle("Main Screen");
		});
		goToMainScreenButton.setLayoutX(550);
		goToMainScreenButton.setLayoutY(639);
		goToMainScreenButton.setPrefSize(150, 36);

		pane.getChildren().addAll(textArea, loadLocationButton, comboBoxForDistricts, nextButton, previousButton,
				insertButton, deleteButton, updateButton, goToMainScreenButton, goToLocationScreenButton);

		MyStack districtNamesStack = districtTree.getDistrictNamesInStack();
		MyStack tempStack = new MyStack();
		MyStack tempStack2 = new MyStack();

		while (!districtNamesStack.isEmpty()) {
			tempStack.push(districtNamesStack.pop());
		}

		while (!tempStack.isEmpty()) {
			String name = (String) tempStack.pop();
			comboBoxForDistricts.getItems().add(name);
			tempStack2.push(name); // Keep another stack for original order
		}

		// Restore tempStack for navigation
		while (!tempStack2.isEmpty()) {
			tempStack.push(tempStack2.pop());
		}

		if (!tempStack.isEmpty()) {
			String firstDistrict = (String) tempStack.pop();
			displayDistrict(firstDistrict, textArea);
			comboBoxForDistricts.setValue(firstDistrict);
		}

		nextButton.setOnAction(x -> {
			String currentDistrict = comboBoxForDistricts.getValue();
			int currentIndex = comboBoxForDistricts.getItems().indexOf(currentDistrict);

			if (currentIndex != -1 && currentIndex < comboBoxForDistricts.getItems().size() - 1) {
				String nextDistrict = comboBoxForDistricts.getItems().get(currentIndex + 1);
				displayDistrict(nextDistrict, textArea);
				comboBoxForDistricts.setValue(nextDistrict);
			}
		});

		previousButton.setOnAction(x -> {
			String currentDistrict = comboBoxForDistricts.getValue();
			int currentIndex = comboBoxForDistricts.getItems().indexOf(currentDistrict);

			if (currentIndex != -1 && currentIndex > 0) {
				String previousDistrict = comboBoxForDistricts.getItems().get(currentIndex - 1);
				displayDistrict(previousDistrict, textArea);
				comboBoxForDistricts.setValue(previousDistrict);
			}
		});

		comboBoxForDistricts.setOnAction(event -> {
			String selectedDistrict = comboBoxForDistricts.getValue();
			DistrictTreeNode districtNode = districtTree.searchDistrict(selectedDistrict);
			displayDistrict(districtNode, textArea);
		});
		insertButton.setOnAction(e -> {
			String newDistrict = getInput("Enter The District Name: ");
			if (newDistrict != null && !newDistrict.isEmpty()) {
				if (districtTree.searchDistrict(newDistrict) != null) {
					// District already exists, show error message
					showAlert(Alert.AlertType.ERROR, "Error", "District Already Exists", "The district '" + newDistrict
							+ "' already exists in the tree. Please enter a different district name.");
				} else {
					Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
					confirmAlert.setTitle("Confirmation");
					confirmAlert.setHeaderText("Confirm District Addition");
					confirmAlert.setContentText("Are you sure you want to add the district '" + newDistrict + "'?");

					Optional<ButtonType> result = confirmAlert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {
						districtTree.insert(newDistrict);
						refreshComboBox(comboBoxForDistricts);
						showAlert(Alert.AlertType.INFORMATION, "Success", "District Added Successfully",
								"The district '" + newDistrict + "' has been successfully added to the tree.");
					} else {
						// User canceled the operation
						showAlert(Alert.AlertType.INFORMATION, "Information", "Operation Canceled",
								"The addition of the district '" + newDistrict + "' has been canceled.");
					}
				}
			}
		});

		updateButton.setOnAction(e -> {
			String oldDistrict = comboBoxForDistricts.getValue();
			if (oldDistrict != null) {
				String newDistrict = getInput("Enter The New District Name For: " + oldDistrict);
				if (newDistrict != null && !newDistrict.isEmpty()) {
					if (districtTree.searchDistrict(newDistrict) != null) {
						// District already exists, show error message
						showAlert(Alert.AlertType.ERROR, "Error", "District Already Exists",
								"The district '" + newDistrict
										+ "' already exists in the tree. Please enter a different district name.");
					} else {
						Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
						confirmAlert.setTitle("Confirmation");
						confirmAlert.setHeaderText("Confirm District Update");
						confirmAlert
								.setContentText("Are you sure you want to update the district '" + newDistrict + "'?");
						Optional<ButtonType> result = confirmAlert.showAndWait();
						if (result.isPresent() && result.get() == ButtonType.OK) {
							String tempText = oldDistrict;
							districtTree.updateDistrictName(oldDistrict, newDistrict);
							System.out.println(districtTree.searchDistrict(newDistrict));
							refreshComboBox(comboBoxForDistricts);
							showAlert(Alert.AlertType.INFORMATION, "Success", "District Updated Successfully",
									"The district '" + tempText + "' has been successfully Updated to " + newDistrict);
						} else {
							// User canceled the operation
							showAlert(Alert.AlertType.INFORMATION, "Information", "Operation Canceled",
									"The update of the district '" + oldDistrict + "' has been canceled.");
						}
					}
				}
			}
		});

		deleteButton.setOnAction(e -> {
			String districtToDelete = comboBoxForDistricts.getValue();
			if (districtToDelete != null && !districtToDelete.isEmpty()) {
				Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
				confirmAlert.setTitle("Confirmation");
				confirmAlert.setHeaderText("Confirm District Deletion");
				confirmAlert.setContentText("Are you sure you want to delete the district '" + districtToDelete + "'?");

				Optional<ButtonType> result = confirmAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					districtTree.delete(districtToDelete);
					comboBoxForDistricts.getItems().remove(districtToDelete);
					if (!comboBoxForDistricts.getItems().isEmpty()) {
						comboBoxForDistricts.setValue(comboBoxForDistricts.getItems().get(0));
						displayDistrict(comboBoxForDistricts.getValue(), textArea);
					} else {
						textArea.clear();
					}
				}
			}
		});
		loadLocationButton.setOnAction(e -> {

			thirdScreen(stage);
		});

		Scene districtScene = new Scene(pane, 900, 700);
		stage.setScene(districtScene);
		stage.setTitle("Location Screen");
		stage.show();

		return pane;
	}

	public Pane thirdScreen(Stage stage) {// location screen
		Pane pane = new Pane();
		String selectedDistrict = comboBoxForDistricts.getValue();
		MyStack locations = getLocationsForDistrict(selectedDistrict); // Method to retrieve locations for the
																		// selected district

		TextArea textArea = new TextArea();
		textArea.setLayoutX(235);
		textArea.setLayoutY(44);
		textArea.setPrefSize(647, 300);

		comboBoxForLocations = new ComboBox<>();
		comboBoxForLocations.setLayoutX(16.0);
		comboBoxForLocations.setLayoutY(50.0);
		comboBoxForLocations.setPrefHeight(25.0);
		comboBoxForLocations.setPrefWidth(203.0);

		Button loadLocationButton = new Button("Load Location's Matytrs");
		loadLocationButton.setLayoutX(460);
		loadLocationButton.setLayoutY(400);
		loadLocationButton.setPrefSize(200, 36);

		Button nextButton = new Button("Next");
		nextButton.setLayoutX(760);
		nextButton.setLayoutY(370);
		nextButton.setPrefSize(118, 6);
		Button previousButton = new Button("Previous");
		previousButton.setLayoutX(245);
		previousButton.setLayoutY(370);
		previousButton.setPrefSize(118, 6);

		Button insertButton = new Button("Insert");
		insertButton.setLayoutX(28.0);
		insertButton.setLayoutY(603.0);
		insertButton.setPrefHeight(36.0);
		insertButton.setPrefWidth(118.0);

		Button deleteButton = new Button("Delete");
		deleteButton.setLayoutX(326.0);
		deleteButton.setLayoutY(603.0);
		deleteButton.setPrefHeight(36.0);
		deleteButton.setPrefWidth(118.0);

		Button updateButton = new Button("Update");
		updateButton.setLayoutX(176.0);
		updateButton.setLayoutY(603.0);
		updateButton.setPrefHeight(36.0);
		updateButton.setPrefWidth(118.0);

		Button goToDistrictScreenButton = new Button("Go To District Screen");
		goToDistrictScreenButton.setOnAction(e -> {
			secondScreen(stage);
		});

		goToDistrictScreenButton.setLayoutX(550);
		goToDistrictScreenButton.setLayoutY(639);
		goToDistrictScreenButton.setPrefHeight(36.0);
		goToDistrictScreenButton.setPrefWidth(150.0);

		Button goToMartyrScreenButton = new Button("Go To Martyr Screen");
		goToMartyrScreenButton.setLayoutX(728.0);
		goToMartyrScreenButton.setLayoutY(639.0);
		goToMartyrScreenButton.setPrefHeight(36.0);
		goToMartyrScreenButton.setPrefWidth(150.0);
		goToMartyrScreenButton.setTextFill(Color.valueOf("#cf1212"));

		goToMartyrScreenButton.setOnAction(e -> {
			fourthScreen(stage);
		});

		pane.getChildren().addAll(textArea, loadLocationButton, comboBoxForLocations, nextButton, previousButton,
				insertButton, deleteButton, updateButton, goToDistrictScreenButton, goToMartyrScreenButton);

		goToMartyrScreenButton.setOnAction(e -> {
			fourthScreen(stage);
		});
		MyStack tempStack = new MyStack();
		while (!locations.isEmpty()) {
			String location = (String) locations.pop();
			comboBoxForLocations.getItems().add(location);
			tempStack.push(location);
		}
		// Restore locations to the original stack
		while (!tempStack.isEmpty()) {
			locations.push(tempStack.pop());
		}

		if (!comboBoxForLocations.getItems().isEmpty()) {
			comboBoxForLocations.setValue(comboBoxForLocations.getItems().get(0));
			textArea.setText(comboBoxForLocations.getItems().get(0));
		}
		nextButton.setOnAction(e -> {
			String currentLocation = comboBoxForLocations.getValue();
			int currentIndex = comboBoxForLocations.getItems().indexOf(currentLocation);

			if (currentIndex != -1 && currentIndex < comboBoxForLocations.getItems().size() - 1) {
				String nextLocation = comboBoxForLocations.getItems().get(currentIndex + 1);
				displayLocation(nextLocation, textArea);
				comboBoxForLocations.setValue(nextLocation);
			}
		});
		previousButton.setOnAction(x -> {
			String currentLocation = comboBoxForLocations.getValue();
			int currentIndex = comboBoxForLocations.getItems().indexOf(currentLocation);

			if (currentIndex != -1 && currentIndex > 0) {
				String previousLocation = comboBoxForLocations.getItems().get(currentIndex - 1);
				displayLocation(selectedDistrict, textArea);
				comboBoxForDistricts.setValue(previousLocation);
			}
		});

		comboBoxForLocations.setOnAction(event -> {
			String selectedLocation = comboBoxForLocations.getValue();
			LocationTreeNode locationNode = locationTree.searchLocation(selectedLocation);
			if (locationNode != null && locationNode.getDate() != null) {
				MartyrDateTree martyrDateTree = locationNode.getDate();
				// Get the earliest date with martyrs
				LocalDate earliestDate = getEarliestDate(martyrDateTree.getRoot());
				// Get the latest date with martyrs
				LocalDate latestDate = getLatestDate(martyrDateTree.getRoot());
				// Get the date with maximum number of martyrs
				LocalDate maxMartyrsDate = getMaxMartyrsDate(martyrDateTree.getRoot());

				// Display the information in the text area
				textArea.setText("Earliest date with martyrs: " + earliestDate + "\n" + "Latest date with martyrs: "
						+ latestDate + "\n" + "Date with maximum number of martyrs: " + maxMartyrsDate);
			} else {
				textArea.setText("No martyr records found for this location.");
			}
		});
		insertButton.setOnAction(e -> {
			String newLocation = getInput("Enter The Location Name: ");
			if (newLocation != null && !newLocation.isEmpty()) {
				if (locationTree.searchLocation(newLocation) != null) {
					// location already exists, show error message
					showAlert(Alert.AlertType.ERROR, "Error", "Location Already Exists", "The location '" + newLocation
							+ "' already exists in the tree. Please enter a different location name.");
				} else {
					Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
					confirmAlert.setTitle("Confirmation");
					confirmAlert.setHeaderText("Confirm Location Addition");
					confirmAlert.setContentText("Are you sure you want to add the location '" + newLocation + "'?");

					Optional<ButtonType> result = confirmAlert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {
						locationTree.insert(newLocation);
						refreshComboBox(comboBoxForLocations);
						showAlert(Alert.AlertType.INFORMATION, "Success", "Location Added Successfully",
								"The district '" + newLocation + "' has been successfully added to the tree.");
					} else {
						// User canceled the operation
						showAlert(Alert.AlertType.INFORMATION, "Information", "Operation Canceled",
								"The addition of the district '" + newLocation + "' has been canceled.");
					}
				}
			}
		});
		updateButton.setOnAction(e -> {
			String oldLocation = comboBoxForLocations.getValue();
			if (oldLocation != null) {
				String newLocation = getInput("Enter The New District Name For: " + oldLocation);
				if (newLocation != null && !newLocation.isEmpty()) {
					if (locationTree.searchLocation(newLocation) != null) {
						// Location already exists, show error message
						showAlert(Alert.AlertType.ERROR, "Error", "Location Already Exists",
								"The location '" + newLocation
										+ "' already exists in the tree. Please enter a different location name.");
					} else {
						Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
						confirmAlert.setTitle("Confirmation");
						confirmAlert.setHeaderText("Confirm District Update");
						confirmAlert
								.setContentText("Are you sure you want to update the location '" + newLocation + "'?");
						Optional<ButtonType> result = confirmAlert.showAndWait();
						if (result.isPresent() && result.get() == ButtonType.OK) {
							String tempText = oldLocation;
							districtTree.updateDistrictName(oldLocation, newLocation);
							// System.out.println(locationTree.searchLocation(newLocation));
							refreshComboBox(comboBoxForLocations);
							showAlert(Alert.AlertType.INFORMATION, "Success", "Location Updated Successfully",
									"The location '" + tempText + "' has been successfully Updated to " + newLocation);
						} else {
							// User canceled the operation
							showAlert(Alert.AlertType.INFORMATION, "Information", "Operation Canceled",
									"The update of the location '" + oldLocation + "' has been canceled.");
						}
					}
				}
			}
		});

		deleteButton.setOnAction(e -> {
			String locationToDelete = comboBoxForLocations.getValue();
			if (locationToDelete != null && !locationToDelete.isEmpty()) {
				Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
				confirmAlert.setTitle("Confirmation");
				confirmAlert.setHeaderText("Confirm Loation Deletion");
				confirmAlert.setContentText("Are you sure you want to delete the location '" + locationToDelete + "'?");

				Optional<ButtonType> result = confirmAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					locationTree.delete(locationToDelete);
					comboBoxForLocations.getItems().remove(locationToDelete);
					if (!comboBoxForLocations.getItems().isEmpty()) {
						comboBoxForLocations.setValue(comboBoxForLocations.getItems().get(0));
						// displayLocation(selected, textArea);
					} else {
						textArea.clear();
					}
				}
			}
		});
		// Create scene and set it to the stage
		Scene locationScene = new Scene(pane, 900, 700);
		stage.setScene(locationScene);
		stage.setTitle("Location Screen");
		stage.show();
		return pane;

	}

	public Pane fourthScreen(Stage stage) {// martyr screen
		TextArea textArea = new TextArea();
		textArea.setLayoutX(562);
		textArea.setLayoutY(32);
		textArea.setPrefHeight(302);
		textArea.setPrefWidth(312);

		// Create TableView
		TableView<Object> tableView = new TableView<>();
		tableView.setLayoutX(14);
		tableView.setLayoutY(14);
		tableView.setPrefHeight(546);
		tableView.setPrefWidth(525);

		// Create TableColumn
		TableColumn<Object, Object> column1 = new TableColumn<>("C1");
		column1.setPrefWidth(75);
		TableColumn<Object, Object> column2 = new TableColumn<>("C2");
		column2.setPrefWidth(75);
		tableView.getColumns().addAll(column1, column2);

		// Create Buttons
		Button previousDateButton = new Button("Previous Date");
		previousDateButton.setLayoutX(562);
		previousDateButton.setLayoutY(350);
		previousDateButton.setPrefHeight(37);
		previousDateButton.setPrefWidth(96);

		Button nextDateButton = new Button("Next Date");
		nextDateButton.setLayoutX(787);
		nextDateButton.setLayoutY(350);
		nextDateButton.setPrefHeight(37);
		nextDateButton.setPrefWidth(87);

		Button insertButton = new Button("Insert");
		insertButton.setLayoutX(40);
		insertButton.setLayoutY(573);
		insertButton.setPrefHeight(37);
		insertButton.setPrefWidth(65);

		Button updateButton = new Button("Update");
		updateButton.setLayoutX(122);
		updateButton.setLayoutY(573);
		updateButton.setPrefHeight(37);
		updateButton.setPrefWidth(65);

		Button searchButton = new Button("Search");
		searchButton.setLayoutX(287);
		searchButton.setLayoutY(573);
		searchButton.setPrefHeight(37);
		searchButton.setPrefWidth(65);

		Button deleteButton = new Button("Delete");
		deleteButton.setLayoutX(204);
		deleteButton.setLayoutY(573);
		deleteButton.setPrefHeight(37);
		deleteButton.setPrefWidth(65);

		Button goToDistrictScreenButton = new Button("Go To The District Screen");
		goToDistrictScreenButton.setLayoutX(521);
		goToDistrictScreenButton.setLayoutY(642);
		goToDistrictScreenButton.setPrefHeight(37);
		goToDistrictScreenButton.setPrefWidth(161);
		goToDistrictScreenButton.setTextFill(javafx.scene.paint.Color.valueOf("#c91919"));

		goToDistrictScreenButton.setOnAction(e -> {
			secondScreen(stage);

		});
		Button goToLocationScreenButton = new Button("Go To The Location Screen");
		goToLocationScreenButton.setLayoutX(718);
		goToLocationScreenButton.setLayoutY(642);
		goToLocationScreenButton.setPrefHeight(37);
		goToLocationScreenButton.setPrefWidth(161);
		goToLocationScreenButton.setTextFill(javafx.scene.paint.Color.valueOf("#18bf39"));
		goToLocationScreenButton.setOnAction(e -> {
			thirdScreen(stage);
		});
		// Create Pane
		Pane pane = new Pane();
		pane.getChildren().addAll(textArea, tableView, previousDateButton, nextDateButton, insertButton, updateButton,
				searchButton, deleteButton, goToDistrictScreenButton, goToLocationScreenButton);

		// Create Scene
		Scene scene = new Scene(pane, 900, 700);

		// Set the Scene
		stage.setScene(scene);
		stage.setTitle("Martyr Screen");
		stage.show();
		return pane;

	}

	public String readfile(Stage primaryStage) throws ParseException { // this method read all date from a file you
		int counter = 0; // choose
		// and save it
		// choose a file to read information from it
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File ");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*")); // show the file in
		// stage
		File file = fileChooser.showOpenDialog(primaryStage);
		// File file = new File("C:\\Users\\ayham\\OneDrive\\سطح
		// المكتب\\ayham\\Test.txt");

		if (file != null) { // check if the file has data
			try {
				Scanner scanner = new Scanner(file); // read from file
				while (scanner.hasNextLine()) {
					String Line = scanner.nextLine(); // take line line from file
					if (Line.isEmpty()) {
						continue; // Skip empty lines
					}

					String[] split = Line.split(",");
					if (split.length < 6) {
						System.out.println("Invalid line format: " + Line);
						continue; // Skip invalid lines
					}

					String name = split[0];
					String d = split[1];
					LocalDate date = parseDate(d);
					int age = 0;
					if (!split[2].trim().isEmpty()) {
						try {
							age = Integer.parseInt(split[2]);
						} catch (NumberFormatException e) {
							continue; // Skip lines with invalid age
						}
					}
					String locationName = split[3];
					String districtName = split[4];
					char gender = split[5].charAt(0);

					Martyr martyr = new Martyr(name, date, age, districtName, locationName, gender);
					counter++;
					martyrLinkedList.insert(martyr);
					martyrDateTree.insert(date);
					locationTree.insert(locationName);
					districtTree.insert(districtName);

					// Check if the district already exists in the district tree
					DistrictTreeNode districtNode = districtTree.searchDistrict(districtName);
					if (districtNode == null) {
						// If the district doesn't exist, create a new district node and add it to the
						// district tree
						districtNode = new DistrictTreeNode(districtName);
						districtNode.setLocationTree(new LocationTree());
						districtTree.insert(districtNode);
					}
					// Ensure that the location tree of the district node is initialized
					if (districtNode.getLocationTree() == null) {
						districtNode.setLocationTree(new LocationTree());
					}

					// Add the location to the corresponding district's location tree
					districtNode.getLocationTree().insert(locationName);
					// Get the location tree of the current district node

					// Check if the location already exists in the location tree
					LocationTreeNode locationNode = locationTree.searchLocation(locationName);
					if (locationNode == null) {
						// If the location doesn't exist, create a new location node and add it to the
						// location tree
						locationNode = new LocationTreeNode(locationName);
						locationTree.insert(locationNode);
					}

					// Ensure that the date tree of the location node is initialized
					if (locationNode.getDate() == null) {
						locationNode.setDate(new MartyrDateTree());
					}

					// Add the date to the corresponding location's date tree
					locationNode.getDate().insert(date);

					MartyrDateTreeNode dateNode = martyrDateTree.Search(date);
					if (dateNode == null) {
						// If the date doesn't exist, create a new date node and add it to the date tree
						dateNode = new MartyrDateTreeNode(date);
						martyrDateTree.insert(dateNode);
					}
					if (dateNode.getMartyrsLinkedList() == null) {
						dateNode.setMartyrsLinkedList(new LinkedListMartyr());
					}

					// Add the martyr to the corresponding date's linked list
					SingleMartyrNode martyrNode = martyrLinkedList.searchForMartyr(name);
					if (martyrNode == null) {
						martyrNode = new SingleMartyrNode(martyr);
						martyrLinkedList.insert(martyr);

					}

					dateNode.getMartyrsLinkedList().insert(martyr);

				}
				scanner.close();
				return "File read successfully."; // Return success message
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "Error reading file: " + e.getMessage(); // Return error message
			}

		} else {
			return "No file selected."; // Return message for no file selected
		}

	}

	public String writeFile(Stage primaryStage) {
		// choose the file to write the new Sorted data inside it
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*")); // show the file in
																									// stage
		File file = fileChooser.showSaveDialog(primaryStage); // Changed to showSaveDialog for saving

		if (file != null) { // check if a file was selected
			try (PrintWriter writer = new PrintWriter(file)) { // try-with-resources to ensure the writer is closed
				SingleMartyrNode current = martyrLinkedList.getHead();

				while (current != null) {
					Martyr martyr = current.getMartyr();
					String name = martyr.getName();
					String date = martyr.getDateOfDeath().toString();
					int age = martyr.getAge();
					String location = martyr.getLocation();
					String district = martyr.getDistrict();
					char gender = martyr.getGender();

					String line = name + "," + date + "," + age + "," + location + "," + district + "," + gender;
					writer.println(line);

					current = current.next;
				}

				writer.flush();
				return "File Saved successfully";
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return "No file selected.";
	}

	public static LocalDate parseDate(String dateString) {
		// Define multiple date formats to support different patterns
		DateTimeFormatter[] formatters = { DateTimeFormatter.ofPattern("MM/dd/yyyy"),
				DateTimeFormatter.ofPattern("M/dd/yyyy"), DateTimeFormatter.ofPattern("MM/d/yyyy"),
				DateTimeFormatter.ofPattern("M/d/yyyy") };

		// Try parsing the date with each formatter
		for (DateTimeFormatter formatter : formatters) {
			try {
				return LocalDate.parse(dateString, formatter);
			} catch (DateTimeParseException e) {
				// Try the next formatter if parsing fails
			}
		}

		// If none of the formatters succeed, throw an exception
		throw new IllegalArgumentException("Invalid date format: " + dateString);
	}

	private void displayDistrict(DistrictTreeNode district, TextArea textArea) {
		if (district != null) { // Check if the district is not null
			if (district.locationTree != null) {
				textArea.setText("District: " + district.getDistrictName() + "\nTotal Martyrs: "
						+ district.locationTree.getTotalMartyrs());
			} else {
				textArea.setText("District: " + district.getDistrictName() + "\nTotal Martyrs: 0");
			}
		} else {
			// Handle the case where district is null
			textArea.setText("District: [District not found]\nTotal Martyrs: 0");
		}
	}

//	private void displayLocation(LocationTreeNode location, TextArea textArea) {
//		if (location != null) { // Check if the location is not null
//			if (location.date != null) {
//				textArea.setText("Location: " + location.getLocationName() + "\nTotal Martyrs: "
//						+ district.locationTree.getTotalMartyrs());
//			} else {
//				textArea.setText("District: " + district.getDistrictName() + "\nTotal Martyrs: 0");
//			}
//		} else {
//			// Handle the case where district is null
//			textArea.setText("District: [District not found]\nTotal Martyrs: 0");
//		}
//	}

	private void refreshComboBox(ComboBox<String> comboBox) {
		comboBox.getItems().clear();
		MyStack districtNamesStack = districtTree.getDistrictNamesInStack();
		MyStack tempStack = new MyStack();

		while (!districtNamesStack.isEmpty()) {
			tempStack.push(districtNamesStack.pop());
		}

		while (!tempStack.isEmpty()) {
			String name = (String) tempStack.pop();
			comboBox.getItems().add(name);
		}

	}

	private String getInput(String prompt) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Input");
		dialog.setHeaderText(prompt);
		return dialog.showAndWait().orElse(null);
	}

	private void displayDistrict(String districtName, TextArea textArea) {
		DistrictTreeNode districtNode = districtTree.searchDistrict(districtName);
		if (districtNode != null && districtNode.getLocationTree() != null) {
			textArea.setText("District: " + districtNode.districtName + "\nTotal Martyrs: "
					+ districtNode.locationTree.getTotalMartyrs());
		} else {
			textArea.setText("District: " + districtName + "\nTotal Martyrs: 0");
		}
	}

	public static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public MyStack getLocationsForDistrict(String district) {
		MyStack locations = new MyStack();
		DistrictTreeNode districtNode = districtTree.searchDistrict(district);

		if (districtNode != null) {
			locations = districtNode.getLocationTree().getAllLocations();
		}
		return locations;
	}

	private LocalDate getEarliestDate(MartyrDateTreeNode node) {
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node.getDate();
	}

	private LocalDate getLatestDate(MartyrDateTreeNode node) {
		if (node == null) {
			return null;
		}
		while (node.right != null) {
			node = node.right;
		}
		return node.getDate();
	}

	private LocalDate getMaxMartyrsDate(MartyrDateTreeNode node) {
		if (node == null) {
			return null;
		}

		// Find the maximum number of martyrs on the current node and its subtrees
		int maxMartyrsCount = getMaxMartyrsCount(node);
		LocalDate maxMartyrsDate = node.getDate();

		// Find the maximum number of martyrs on the left subtree
		LocalDate leftMaxDate = getMaxMartyrsDate(node.left);
		if (leftMaxDate != null && getMaxMartyrsCount(node.left) > maxMartyrsCount) {
			maxMartyrsDate = leftMaxDate;
		}

		// Find the maximum number of martyrs on the right subtree
		LocalDate rightMaxDate = getMaxMartyrsDate(node.right);
		if (rightMaxDate != null && getMaxMartyrsCount(node.right) > maxMartyrsCount) {
			maxMartyrsDate = rightMaxDate;
		}

		return maxMartyrsDate;
	}

	// Helper method to find the maximum number of martyrs in the subtree rooted at
	// the given node
	private int getMaxMartyrsCount(MartyrDateTreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftCount = getMaxMartyrsCount(node.left);
		int rightCount = getMaxMartyrsCount(node.right);
		return Math.max(node.getMartyrsLinkedList() != null ? node.getMartyrsLinkedList().size() : 0,
				Math.max(leftCount, rightCount));
	}

	private void displayLocation(String selectedLocation, TextArea textArea) {
		LocationTreeNode locationNode = locationTree.searchLocation(selectedLocation);
		if (locationNode != null && locationNode.getDate() != null) {
			MartyrDateTree martyrDateTree = locationNode.getDate();
			// Get the earliest date with martyrs
			LocalDate earliestDate = getEarliestDate(martyrDateTree.getRoot());
			// Get the latest date with martyrs
			LocalDate latestDate = getLatestDate(martyrDateTree.getRoot());
			// Get the date with maximum number of martyrs
			LocalDate maxMartyrsDate = getMaxMartyrsDate(martyrDateTree.getRoot());

			// Display the information in the text area
			textArea.setText("Earliest date with martyrs: " + earliestDate + "\n" + "Latest date with martyrs: "
					+ latestDate + "\n" + "Date with maximum number of martyrs: " + maxMartyrsDate);
		} else {
			textArea.setText("No martyr records found for this location.");
		}
	}

}
