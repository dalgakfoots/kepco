package egovframework.otl.message.web;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


@ServerEndpoint("/trainMessageReceiver")
public class TrainMessageReceiver {
    // 유저용 Endpoint

    private class User {
        Session session;
        String key;
    }

    private interface SearchExpression{
        boolean expression(User user);
    }

    private static List<User> sessionUsers = Collections.synchronizedList(new ArrayList<User>());

    // 접속 리스트 탐색 함수
    private static User searchUser(SearchExpression func) {

        Optional<User> op = sessionUsers.stream().filter(x -> func.expression(x)).findFirst();
        // 결과가 있으면
        if (op.isPresent()) {
            // 결과를 리턴
            return op.get();
        }
        // 없으면 null 처리
        return null;
    }

    // Session으로 접속 리스트에서 User 클래스를 탐색
    private static User getUser(Session session) {
        return searchUser(x -> x.session == session);
    }

    // Key로 접속 리스트에서 User 클래스를 탐색
    private static User getUser(String key) {
        return searchUser(x -> x.key.equals(key));
    }

    // 운영자 client가 유저에게 메시지를 보내는 함수
    public static void sendMessage(String key, String message) {
        // key로 접속 리스트에서 ChattingUser 클래스를 탐색
        User user = getUser(key);
        // 접속 리스트에 User가 있으면(당연히 있다. 없으면 버그..)
        if (user != null) {
            try {
                // 유저 Session으로 socket을 취득한 후 메시지를 전송한다.
                user.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessage(String message) {
        for(User user : sessionUsers) {
            if (user != null) {
                try {
                    // 유저 Session으로 socket을 취득한 후 메시지를 전송한다.
                    user.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 유저간의 접속 리스트의 키를 취득하려고 할때.
    public static String[] getUserKeys() {
        // 반환할 String 배열을 선언한다.
        String[] ret = new String[sessionUsers.size()];
        // 유저 리스트를 반복문에 돌린다.
        for (int i = 0; i < ret.length; i++) {
            // 유저의 키만 반환 변수에 넣는다.
            ret[i] = sessionUsers.get(i).key;
        }
        // 값 반환
        return ret;
    }

    @OnOpen
    public void handleOpen(Session session) {
        User user = new User();
        user.key = UUID.randomUUID().toString().replace("-", "");
        user.session = session;
        sessionUsers.add(user);
    }

    @OnClose
    public void handleClose(Session userSession) {
        // Session으로 접속 리스트에서 User 클래스를 탐색
        User user = getUser(userSession);
        // 접속 리스트에 User가 있으면(당연히 있다. 없으면 버그..)
        if (user != null) {
            // 위 유저 접속 리스트에서 유저를 삭제한다.
            sessionUsers.remove(user);
        }
    }

    @OnError
    public void handleError(Throwable t){
        t.printStackTrace();
    }


}
