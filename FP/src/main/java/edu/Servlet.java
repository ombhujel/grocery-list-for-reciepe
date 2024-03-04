package edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class Servlet
 */

public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("name");
        API api = new API();
        try {
            String[][] recipes = api.APII(id);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Recipes</title></head><body>");
            for (String[] recipe : recipes) {
                displayRecipe(out, recipe);
            }
            out.println("</body></html>");
        } catch (IOException | InterruptedException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
    
    private void displayRecipe(PrintWriter out, String[] recipe) {
        String name = recipe[0].replace(" ", "&nbsp;");
        String imgUrl = recipe[6];
        out.println("<h3>" + name + "</h3>");
        out.println("<img src='" + imgUrl + "'><br>");
        out.println("<b>Ingredients:</b><br>");
        for (int i = 1; i < recipe.length - 1; i++) {
            String ingredient = recipe[i].replace(" ", "&nbsp;");
            out.println((i) + ". " + ingredient + "<br>");
        }
        out.println("<form action='add' method='post'>");
        out.println("<input type='text' name='name' value='" + name + "' hidden>");
        for (int i = 1; i < recipe.length - 1; i++) {
            String ingredient = recipe[i].replace(" ", "&nbsp;");
            out.println("<input type='text' name='ing" + i + "' value='" + ingredient + "'>");
        }
        out.println("<input type='submit' value='ADD'></form><br>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

