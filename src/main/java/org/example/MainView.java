package org.example;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.example.entity.EmployeeInformations;

import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    // Init with test employee
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
        });

        crud.addDeleteListener(deleteEvent -> {
            // Delete the item in the database
            dataProvider.getItems().remove(deleteEvent.getItem());
        });

        add(crud);

    }

    private static ListDataProvider<EmployeeInformations> testData() {

        List<EmployeeInformations> testData = new ArrayList<>();

        testData.add(new EmployeeInformations("Berat", "ITU", "Istanbul", "sds@gmail.com", "Turkey", "1321312"));

        return new ListDataProvider<>(testData);

    }

    private CrudEditor<EmployeeInformations> createCrudEditor() {

        TextField name = new TextField("Name");
        name.setRequiredIndicatorVisible(true);

        TextField street = new TextField("Street");

        TextField city = new TextField("City");

        TextField country = new TextField("Country");

        TextField phone = new TextField("Phone");

        TextField email = new TextField("E-Mail");

        FormLayout form = new FormLayout(name, street, city, email, country, phone);

        Binder<EmployeeInformations> binder = new Binder<>(EmployeeInformations.class);
        binder.bind(name, EmployeeInformations::getName, EmployeeInformations::setName);
        binder.forField(name).asRequired("required!").bind("name");
        binder.bind(street, EmployeeInformations::getStreet, EmployeeInformations::setStreet);
        binder.bind(city, EmployeeInformations::getCity, EmployeeInformations::setCity);
        binder.bind(email, EmployeeInformations::getEmail, EmployeeInformations::setEmail);
        binder.bind(country, EmployeeInformations::getCountry, EmployeeInformations::setCountry);
        binder.bind(phone, EmployeeInformations::getPhone, EmployeeInformations::setPhone);
        return new BinderCrudEditor<>(binder, form);
    }

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

        //Filtering
        nameFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

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
        phoneFilter.setSizeFull();
        phoneFilter.setPlaceholder("Filter");
        phoneFilter.getElement().setAttribute("focus-target", "");

        phoneFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(c -> c.getName().equalsIgnoreCase(event.getValue()));
            dataProvider.refreshAll();
        });

        //Adding filters to grid
        filterRow.getCell(grid.getColumnByKey("Name")).setComponent(nameFilter);
        filterRow.getCell(grid.getColumnByKey("Street")).setComponent(streetFilter);
        filterRow.getCell(grid.getColumnByKey("City")).setComponent(cityFilter);
        filterRow.getCell(grid.getColumnByKey("Country")).setComponent(countryFilter);
        filterRow.getCell(grid.getColumnByKey("Email")).setComponent(emailFilter);
        filterRow.getCell(grid.getColumnByKey("Phone")).setComponent(phoneFilter);


    }


}
