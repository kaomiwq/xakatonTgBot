package org.example.util.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardsStorage {
    public static InlineKeyboardMarkup getApproveRegisterKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.ApproveRegister.getTitle());
        button.setCallbackData(InlineButtonsStorage.ApproveRegister.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.DisapproveRegister.getTitle());
        button.setCallbackData(InlineButtonsStorage.DisapproveRegister.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getStartKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.ShowCommandsStart.getTitle());
        button.setCallbackData(InlineButtonsStorage.ShowCommandsStart.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.RegisterNewCommandStart.getTitle());
        button.setCallbackData(InlineButtonsStorage.RegisterNewCommandStart.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getOneShowKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.FinishShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.FinishShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getFirstShowKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.MoveNextShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.MoveNextShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.FinishShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.FinishShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getMiddleShowKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.MoveNextShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.MoveNextShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.MovePrevShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.MovePrevShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.FinishShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.FinishShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getLastShowKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.MovePrevShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.MovePrevShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.FinishShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.FinishShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }
}
