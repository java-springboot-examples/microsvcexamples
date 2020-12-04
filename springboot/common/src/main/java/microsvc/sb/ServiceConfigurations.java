package microsvc.sb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceConfigurations {

    private List<ServiceConfig> configs;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public List<ServiceConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ServiceConfig> configs) {
        this.configs = configs;
    }

    public ServiceConfig get(String name) {
        return configs.stream().filter(sc -> sc.getName().equals(name)).findFirst().orElse(null);
    }
}

