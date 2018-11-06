/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.bbn.openmap.Environment;
import com.bbn.openmap.MapHandler;
import com.bbn.openmap.PropertyHandler;
import com.bbn.openmap.gui.BasicMapPanel;
import com.bbn.openmap.gui.MapPanel;
import com.bbn.openmap.gui.OpenMapFrame;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author JUAN
 */
public class OpenMap {
    /** The main panel of the application. */
    private MapPanel mapPanel;

    /**
     * Schedule creation of this application's GUI in the event-dispatching
     * thread.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OpenMap.create("./openmap.properties").showInFrame();
        });
    }

    /** Passes a null property handler. Use {@code create()} instead. */
    public OpenMap() {
        this((PropertyHandler) null);
    }

    /**
     * Configures the map pane with this property handler.
     *
     * @param propertyHandler
     */
    private OpenMap(PropertyHandler propertyHandler) {
        configureMapPanel(propertyHandler);
    }

    /**
     * Factory method.
     *
     * @param propertiesFile path to {@code openmap.properties}
     * @return new {@code OpenMap} instance
     */
    public static OpenMap create(String propertiesFile) {
        return new OpenMap(configurePropertyHandler(propertiesFile));
    }

    /**
     * Given a path to a properties file, try to configure a
     * {@code PropertyHandler} with it. If the properties file is not valid, the
     * returned {@code PropertyHandler} will look for the
     * {@code openmap.properties} file in the classpath and the user's home
     * directory.
     *
     * @param propertiesFile path to {@code openmap.properties}
     * @return the respective {@code PropertyHandler} or an empty one if an
     * error occurs
     * @throws MalformedURLException, IOException
     */
    private static PropertyHandler configurePropertyHandler(String propertiesFile) {
        try {
            return new PropertyHandler.Builder().setPropertiesFile(propertiesFile).build();
        } catch (MalformedURLException murle) {
            Logger.getLogger(OpenMap.class.getName()).log(Level.WARNING, murle.getMessage(), murle);
        } catch (IOException ioe) {
            Logger.getLogger(OpenMap.class.getName()).log(Level.WARNING, ioe.getMessage(), ioe);
        }
        return new PropertyHandler();
    }

    /** @return the MapHandler */
    public MapHandler getMapHandler() {
        return mapPanel.getMapHandler();
    }

    /**
     * If there is no {@code OpenMapFrame} specified in the properties file, we
     * need to create one and configure it from the current properties.
     */
    public void showInFrame() {
        MapHandler mapHandler = getMapHandler();
        OpenMapFrame omf = (OpenMapFrame) mapHandler.get(OpenMapFrame.class
        );
        if (omf == null) {
            omf = new OpenMapFrame(Environment.get(Environment.Title));
            PropertyHandler propertyHandler = (PropertyHandler) mapHandler.get(PropertyHandler.class
            );
            if (propertyHandler != null) {
                // Use the default property prefix for the default window
                // property settings.
                omf.setProperties("openmap", propertyHandler.getProperties());
            }
            getMapHandler().add(omf);
        }
        omf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        omf.setVisible(true);
    }

    /**
     * Creates the components in the main application thread. If any of these
     * components need to update their GUI, they should pass a {@code Runnable}
     * object to the {@code SwingUtilities.invokeLater(Runnable)} method, and it
     * will be updated accordingly.
     *
     * @param propertyHandler
     */
    private void configureMapPanel(PropertyHandler propertyHandler) {
        BasicMapPanel basicMapPanel = new BasicMapPanel(propertyHandler, true);
        basicMapPanel.create();
        mapPanel = basicMapPanel;
    }
}
