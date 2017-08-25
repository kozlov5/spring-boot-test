package ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import entity.Car;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.ListContainer;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import service.test.CarService;
import service.test.OwnerService;

import javax.annotation.PostConstruct;

/**
 * Created by Vladimir on 25.08.2017.
 */
@UIScope
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends MVerticalLayout implements View {

	public static final String VIEW_NAME = "main";

	@Autowired
	private CarService carService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private CarView carView;
	@Autowired
	private OwnerView ownerView;

//	private final MButton addCar = new MButton("Добавить");

	/**
	 * Список автомобилей
	 */
	private MGrid<Car> carGrid = new MGrid<>(Car.class);

	/**
	 * Список владельцев
	 */
	private MGrid<Owner> ownerGrid = new MGrid<>(Owner.class);

	@PostConstruct
	public void init() {
		withFullWidth();
		withFullHeight();
		add(new Header("Main page").setHeaderLevel(2));
		initCarGrid();
		initOwnerGrid();


		final MHorizontalLayout gridLayout = new MHorizontalLayout().withFullHeight().withFullWidth();
		gridLayout.add(carGrid, ownerGrid);

		add(gridLayout);
		setExpandRatio(gridLayout, 1f);
	}

	private void initCarGrid() {
		carGrid.setSizeFull();
		carGrid.setCaption("Автомобили");
		carGrid.setContainerDataSource(new ListContainer<>(Car.class));
		carGrid.withProperties("name", "model");
		carGrid.getColumn("name")
				.setHeaderCaption("Название");
		carGrid.getColumn("model")
				.setHeaderCaption("Модель")
				.setHidable(true)
				.setHidden(true);

		carGrid.addItemClickListener(event -> {
			if (event.isDoubleClick()) {
				carView.build((Car) event.getItemId());
				getUI().getNavigator().navigateTo(CarView.VIEW_NAME);
			}
		});
	}

	private void initOwnerGrid() {
		ownerGrid.setSizeFull();
		ownerGrid.setCaption("Владельцы");
		ownerGrid.setContainerDataSource(new ListContainer<>(Owner.class));
		ownerGrid.withProperties("lastName", "firstName");
		ownerGrid.getColumn("firstName")
				.setHeaderCaption("Имя");
		ownerGrid.getColumn("lastName")
				.setHeaderCaption("Фамилия");

		ownerGrid.addItemClickListener(event -> {
			if (event.isDoubleClick()) {
				ownerView.build((Owner) event.getItemId());
				getUI().getNavigator().navigateTo(OwnerView.VIEW_NAME);
			}
		});
	}

	private void refresh() {
		carGrid.setRows(carService.getCars());
		ownerGrid.setRows(ownerService.getOwners());
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		refresh();
	}
}
