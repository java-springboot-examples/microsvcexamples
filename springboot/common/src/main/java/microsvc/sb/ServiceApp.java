package microsvc.sb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public abstract class ServiceApp implements WebMvcConfigurer {

    @Autowired
    private ServiceHandlerInterceptor serviceHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceHandlerInterceptor);
    }
}
