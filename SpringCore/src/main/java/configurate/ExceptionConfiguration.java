package configurate;

import exceptions.InvalidInputFormat;
import exceptions.NotPointFound;
import exceptions.RowTooLargeExeption;
import exceptions.XmlFileNotValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:exceptMessages.properties")
public class ExceptionConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope("prototype")
    public InvalidInputFormat invalidInputFormat() {
        return new InvalidInputFormat(env.getProperty("exception.invalidInputFormat"));
    }

    @Bean
    @Scope("prototype")
    public NotPointFound notPointFound() {
        return new NotPointFound(env.getProperty("exception.notPointFound"));
    }

    @Bean
    @Scope("prototype")
    public RowTooLargeExeption rowTooLargeExeption() {
        return new RowTooLargeExeption(env.getProperty("exception.rowTooLarge"));
    }

    @Bean
    @Scope("prototype")
    public XmlFileNotValid xmlFileNotValid() {
        return new XmlFileNotValid(env.getProperty("exception.xmlFileNotValid"));
    }


}
