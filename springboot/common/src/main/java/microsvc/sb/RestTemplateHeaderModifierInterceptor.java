package microsvc.sb;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static microsvc.sb.ServiceConfig.HTTP_HEADER_CORRELATION_ID;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String corrId = MDC.get(HTTP_HEADER_CORRELATION_ID);
        request.getHeaders().add(ServiceConfig.HTTP_HEADER_CORRELATION_ID, corrId != null ? corrId : "N/A");
        return execution.execute(request, body);
    }
}
