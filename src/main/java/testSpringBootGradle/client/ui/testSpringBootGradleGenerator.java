package testSpringBootGradle.client.ui;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.Connect.LoadStyle;

public class testSpringBootGradleGenerator extends
        ConnectorBundleLoaderFactory {

    private static final List<Class> eagerComponents = new LinkedList<Class>();
    static {
       
        // Add your eager components here...
        // eagerComponents.add(VerticalLayout.class)

    }

    private static final List<Class> deferredComponents = new LinkedList<Class>();
    static {

        // Add your deferred components here...
        // deferredComponents.add(VerticalLayout.class)
    }    

    private static final List<Class> lazyComponents = new LinkedList<Class>();
    static {

        // Add your lazy components here...
        // lazyComponents.add(VerticalLayout.class)
    }    

    @Override
    protected LoadStyle getLoadStyle(JClassType connectorType) {
        Connect annotation = connectorType.getAnnotation(Connect.class);
        Class componentClass = annotation.value();

        if (eagerComponents.contains(componentClass)) {
            return LoadStyle.EAGER;

        } else if(deferredComponents.contains(componentClass)) {
            return LoadStyle.DEFERRED;

        } else if(lazyComponents.contains(componentClass)) {
            return LoadStyle.LAZY;
        } 

        return super.getLoadStyle(connectorType);
    }
}
