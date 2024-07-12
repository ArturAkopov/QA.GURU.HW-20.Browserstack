package config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    String user();

    String pass();

}