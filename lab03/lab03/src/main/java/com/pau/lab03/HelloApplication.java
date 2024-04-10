package com.pau.lab03;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.ParsedValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Proszę wziąć pod uwagę, że brakuje mi jakiegokolwiek wyczucia stylu...

public class HelloApplication extends Application {

    private TableView groupTable = new TableView();
    private TableView teacherTable = new TableView();
    public final ObservableList<ClassTeacher> groups = FXCollections.observableArrayList(
            new ClassTeacher("Klasa1", 2),
            new ClassTeacher("Klasa2", 9)
    );

    private TextField groupsTextFilter = new TextField();
    private TextField teachersTextFilter = new TextField();

    private Button deleteGroupButton = new Button("Usuń Grupę");
    private Button deleteTeacherButton = new Button("Usuń Nauczyciela");
    private Button modifyGroupButton = new Button("Modyfikuj");
    private Button addGroupButton = new Button("Dodaj");
    private Button addTeacherButton = new Button("Dodaj");

    private void RefreshTeacherTableFromSelection() {
        teacherTable.getItems().clear();
        ClassTeacher selected = (ClassTeacher) groupTable.getSelectionModel().getSelectedItems().get(0);
        for(Teacher t : selected.teachers) {
            teacherTable.getItems().add(t);
            teacherTable.refresh();
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(new Group(), 900, 600);
        stage.setTitle("Hello!");

        groupsTextFilter.setPromptText("Filtruj");
        teachersTextFilter.setPromptText("Filtruj (Enter)");

        groupTable.setEditable(true);

        Teacher t1 = new Teacher("Tomasz", "Jabłowski", TeacherCondition.obecny, 1994, 2250);
        Teacher t2 = new Teacher("Jan", "Jabłoński", TeacherCondition.obecny, 1991, 3200);
        Teacher t3 = new Teacher("Jerzy", "Jabolowicz", TeacherCondition.obecny, 1984, 2112);
        Teacher t4 = new Teacher("Slim", "Shady", TeacherCondition.nieobecny, 1978, 4000);
        Teacher t5 = new Teacher("Krzysztof", "Tomaszewski", TeacherCondition.obecny, 1999, 2000);

        groups.get(0).addTeacher(t1);
        groups.get(0).addTeacher(t3);
        groups.get(0).addTeacher(t2); // błąd

        groups.get(1).addTeacher(t4);
        groups.get(1).addTeacher(t2);
        groups.get(1).addTeacher(t5);

        groupTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        groupTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn groupNameCol = new TableColumn("Nazwa");
        TableColumn groupCapCol = new TableColumn("Pojemność");
        TableColumn groupFillCol = new TableColumn("Zapełnienie %");

        groupNameCol.setPrefWidth(9999);
        groupCapCol.setPrefWidth(9999);
        groupFillCol.setPrefWidth(9999);

        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        groupNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        groupNameCol.setOnEditCommit(tev -> {CellEditEvent<ClassTeacher, String> t = (CellEditEvent<ClassTeacher, String>)tev;
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());});
        groupCapCol.setCellValueFactory(new PropertyValueFactory<>("max_teachers"));
        groupCapCol.setCellFactory(TextFieldTableCell.<ClassTeacher, Number>forTableColumn(new NumberStringConverter()));
        groupCapCol.setOnEditCommit(tev -> {CellEditEvent<ClassTeacher, Long> t = (CellEditEvent<ClassTeacher, Long>)tev;
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setMax_teachers(Math.toIntExact(t.getNewValue())); groupTable.refresh();});
        groupFillCol.setCellValueFactory(new PropertyValueFactory<>("fill"));
        groupTable.getColumns().addAll(groupNameCol, groupCapCol, groupFillCol);
        groupTable.setItems(groups);

        groupTable.setMinWidth(300);
        groupTable.setMaxWidth(300);

//        for(ClassTeacher ct : cont.grupy.values()) {
//            groupTable.getItems().add(ct);
//        }

        ObservableList selectedItems =
                groupTable.getSelectionModel().getSelectedItems();

        selectedItems.addListener(
                new ListChangeListener<ClassTeacher>() {
                    @Override
                    public void onChanged(
                            Change<? extends ClassTeacher> change) {
                        System.out.println(
                                "Selection changed: " + change.getList());

                        teacherTable.getItems().clear();

                        if(change.getList().isEmpty())
                            return;

                        RefreshTeacherTableFromSelection();
                    }
                });


        teacherTable.setEditable(true);

        TableColumn teacherFirstNameCol = new TableColumn("Imie");
        TableColumn teacherLastNameCol = new TableColumn("Nazwisko");
        TableColumn teacherStatusCol = new TableColumn("Stan");
        TableColumn teacherPayCol = new TableColumn("Wynagrodzenie");

        teacherFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
        teacherLastNameCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        teacherStatusCol.setCellValueFactory(new PropertyValueFactory<>("stan"));
        teacherPayCol.setCellValueFactory(new PropertyValueFactory<>("wynagrodzenie"));

        teacherTable.getColumns().addAll(teacherFirstNameCol, teacherLastNameCol, teacherStatusCol, teacherPayCol);

        EventHandler<ActionEvent> deleteGroupAction = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(groupTable.getSelectionModel().isEmpty())
                    return;
                groups.remove(groupTable.getSelectionModel().getSelectedIndex());
                groupTable.refresh();
            }
        };

        EventHandler<ActionEvent> deleteTeacherAction = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(teacherTable.getSelectionModel().isEmpty())
                    return;
                ClassTeacher selected = (ClassTeacher) groupTable.getSelectionModel().getSelectedItems().get(0);
                selected.removeTeacher((Teacher) teacherTable.getSelectionModel().getSelectedItems().get(0));
                teacherTable.getItems().remove(teacherTable.getSelectionModel().getSelectedIndex());
                groupTable.refresh();
            }
        };

        EventHandler<ActionEvent> modifyEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(teacherTable.getSelectionModel().isEmpty())
                    return;

                Teacher selected = (Teacher) teacherTable.getSelectionModel().getSelectedItems().get(0);

                Dialog<Touple4<String, String, TeacherCondition, Double>> dialog = new Dialog();
                dialog.setTitle("Modyfikacja");
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                VBox vbox = new VBox();
                TextField inputName = new TextField();
                inputName.setPromptText("Imie");
                inputName.setText(selected.imie);
                vbox.getChildren().add(new HBox(new Label("Imie:  "), inputName));

                TextField inputLastName = new TextField();
                inputLastName.setPromptText("Nazwisko");
                inputLastName.setText(selected.nazwisko);
                vbox.getChildren().add(new HBox(new Label("Nazwisko:  "), inputLastName));

                ComboBox<String> stateCB = new ComboBox<String>();
                List<String> enumNames = Stream.of(TeacherCondition.values())
                        .map(TeacherCondition::name)
                        .collect(Collectors.toList());
                stateCB.getItems().addAll(enumNames);
                stateCB.getSelectionModel().select(selected.stan.toString());
                vbox.getChildren().add(new HBox(new Label("text"), new Separator(), stateCB));

                TextField inputSalary = new TextField();
                inputSalary.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        if (!newValue.matches("\\d*")) {
                            inputSalary.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
                inputSalary.setText(String.valueOf(selected.wynagrodzenie));
                vbox.getChildren().add(new HBox(new Label("wynagrodzenie:  "), new Separator(), inputSalary));

                dialog.getDialogPane().setContent(vbox);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        return new Touple4<>(inputName.getText(), inputLastName.getText(), TeacherCondition.values()[stateCB.getSelectionModel().getSelectedIndex()], Double.parseDouble(inputSalary.getText()));
                    }
                    return null;
                });

                Optional<Touple4<String, String, TeacherCondition, Double>> res = dialog.showAndWait();

                if(res.isEmpty())
                    return;

                selected.imie = res.get().U;
                selected.nazwisko = res.get().V;
                selected.stan = res.get().W;
                selected.wynagrodzenie = res.get().X;

                teacherTable.getItems().clear();

                ClassTeacher selectedG = (ClassTeacher) groupTable.getSelectionModel().getSelectedItems().get(0);
                for(Teacher t : selectedG.teachers) {
                    teacherTable.getItems().add(t);
                    teacherTable.refresh();
                }
                teacherTable.getSelectionModel().select(selected);
            }
        };

        deleteGroupButton.setOnAction(deleteGroupAction);
        deleteTeacherButton.setOnAction(deleteTeacherAction);
        modifyGroupButton.setOnAction(modifyEvent);
        addGroupButton.setOnAction(t -> groups.add(new ClassTeacher("Nazwa Klasy", 0)));
        addTeacherButton.setOnAction(t -> {
            if(groupTable.getSelectionModel().isEmpty())
                return;
            ClassTeacher selectedG = (ClassTeacher) groupTable.getSelectionModel().getSelectedItems().get(0);
            selectedG.teachers.add(new Teacher("Imie", "Nazwisko", TeacherCondition.nieobecny, 2000, 0));
            RefreshTeacherTableFromSelection();
        });


        FilteredList<ClassTeacher> groupFilteredList = new FilteredList<>(groups);
        SortedList<ClassTeacher> sortableData = new SortedList<ClassTeacher>(groupFilteredList);
        groupTable.setItems(sortableData);
        sortableData.comparatorProperty().bind(groupTable.comparatorProperty());
        groupsTextFilter.textProperty().addListener(x -> groupFilteredList.setPredicate(
                t -> t.name.toString().toLowerCase().contains(groupsTextFilter.getText().toLowerCase())
        ));

        ObservableList data = teacherTable.getItems();
        teachersTextFilter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                String value = teachersTextFilter.getText().toLowerCase();
                if(value.isEmpty())
                    teacherTable.setItems(data);

                if (ke.getCode().equals(KeyCode.ENTER)) {
                    ObservableList<Teacher> subentries = FXCollections.observableArrayList();

                    for(Teacher teacher : (ObservableList<Teacher>)teacherTable.getItems()) {
                        if(teacher.imie.toLowerCase().contains(value) || teacher.nazwisko.toLowerCase().contains(value)) {
                            subentries.add(teacher);
                        }
                    }
                    teacherTable.setItems(subentries);
                }
            }
        });


        final HBox tableBox = new HBox();
        tableBox.setSpacing(5);
        tableBox.setPadding(new Insets(10, 0, 0, 10));

        final VBox controlBox = new VBox();
        controlBox.getChildren().addAll(deleteGroupButton, deleteTeacherButton, modifyGroupButton);

        tableBox.getChildren().addAll(new VBox(groupTable, addGroupButton, groupsTextFilter), new VBox(teacherTable, addTeacherButton, teachersTextFilter), controlBox);

        ((Group) scene.getRoot()).getChildren().addAll(tableBox);

        stage.setScene(scene);
        stage.show();
    }

    public class Touple4<L, M, R, P> {

        private final L U;
        private final M V;
        private final R W;
        private final P X;

        public Touple4(L left, M middle, R right, P right_right) {
            this.U = left;
            this.V = middle;
            this.W = right;
            this.X = right_right;
        }

        public L getLeft() {
            return U;
        }

        public M getV() {
            return V;
        }

        public R getW() {
            return W;
        }

        public P getX() {
            return X;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}