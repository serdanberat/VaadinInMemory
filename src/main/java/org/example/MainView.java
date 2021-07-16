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
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.example.entity.EmployeeInformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Route
public class MainView extends VerticalLayout {

    static ListDataProvider<EmployeeInformations> dataProvider = testData(); /**Add Employee**/

public MainView(){

      Crud<EmployeeInformations> crud = new Crud<>(EmployeeInformations.class, createGrid(), createEmployeeEditor());

    CrudI18n customI18n = CrudI18n.createDefault();
    customI18n.setEditItem("Update Customer");
    customI18n.setNewItem("New Customer");

    crud.setDataProvider(dataProvider);
    crud.setI18n(customI18n);

    crud.addSaveListener(saveEvent -> {
        EmployeeInformations toSave = saveEvent.getItem();
        // Save the item in the database
        if(dataProvider==null)
            dataProvider.getItems().add(toSave);
        else if(!dataProvider.getItems().contains(toSave))
            dataProvider.getItems().add(toSave);
});

    crud.addDeleteListener(deleteEvent -> {
        // Delete the item in the database
        dataProvider.getItems().remove(deleteEvent.getItem());
    });

    add(crud);

}

    private static ListDataProvider<EmployeeInformations> testData() {

        List<EmployeeInformations> testData= new ArrayList<>();

        testData.add(new EmployeeInformations("Berat","ITU","Istanbul","sds@gmail.com","Turkey","1321312"));

        return new ListDataProvider<>(testData);

    }

    private  CrudEditor<EmployeeInformations> createEmployeeEditor() {

        /**Update Edit Form**/

        TextField name = new TextField("Name");
        name.setRequiredIndicatorVisible(true);

        TextField street = new TextField("Street");

        TextField city = new TextField("City");

        TextField country = new TextField("Country");

        TextField phone = new TextField("Phone");

        TextField email = new TextField("E-Mail");

        FormLayout form = new FormLayout(name, street,city,email,country,phone);

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

    private  Grid<EmployeeInformations> createGrid() {

        Grid<EmployeeInformations> grid = new Grid<>();

        /**Grid Columns**/

        grid.addColumn(c -> c.getName()).setHeader("Name").setKey("Name")
                .setWidth("160px").setComparator(EmployeeInformations::getName);
        grid.addColumn(c -> c.getStreet()).setHeader("Street").setKey("Street");
        grid.addColumn(c -> c.getCity()).setHeader("City").setKey("City");
        grid.addColumn(c -> c.getCountry()).setHeader("Country").setKey("Country");
        grid.addColumn(c -> c.getEmail()).setHeader("Email").setKey("Email");
        grid.addColumn(c -> c.getPhone()).setHeader("Phone").setKey("Phone");

        /**Filter Rows**/
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField nameFilter= new TextField();
        nameFilter.setSizeFull();
        nameFilter.setPlaceholder("Filter");
        nameFilter.getElement().setAttribute("focus-target", "");


        TextField streetFilter= new TextField();
        streetFilter.setSizeFull();
        streetFilter.setPlaceholder("Filter");
        streetFilter.getElement().setAttribute("focus-target", "");

        TextField cityFilter= new TextField();
        cityFilter.setSizeFull();
        cityFilter.setPlaceholder("Filter");
        cityFilter.getElement().setAttribute("focus-target", "");

        TextField countryFilter= new TextField();
        countryFilter.setSizeFull();
        countryFilter.setPlaceholder("Filter");
        countryFilter.getElement().setAttribute("focus-target", "");

        TextField emailFilter= new TextField();
        emailFilter.setSizeFull();
        emailFilter.setPlaceholder("Filter");
        emailFilter.getElement().setAttribute("focus-target", "");

        TextField phoneFilter= new TextField();
        phoneFilter.setSizeFull();
        phoneFilter.setPlaceholder("Filter");
        phoneFilter.getElement().setAttribute("focus-target", "");

        filterRow.getCell(grid.getColumnByKey("Name")).setComponent(nameFilter);
        filterRow.getCell(grid.getColumnByKey("Street")).setComponent(streetFilter);
        filterRow.getCell(grid.getColumnByKey("City")).setComponent(cityFilter);
        filterRow.getCell(grid.getColumnByKey("Country")).setComponent(countryFilter);
        filterRow.getCell(grid.getColumnByKey("Email")).setComponent(emailFilter);
        filterRow.getCell(grid.getColumnByKey("Phone")).setComponent(phoneFilter);


        Crud.addEditColumn(grid);


        return grid;
    }

}
