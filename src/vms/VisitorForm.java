package vms;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitorForm {
    private VBox root;

    public VisitorForm() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setId("form-background");

        Text title = new Text("Visitor Management System");
        title.setId("title");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Visitor Name");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter Phone Number");

        TextField purposeField = new TextField();
        purposeField.setPromptText("Purpose of Visit");

        Button checkInButton = new Button("Check In");
        Button checkOutButton = new Button("Check Out");

        Label statusLabel = new Label();

        SpreadsheetManager manager = new SpreadsheetManager("resources/visitors.xlsx");

        checkInButton.setOnAction(e -> {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            Visitor visitor = new Visitor(nameField.getText(), phoneField.getText(), purposeField.getText(), time, "");
            manager.addVisitor(visitor);
            statusLabel.setText("Check-in recorded successfully!");
        });

        checkOutButton.setOnAction(e -> {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            manager.updateCheckOut(nameField.getText(), time);
            statusLabel.setText("Check-out updated successfully!");
        });

        root.getChildren().addAll(title, nameField, phoneField, purposeField, checkInButton, checkOutButton, statusLabel);
    }

    public VBox getRoot() {
        return root;
    }
}
