package ta105;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "encodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
        // Annotation-only mode: keep UTF-8 as fixed default.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding(DEFAULT_ENCODING);
        resp.setCharacterEncoding(DEFAULT_ENCODING);
        chain.doFilter(req, resp);
    }
}
