package configuration.browser;

import configuration.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserProperties {
    Logger logger = LoggerFactory.getLogger(BrowserProperties.class);

    public BrowserProperties() {

    }
    public Browser getActiveBrowser(){
        Browser browser = Browser.CHROME;
        try{
            browser = new YamlReader().getConfig().getBrowsers().getActiveBrowser();
        }catch (NullPointerException e){
            logger.info("No driver was specified. Running test on default browser: {}",browser);
            return browser;
        }
        return browser;

    }
}
