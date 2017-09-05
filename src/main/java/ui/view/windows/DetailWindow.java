package ui.view.windows;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import entity.Car;
import entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;
import service.test.DetailsService;
import service.test.impl.DetailsServiceImpl;

/**
 * Created by Vladimir on 05.09.2017.
 */
@UIScope
@SpringComponent
public class DetailWindow extends MWindow {

	private final BeanFieldGroup<Details> fieldGroup = new BeanFieldGroup<>(Details.class);

	@PropertyId("text")
	private final MTextField textField = new MTextField("Текст");

	private final MVerticalLayout fields = new MVerticalLayout();

	private final MButton save = new MButton("Сохранить");
	private final MButton cancel = new MButton("Отмена");

	private Car car;

	public void init() {
		setResizable(false);
		setClosable(true);
		setDraggable(false);
		setModal(true);

		fields.add(textField, save, cancel);
		withContent(fields);
		this.setCaption("Детали автомобиля");

		save.addClickListener(event -> {
			try {
				fieldGroup.commit();
				if (fieldGroup.getItemDataSource().getBean().getId() == null) {
					car.getDetails().add(fieldGroup.getItemDataSource().getBean());
				}
//				detailsService.update(details);
			} catch (FieldGroup.CommitException e) {
				e.printStackTrace();
			}
			cancel.click();
		});

		cancel.addClickListener(event -> close());
	}

	public void build(Details details, Car car) {
		this.car = car;
		fieldGroup.setItemDataSource(details);
		fieldGroup.buildAndBindMemberFields(this);
		init();
	}

}
