import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        String urlPath = req.getPathInfo();

        if (urlPath == null || urlPath.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("not found url path");
            return;
        }

        String[] urlParts = urlPath.split("/");
//        for (int i = 0; i < urlParts.length; i++) {
//            System.out.println(urlParts[i]);
//        }

        BufferedReader content = req.getReader();
        String returnString = ReadBigStringIn(content);
//        System.out.println(returnString);

        if (!isUrlValid(urlParts)) {
            res.setStatus((HttpServletResponse.SC_NOT_FOUND));
            res.getWriter().write("url not valid");
        } else {
            res.setStatus((HttpServletResponse.SC_CREATED));
            res.getWriter().write("It works" + returnString);
            System.out.println("success");
        }
    }

    private boolean isUrlValid(String[] urlPath) {
//        "", "left"
        if (    urlPath[1].equals("swipe") &&
                (urlPath[2].equals("left") || urlPath[2].equals("right"))) {
            return true;
        }
        return false;
    }
    public String ReadBigStringIn(BufferedReader buffIn) throws IOException {
        StringBuilder everything = new StringBuilder();
        String line;
        while( (line = buffIn.readLine()) != null) {
            everything.append(line);
        }
        return everything.toString();
    }
}
