/*
 * Copyright © 2026 Sprint S.A.
 * Contact: slawomir.kostrzewa@sprint.pl

 */
package pl.sprint.dialer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author Sławomir Kostrzewa
 */
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static Properties appProps;
    private static boolean serviceInitialized = false;
    
    
    public static String getValue(String key, String defaultValue) {
        String ret = defaultValue; 
        if(appProps == null) {
            log.info("Config appProps is null, return default value{}", defaultValue);
            return defaultValue; 
        }
        
        try {
            ret = appProps.getProperty(key);
            if(ret == null)
                ret = defaultValue; 
        } catch (Exception e) {
            log.error("Config getValue exception message", e);
        }
        
        return ret;
    }
    
    
    public static void reConfigure(String confFileName) {
        serviceInitialized = false;
        configure(confFileName);
    }
    
    public static void configure(String confFileName) {
        log.info("Config configure {}", serviceInitialized);
        if (serviceInitialized)
            return;	
		// init configuration    
               
       appProps = new Properties();
        try {
            appProps.load(Files.newInputStream(Paths.get("config/plugins/" + confFileName)));
        } catch (Exception ex) {
            log.error("Config configure exception", ex);
            appProps = null;
        }
    }
    
   
}
