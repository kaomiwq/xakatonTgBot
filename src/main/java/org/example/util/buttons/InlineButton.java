package org.example.util.buttons;

public class InlineButton {
    private String title;
    private String callBackData;

    public InlineButton(String title, String callBackData) {
        this.title = title;
        this.callBackData = callBackData;
    }

    public String getTitle() {
        return title;
    }

    public String getCallBackData() {
        return callBackData;
    }
}
