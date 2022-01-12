package configuration.browser;

import configuration.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserProperties {
    Logger logger = LoggerFactory.getLogger(BrowserProperties.class);

    public BrowserProperties() {

    }


    public Browser getBrowser(){
        try {
            System.getProperty("Browser_Value");
            return getRemoteBrowser();
        } catch (NullPointerException e) {
            return getActiveBrowser();
        }
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

    private Browser getRemoteBrowser() {
        switch (System.getProperty("Browser_Value")) {
            case "chrome":
                logger.info("Browser set remotely: CHROME");
                return Browser.CHROME;
            case "firefox":
                logger.info("Browser set remotely: FIREFOX");
                return Browser.FIREFOX;
            case "edge":
                logger.info("Browser set remotely: EDGE");
                return Browser.EDGE;
            case "IE":
                logger.info("Browser set remotely: IE");
                return Browser.IE;
            default:
                return null;
        }
    }
}
