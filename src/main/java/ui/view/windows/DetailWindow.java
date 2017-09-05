package ui.view.windows;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.vaadin.viritin.layouts.MWindow;

/**
 * Created by Vladimir on 05.09.2017.
 */
@UIScope
@SpringComponent
public class DetailWindow extends MWindow {

	public void init() {
		setResizable(true);
		setClosable(true);
		setModal(false);
		withWidth("50%").withHeight("50%");
	}

}
