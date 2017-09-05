package ui.view;

import com.vaadin.data.fieldgroup.*;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import entity.Car;
import entity.Details;
import enums.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.ListContainer;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import service.test.CarService;
import ui.view.windows.DetailWindow;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.EnumSet;

/**
 * Created by Vladimir on 25.08.2017.
 */
@UIScope
@SpringView(name = CarView.VIEW_NAME)
public class CarView extends MVerticalLayout implements View {

	public static final String VIEW_NAME = "car";

	@Autowired
	private CarService carService;
	@Autowired
	private DetailWindow detailWindow;

	private Car car;

	@PropertyId("name")
	private final MTextField nameField = new MTextField("Название автомобиля").withWidth(200, Unit.PIXELS);
	@PropertyId("model")
	private final MTextField modelField = new MTextField("Модель автомобиля").withWidth(200, Unit.PIXELS);
	@PropertyId("status")
	private final ComboBox carStatus = new ComboBox("Статус автомобиля");

	private final MGrid<Details> detailsGrid = new MGrid<>(Details.class);

	private final BeanFieldGroup<Car> fieldGroup = new BeanFieldGroup<>(Car.class);

	private final MButton save = new MButton("Сохранить");
	private final MButton cancel = new MButton("Отмена");

	@PostConstruct
	public void init() {
		withFullWidth();
//		withFullHeight();
		add(new Header("Car page").setHeaderLevel(2));
		initDetailsGrid();

		carStatus.addItems(CarStatus.values());
		carStatus.setNullSelectionAllowed(false);
		carStatus.setNewItemsAllowed(false);

		save.addClickListener(event -> {
			try {
				fieldGroup.commit();
				Car car = fieldGroup.getItemDataSource().getBean();
				carService.edit(car);
				getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
			} catch (FieldGroup.CommitException e) {
				e.printStackTrace();
			}
		});
		cancel.addClickListener(event -> {
			getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
		});

		final MVerticalLayout fieldLayout = new MVerticalLayout(nameField, modelField, carStatus, detailsGrid).withMargin(true).withFullHeight().withFullWidth();

		final MHorizontalLayout buttonLayout = new MHorizontalLayout(save, cancel).withMargin(true).withFullWidth();

		add(fieldLayout, buttonLayout);
		setExpandRatio(fieldLayout, 0.8f);
		setExpandRatio(buttonLayout, 0.2f);
	}

	public void initDetailsGrid() {
	    detailsGrid.setCaption("Детали автомобиля");
	    detailsGrid.setContainerDataSource(new ListContainer<>(Details.class));
	    detailsGrid.getColumn("text").setHeaderCaption("Текст");
		detailsGrid.addItemClickListener(event -> {
			if (event.isDoubleClick()) {
				UI.getCurrent().removeWindow(detailWindow);
				UI.getCurrent().addWindow(detailWindow);
			}
		});
    }

    private void refresh() {
	    detailsGrid.setRows(car.getDetails());
    }

	public void build(@NotNull Car car) {
	    this.car = car;
		fieldGroup.setItemDataSource(car);
		fieldGroup.buildAndBindMemberFields(this);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
        refresh();
	}
}
