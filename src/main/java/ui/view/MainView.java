package ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by Vladimir on 25.08.2017.
 */
@UIScope
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends MVerticalLayout implements View {

	public static final String VIEW_NAME = "main";

	@PostConstruct
	public void init() {
		withFullWidth();
		withFullHeight();
		add(new Header("wegwe").setHeaderLevel(2));
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {

	}
}
