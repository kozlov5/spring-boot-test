package ui.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import service.test.OwnerService;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by Alexander on 25.08.2017.
 */
@UIScope
@SpringView(name = OwnerView.VIEW_NAME)
public class OwnerView extends MVerticalLayout implements View {

    public static final String VIEW_NAME = "owner";

    @Autowired
    private OwnerService ownerService;

    private final BeanFieldGroup<Owner> fieldGroup = new BeanFieldGroup<>(Owner.class);

    @PropertyId(value = "firstName")
    private final MTextField firstName = new MTextField("Имя").withWidth(150, Unit.PIXELS);
    @PropertyId(value = "lastName")
    private final MTextField lastName = new MTextField("Фамилия").withWidth(150, Unit.PIXELS);

    private final MButton save = new MButton("Сохранить");
    private final MButton cancel = new MButton("Отмена");

    @PostConstruct
    public void init() {
        withFullHeight();
        withFullHeight();
        add(new Header("Owner page").setHeaderLevel(2));

        save.addClickListener(event -> {
            try {
                fieldGroup.commit();
                Owner owner = fieldGroup.getItemDataSource().getBean();
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
        add(fields, buttons);
        setExpandRatio(fields, 0.8f);
        setExpandRatio(buttons, 0.2f);
    }

    public void build(@NotNull Owner owner) {
        fieldGroup.setItemDataSource(owner);
        fieldGroup.buildAndBindMemberFields(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
