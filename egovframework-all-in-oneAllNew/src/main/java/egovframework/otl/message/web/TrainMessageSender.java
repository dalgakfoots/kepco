package egovframework.otl.message.web;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/trainMessageSender")
public class TrainMessageSender {

    // 운영자 유저는 하나라고 가정한다. 둘 이상 세션에서 접속하면 마지막 세션만 작동한다.
    private static Session admin = null;

    // 운영자 유저로 메시지를 보내는 함수
    private static void send(String message) {
        if (admin != null) {
            try {
                admin.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 일반 유저가 접속했을 때, 운영자 유저에게 알리는 함수
    public static void visit(String key) {
        // json 구조로 status는 visit이고 key는 유저 키 정보이다.(javascript와 맞추는 프로토콜)
        send("{\"status\":\"visit\", \"key\":\"" + key + "\"}");
    }

    // 운영자 유저가 접속 시 발생하는 이벤트 함수
    @OnOpen
    public void handleOpen(Session userSession) {
        // 이미 운영자 유저가 소켓에 접속중이라면
        if(admin != null) {
            try {
                admin.close(); // 접속 종료
            }catch(IOException e) {
                e.printStackTrace();
            }
        }

        // 운영자 유저의 세션을 바꾼다.
        admin = userSession;
        // 기존에 접속해 있는 유저의 정보를 운영자 client로 보낸다.
        for(String key : TrainMessageReceiver.getUserKeys()) {
            visit(key); // 전송
        }
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        // 일반 유저의 key로 탐색후 메시지 전송
        TrainMessageReceiver.sendMessage(message);
    }

    @OnClose
    public void handleClose(Session userSession) {
        admin = null;
    }

}
