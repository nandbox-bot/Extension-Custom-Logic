package org.example;

import com.nandbox.bots.api.Nandbox;
import com.nandbox.bots.api.NandboxClient;
import com.nandbox.bots.api.data.*;
import com.nandbox.bots.api.inmessages.*;
import com.nandbox.bots.api.outmessages.*;
import com.nandbox.bots.api.util.*;
import com.nandbox.bots.api.test.*;
import net.minidev.json.*;
import net.minidev.json.parser.JSONParser;
import org.example.CallbackAdapter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExtensionCustomLogic extends CallbackAdapter {
    private Nandbox.Api api;

    public static void main(String[] args) throws Exception {
        String TOKEN = "90091783776292659:0:O0B82ZDOvFPupWd2J9bPJlAIALaDbs";
        NandboxClient client = NandboxClient.get();
        client.connect(TOKEN, new ExtensionCustomLogic());
    }

    @Override
    public void onConnect(Nandbox.Api api) {
        this.api = api;
    }

    @Override
    public void onReceive(IncomingMessage incomingMsg) {
        String chatId = incomingMsg.getChat().getId();
        String text = incomingMsg.getText();
        if (text != null && text.startsWith("/weather ")) {
            String city = text.substring(9).trim();
            String apiKey = "4e57492e251f4907834122714252304";
            String endpoint = String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s", apiKey, city);
            try {
                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                String weather = jsonObject.get("current").toString();
                api.sendText(chatId, "Current weather: " + weather, Utils.getUniqueId(), null, null, 1, false, 0, null, null, null, incomingMsg.getAppId());
            } catch (Exception e) {
                api.sendText(chatId, "Error fetching weather: " + e.getMessage(), Utils.getUniqueId(), null, null, 1, false, 0, null, null, null, incomingMsg.getAppId());
            }
        }
    }

    @Override
    public void onReceive(JSONObject obj) {}

    @Override
    public void onClose() {}

    @Override
    public void onError() {}

    @Override
    public void onChatMenuCallBack(ChatMenuCallback chatMenuCallback) {}

    @Override
    public void onInlineMessageCallback(InlineMessageCallback inlineMsgCallback) {}

    @Override
    public void onMessagAckCallback(MessageAck msgAck) {}

    @Override
    public void onUserJoinedBot(User user) {}

    @Override
    public void onChatMember(ChatMember chatMember) {}

    @Override
    public void onChatAdministrators(ChatAdministrators chatAdministrators) {}

    @Override
    public void userStartedBot(User user) {}

    @Override
    public void onMyProfile(User user) {}

    @Override
    public void onProductDetail(ProductItemResponse productItem) {}

    @Override
    public void onCollectionProduct(GetProductCollectionResponse collectionProduct) {}

    @Override
    public void listCollectionItemResponse(ListCollectionItemResponse collections) {}

    @Override
    public void onUserDetails(User user, String appId) {}

    @Override
    public void userStoppedBot(User user) {}

    @Override
    public void userLeftBot(User user) {}

    @Override
    public void permanentUrl(PermanentUrl permenantUrl) {}

    @Override
    public void onChatDetails(Chat chat, String appId) {}

    @Override
    public void onInlineSearh(InlineSearch inlineSearch) {}

    @Override
    public void onBlackListPattern(Pattern pattern) {}

    @Override
    public void onWhiteListPattern(Pattern pattern) {}

    @Override
    public void onBlackList(BlackList blackList) {}

    @Override
    public void onDeleteBlackList(List_ak blackList) {}

    @Override
    public void onWhiteList(WhiteList whiteList) {}

    @Override
    public void onDeleteWhiteList(List_ak whiteList) {}

    @Override
    public void onScheduleMessage(IncomingMessage incomingScheduleMsg) {}

    @Override
    public void onWorkflowDetails(WorkflowDetails workflowDetails) {}

    @Override
    public void onCreateChat(Chat chat) {}

    @Override
    public void onMenuCallBack(MenuCallback menuCallback) {}
}
