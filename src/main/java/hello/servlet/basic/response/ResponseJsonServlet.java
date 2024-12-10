package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type : application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setAge(29);
        helloData.setUsername("park");

        //{"username":"park", "age":29}
        String result = mapper.writeValueAsString(helloData);

        response.getWriter().write(result);
    }
    //HTTP 응답으로 JSON을 반환할 때에는 ContentType을 application/json로 지정
    // Jackson 라이브러리가 제공하는 ObjectMapper의 writeValueAsString()을 사용하면 객체를 JSON 문자로 변경 가능.
    /*
    * 참고
    *
    * application/json을 스펙상 utf-8 혁식을 사용하도록 정의됨.
    * 따라서 스펙에서 charset=utf-8과 같은 추가 파라미터를 지원 X
    *
    * response.getWriter()를 사용하면 추가 파라미터를 자동으로 추가한다.
    * 이 떄 getWriter() 대신 response.getOutputStream()으로 출력하면 그런 문제가 없음.
    * */

}
