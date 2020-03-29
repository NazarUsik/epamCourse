package ua.nure.usik.SummaryTask4.filter;

import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.User;
import ua.nure.usik.SummaryTask4.db.entity.enums.Role;
import ua.nure.usik.SummaryTask4.request.UserRoleRequestWrapper;
import ua.nure.usik.SummaryTask4.utils.SecurityUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        User loginedUser = MyUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String email = loginedUser.getEmail();

            // Роль (Role).
            String role = String.valueOf(Role.getRole(loginedUser));

            // Старый пакет request с помощью нового Request с информацией email и Role.
            wrapRequest = new UserRoleRequestWrapper(email, role, request);
        }

        // Страницы требующие входа в систему.
        if (SecurityUtils.isSecurityPage(request)) {

            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
                int redirectId = MyUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                wrapRequest.getSession().setAttribute("redirectId", redirectId);

                response.sendRedirect(wrapRequest.getContextPath() + "/login");

                return;
            }

            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}