package ua.nure.usik.SummaryTask4.request;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * An extension for the HTTPServletRequest that overrides the getUserPrincipal()
 * and isUserInRole(). We supply these implementations here, where they are not
 * normally populated unless we are going through the facility provided by the
 * container.
 * <p>
 * If he user or roles are null on this wrapper, the parent request is consulted
 * to try to fetch what ever the container has set for us. This is intended to
 * be created and used by the UserRoleFilter.
 *
 * @author N.Usik
 *
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String email;
    private String role;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String email, String role, HttpServletRequest request) {
        super(request);
        this.email = email;
        this.role = role;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (this.role == null) {
            return this.realRequest.isUserInRole(role);
        }
        return this.role.equals(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.email == null) {
            return realRequest.getUserPrincipal();
        }

        // Make an anonymous implementation to just return our user
        return new Principal() {

            @Override
            public String getName() {
                return email;
            }
        };
    }
}