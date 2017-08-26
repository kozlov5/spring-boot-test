package ui.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import entity.Car;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.ListContainer;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import service.test.CarService;
import service.test.OwnerService;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 25.08.2017.
 */
@UIScope
@SpringView(name = OwnerView.VIEW_NAME)
public class OwnerView extends MVerticalLayout implements View {

    public static final String VIEW_NAME = "owner";

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private CarService carService;

    private Owner owner = new Owner();

    private final BeanFieldGroup<Owner> fieldGroup = new BeanFieldGroup<>(Owner.class);

    @PropertyId(value = "firstName")
    private final MTextField firstName = new MTextField("Имя").withWidth(150, Unit.PIXELS);
    @PropertyId(value = "lastName")
    private final MTextField lastName = new MTextField("Фамилия").withWidth(150, Unit.PIXELS);

    private final MButton save = new MButton("Сохранить");
    private final MButton cancel = new MButton("Отмена");

    /**
     * Список автомобилей не принадлежащих текущему владельцу
     */
    private MGrid<Car> carGrid = new MGrid<>(Car.class);

    /**
     * Список автомобилей владельца
     */
    private MGrid<Car> ownerCarsGrid = new MGrid<>(Car.class);

    @PostConstruct
    public void init() {
        withFullWidth();
//        withFullHeight();
        add(new Header("Owner page").setHeaderLevel(2));
        initCarGrid();
        initOwnerCarsGrid();

        save.addClickListener(event -> {
            try {
                fieldGroup.commit();
                Owner owner = fieldGroup.getItemDataSource().getBean();
                carGrid.getSelectedRowsWithType().forEach(car -> {
                    car.setOwner(owner);
                    carService.edit(car);
                });
                carGrid.getSelectionModel().reset();
                ownerService.edit(owner);
                getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
        });

        cancel.addClickListener(event -> {
            getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
        });

        final MVerticalLayout fields = new MVerticalLayout(firstName, lastName);
        final MHorizontalLayout buttons = new MHorizontalLayout(save, cancel);
        final MVerticalLayout owner = new MVerticalLayout(fields, buttons);
        final MHorizontalLayout top = new MHorizontalLayout(owner, ownerCarsGrid);
        owner.setExpandRatio(fields, 0.8f);
        owner.setExpandRatio(buttons, 0.2f);
        add(top, carGrid);
    }

    private void initCarGrid() {
        carGrid.setCaption("Автомобили");
        carGrid.setContainerDataSource(new ListContainer<>(Car.class));
        carGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        carGrid.withProperties("name", "model");
        carGrid.getColumn("name").setHeaderCaption("Название");
        carGrid.getColumn("model").setHeaderCaption("Модель");
    }

    private void initOwnerCarsGrid() {
        ownerCarsGrid.setCaption("Автомобили владельца");
        ownerCarsGrid.setContainerDataSource(new ListContainer<>(Car.class));
        ownerCarsGrid.setSelectionMode(Grid.SelectionMode.NONE);
        ownerCarsGrid.withProperties("name", "model");
        ownerCarsGrid.getColumn("name").setHeaderCaption("Название");
        ownerCarsGrid.getColumn("model").setHeaderCaption("Модель");
    }

    private void refresh() {
        carGrid.setRows(carService.getNoOwnerCars(owner.getId()));
        ownerCarsGrid.setRows(carService.getByOwnerId(owner.getId()));
    }

    public void build(@NotNull Owner owner) {
        this.owner = owner;
        fieldGroup.setItemDataSource(owner);
        fieldGroup.buildAndBindMemberFields(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refresh();
    }
}
