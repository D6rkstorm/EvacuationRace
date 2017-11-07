import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;

import java.util.ArrayList;

import static spark.Spark.*;
import static spark.route.HttpMethod.get;



public class Main {
    public static Map m; //declared outside to be global?

    private static User current = new User();
    private static ArrayList chat = new ArrayList();

    private static void adder(String user, String message) { //Method with User ID and Name
        String totalMessage = user + ": " + message;
        chat.add(totalMessage);
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Spark.staticFileLocation("/FrontEnd");

        post("/chat/createMessage", (request, response) -> {
            logger.info("GET request to /chat/createMessage");
            String[] parts = request.body().split("\"");
            String user = parts[3];
            String message = parts[7];
            adder(user, message);
            System.out.println(chat.get(chat.size() -1));
            String convertedString = chat.toString();
            return convertedString;
        });

        post("/trade/createRequest", (request, response) -> {
            System.out.println(request.body());
            return "";
        });

        Spark.get("/map/getBoard", (req, res) -> {
            logger.info("GET request to /map/getBoard");
            int[][] map;
        //    if(m == null) { //if map has not been initialized, this initializes the map
                m = new Map(0, 0); //map initialized here if not initialized previously
        //    }
            map = m.getMainMap();

            for (int i = 0; i < map[0].length; i++) {   //prints out map values
                for (int j = 0; j < map.length; j++) {
                    System.out.print(map[j][i] + "\t");
                }
                System.out.println("");
            }

            return m;
        }, new JsonUtil());
    }


    public static void login(current){

    }
}
