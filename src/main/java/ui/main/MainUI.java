package ui.main;

import com.vaadin.annotations.*;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ui.view.MainView;


@Theme(MainUI.APPLICATION_NAME)
@SpringUI(path = MainUI.APPLICATION_PATH)
@Widgetset(MainUI.APPLICATION_NAME)
@PreserveOnRefresh
@Push
public class MainUI extends UI implements ViewChangeListener {

	public static final String APPLICATION_NAME = "testSpringBootGradle";
	public static final String APPLICATION_PATH = "admin";
	private final SpringViewProvider springViewProvider;
	private final SpringNavigator navigator;

	private final MenuBar adminMainMenu = new MenuBar();
	private final MenuBar managerMainMenu = new MenuBar();

	@Autowired
	public MainUI(SpringViewProvider springViewProvider, SpringNavigator navigator) {
		this.springViewProvider = springViewProvider;
		this.navigator = navigator;
	}

	@Override
	protected void init(final VaadinRequest request) {
		addStyleName(ValoTheme.UI_WITH_MENU);
		Panel display = new Panel();
		display.setSizeFull();
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(false);
		content.addComponents(adminMainMenu, managerMainMenu);
		content.addComponent(display);
		content.setExpandRatio(display, 1f);
		setContent(content);
		navigator.init(this, display);
		navigator.addProvider(springViewProvider);
		getUI().getNavigator().addViewChangeListener(this);
		getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
	}

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		adminMainMenu.setVisible(true);
		managerMainMenu.setVisible(true);
		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {

	}
}
