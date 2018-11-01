package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UtilsJsp {

    public static void forwardToJsp(String jsp, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        rd = req.getRequestDispatcher(jsp);
        rd.forward(req, resp);
    }

    public static void forwardToErrorPage(
            HttpServletRequest req,
            HttpServletResponse resp,
            String title,
            String subtitle,
            String headerError,
            String bodyError) throws ServletException, IOException {
        req.setAttribute("title", title);
        req.setAttribute("subtitle", subtitle);
        req.setAttribute("headerError", headerError);
        req.setAttribute("bodyError", bodyError);
        UtilsJsp.forwardToJsp("/jsp/errorPage.jsp", req, resp);
    }

}
