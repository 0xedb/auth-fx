package little.secrets;

import com.sun.javafx.webkit.WebConsoleListener;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author bruno
 */
public class MainController implements Initializable {

    @FXML
    private WebView browser;

    private WebEngine webEngine;
    @FXML
    private Pane secrets;
    @FXML
    private Text hidden_secret;
    
    
    // JavaScript interface object
    public class JavaApp {

        public void exit() {
            secrets.setVisible(true);
            hidden_secret.setText(webEngine.getUserAgent() + "\n" + webEngine.getHistory().toString()); 
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        webEngine = browser.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> ov, State oldState,
                        State newState) -> {
                    if (newState == State.SUCCEEDED) {
                        JSObject win = (JSObject) webEngine.executeScript("window");
                        win.setMember("app", new JavaApp());
                    }
                });

        webEngine.load("https://nifty-roentgen-5414c4.netlify.com/");               //***************variable part

    }
 

}

 
