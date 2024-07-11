package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:samsungGalaxyS22Ultra.properties"
})
public interface BrowserStackConfig extends Config {

    String browserstackUser();

    String browserstackPass();

    String browserstackApp();

    String browserstackDevice();

    String browserstackPlatform();

    String browserstackProject();

    String browserstackBuild();

    String browserstackName();

    String browserstackUrl();
}
