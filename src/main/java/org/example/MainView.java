package org.example;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
<<<<<<< HEAD
import com.vaadin.flow.router.Route;
import org.example.Entity.EmployeeInformations;

import java.util.Vector;

=======
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.example.entity.EmployeeInformations;

import java.util.ArrayList;
import java.util.List;

>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5
@Route
public class MainView extends VerticalLayout {

    // Init with test employee
<<<<<<< HEAD
    static Vector<EmployeeInformations> dataProvider = testData();

    private TextField phone = new TextField("Phone");

    Binder<EmployeeInformations> binder = new Binder<>(EmployeeInformations.class);
    Grid<EmployeeInformations> grid = new Grid<>();
    Crud<EmployeeInformations> crud = new Crud<>(EmployeeInformations.class, createGrid(grid), createCrudEditor());


    public MainView() {

        CrudI18n customI18n = CrudI18n.createDefault();
        customI18n.setEditItem("Update Customer");
        customI18n.setNewItem("New Customer");

        grid.setItems(dataProvider);

        //LISTENERS

        crud.addNewListener(personInformationsNewEvent -> {
            binder.forField(phone).withValidator(v -> isPhoneUnique(dataProvider,v),"Phone Must Be Unique").bind("phone");
        });

        crud.addSaveListener(saveEvent -> {
            EmployeeInformations toSave = saveEvent.getItem();
            // Save the item to memory

            binder.setValidatorsDisabled(true);
         if(dataProvider.stream().anyMatch(x -> x.getPhone().equals(saveEvent.getItem().getPhone())))
             Notification.show("Phone Must Be Unique",2233, Notification.Position.TOP_CENTER);
         else if (dataProvider != null && !dataProvider.contains(toSave))
                dataProvider.add(toSave);
=======
    static ListDataProvider<EmployeeInformations> dataProvider = testData();

    Grid<EmployeeInformations> grid = new Grid<>();

    public MainView() {

        Crud<EmployeeInformations> crud = new Crud<>(EmployeeInformations.class, createGrid(), createCrudEditor());

        CrudI18n customI18n = CrudI18n.createDefault();
        customI18n.setEditItem("Update Customer");
        customI18n.setNewItem("New Customer");

        crud.setDataProvider(dataProvider);
        crud.setI18n(customI18n);

        crud.addSaveListener(saveEvent -> {
            EmployeeInformations toSave = saveEvent.getItem();
            // Save the item to memory
            if (dataProvider != null)
                dataProvider.getItems().add(toSave);
            else if (!dataProvider.getItems().contains(toSave))
                dataProvider.getItems().add(toSave);
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5
        });

        crud.addDeleteListener(deleteEvent -> {
            // Delete the item in the database
<<<<<<< HEAD
            dataProvider.remove(deleteEvent.getItem());
        });

=======
            dataProvider.getItems().remove(deleteEvent.getItem());
        });

        add(crud);

    }
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5

        add(crud);

<<<<<<< HEAD
    }

    private static Vector<EmployeeInformations> testData() {

        Vector<EmployeeInformations> testData = new Vector<>();
        Integer phone = 1;
=======
        List<EmployeeInformations> testData = new ArrayList<>();

        testData.add(new EmployeeInformations("Berat", "ITU", "Istanbul", "sds@gmail.com", "Turkey", "1321312"));
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5

        for ( int x =0; x < 10000000; x++) {
            testData.add(new EmployeeInformations("Berat", "ITU", "Istanbul", "Turkey", phone.toString(), "1321312"));
            phone++;
            testData.add(new EmployeeInformations("Serdan", "ITU", "Istanbul", "Turkey", phone.toString(), "1321312"));
            phone++;
        }
        return testData;

    }

    private CrudEditor<EmployeeInformations> createCrudEditor() {
<<<<<<< HEAD

        Binder<EmployeeInformations> binder = new Binder<>(EmployeeInformations.class);
=======
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5

        TextField name = new TextField("Name");
        name.setRequiredIndicatorVisible(true);
        TextField street = new TextField("Street");
        TextField city = new TextField("City");
        TextField country = new TextField("Country");
        TextField email = new TextField("E-Mail");


        phone.setRequiredIndicatorVisible(true);

        FormLayout form = new FormLayout(name, street, city, email, country, phone);

        binder.bind(name, EmployeeInformations::getName, EmployeeInformations::setName);
        binder.forField(name).asRequired("required!").bind("name");
        binder.bind(street, EmployeeInformations::getStreet, EmployeeInformations::setStreet);
        binder.bind(city, EmployeeInformations::getCity, EmployeeInformations::setCity);
        binder.bind(email, EmployeeInformations::getEmail, EmployeeInformations::setEmail);
        binder.bind(country, EmployeeInformations::getCountry, EmployeeInformations::setCountry);
        binder.bind(phone, EmployeeInformations::getPhone, EmployeeInformations::setPhone);
        binder.forField(phone).asRequired("required!").bind("phone");

        return new BinderCrudEditor<>(binder, form);
    }

<<<<<<< HEAD
    private Grid<EmployeeInformations> createGrid(Grid<EmployeeInformations> grid) {

        grid.addColumn(EmployeeInformations::getName).setHeader("Name").setKey("Name")
                .setWidth("160px").setComparator(EmployeeInformations::getName);
        grid.addColumn(EmployeeInformations::getPhone).setHeader("Phone").setKey("Phone");

        configureFilter(grid, dataProvider);

        Crud.addEditColumn(grid);

        return grid;
    }

    public  void configureFilter(Grid<EmployeeInformations> grid, Vector<EmployeeInformations> dataProvider) {

        HeaderRow filterRow = grid.appendHeaderRow();

         TextField nameFilter = new TextField();
         TextField phoneFilter = new TextField();
=======
    private Grid<EmployeeInformations> createGrid() {

        grid.addColumn(EmployeeInformations::getName).setHeader("Name").setKey("Name")
                .setWidth("160px").setComparator(EmployeeInformations::getName);
        grid.addColumn(EmployeeInformations::getStreet).setHeader("Street").setKey("Street");
        grid.addColumn(EmployeeInformations::getCity).setHeader("City").setKey("City");
        grid.addColumn(EmployeeInformations::getCountry).setHeader("Country").setKey("Country");
        grid.addColumn(EmployeeInformations::getEmail).setHeader("Email").setKey("Email");
        grid.addColumn(EmployeeInformations::getPhone).setHeader("Phone").setKey("Phone");

        configureFilter();

        Crud.addEditColumn(grid);


        return grid;
    }

    private void configureFilter() {

        HeaderRow filterRow = grid.appendHeaderRow();


        //Name Filter
        TextField nameFilter = new TextField();
        nameFilter.setSizeFull();
        nameFilter.setPlaceholder("Filter");
        nameFilter.getElement().setAttribute("focus-target", "");
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5

        //Filtering
        nameFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

<<<<<<< HEAD
        //Name Filter

        nameFilter.setSizeFull();
        nameFilter.setPlaceholder("Filter");
        nameFilter.setClearButtonVisible(true);
        nameFilter.getElement().setAttribute("focus-target", "");


        //Phone Filter

=======
        //Street Filter
        TextField streetFilter = new TextField();
        streetFilter.setSizeFull();
        streetFilter.setPlaceholder("Filter");
        streetFilter.getElement().setAttribute("focus-target", "");

        streetFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

        //City Filter
        TextField cityFilter = new TextField();
        cityFilter.setSizeFull();
        cityFilter.setPlaceholder("Filter");
        cityFilter.getElement().setAttribute("focus-target", "");

        cityFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

        //Country Filter
        TextField countryFilter = new TextField();
        countryFilter.setSizeFull();
        countryFilter.setPlaceholder("Filter");
        countryFilter.getElement().setAttribute("focus-target", "");

        countryFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

        //Email Filter
        TextField emailFilter = new TextField();
        emailFilter.setSizeFull();
        emailFilter.setPlaceholder("Filter");
        emailFilter.getElement().setAttribute("focus-target", "");

        emailFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

        //Phone Filter
        TextField phoneFilter = new TextField();
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5
        phoneFilter.setSizeFull();
        phoneFilter.setPlaceholder("Filter");
        phoneFilter.setClearButtonVisible(true);
        phoneFilter.getElement().setAttribute("focus-target", "");

<<<<<<< HEAD
=======
        phoneFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5
        //Adding filters to grid
        filterRow.getCell(grid.getColumnByKey("Name")).setComponent(nameFilter);
        filterRow.getCell(grid.getColumnByKey("Phone")).setComponent(phoneFilter);

        nameFilter.addValueChangeListener(e -> filterColumns(grid,dataProvider,nameFilter,phoneFilter));
        phoneFilter.addValueChangeListener(e -> filterColumns(grid,dataProvider,nameFilter,phoneFilter));

<<<<<<< HEAD
    }

    private  void filterColumns(Grid<EmployeeInformations> grid, Vector<EmployeeInformations> dataProvider, TextField nameFilter, TextField phoneFilter) {

        String q1 = nameFilter.getValue();
        String q2 = phoneFilter.getValue();

        if (!q1.equals("") && q2.equals(""))
            grid.setItems(dataProvider.stream().filter(c -> c.getName().equalsIgnoreCase(nameFilter.getValue())));

        if (q1.equals("") && !q2.equals(""))
            grid.setItems(dataProvider.stream().filter(c -> c.getPhone().equalsIgnoreCase(phoneFilter.getValue())));

        if (!q1.equals("") && !q2.equals(""))
            grid.setItems(dataProvider.stream().filter(c -> c.getName().equalsIgnoreCase(nameFilter.getValue()) && c.getPhone().equalsIgnoreCase(phoneFilter.getValue())));

        if (q1.equals("") && q2.equals("")) {
            grid.setItems(dataProvider);
        }
    }

    public static boolean isPhoneUnique(Vector<EmployeeInformations> dataProvider, String v) {
        return  !dataProvider.stream().anyMatch(x -> x.getPhone().equals(v));
    }

}
=======
    }


}
>>>>>>> f690c758c0bbe1bf791b67227bb76d570ff333b5
