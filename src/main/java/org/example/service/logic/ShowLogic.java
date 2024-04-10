package org.example.service.logic;

import org.example.db.Registration;
import org.example.db.RegistrationsRepository;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.buttons.InlineButtonsStorage;
import org.example.util.buttons.InlineKeyboardsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;

public class ShowLogic {
    private RegistrationsRepository registrationsRepository;

    public ShowLogic() {
        registrationsRepository = new RegistrationsRepository();
    }

    public SendMessage processWaitingFirstShowCommands(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.equals(InlineButtonsStorage.MoveNextShow.getCallBackData()) == false && textFromUser.equals(InlineButtonsStorage.FinishShow.getCallBackData()) == false) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MoveNextShow.getCallBackData()) == true) {

            ArrayList<Registration> registrations = (ArrayList<Registration>) transmittedData.getDataStorage().get("registrations");

            int countRegistrations = (int) transmittedData.getDataStorage().get("countRegistrations");

            int currentRegistrationNumber = (int) transmittedData.getDataStorage().get("currentRegistrationNumber");

            currentRegistrationNumber++;

            transmittedData.getDataStorage().addOrUpdate("currentRegistrationNumber", currentRegistrationNumber);

            Registration currentRegistration = registrations.get(currentRegistrationNumber - 1);

            messageToUser.setText(String.format("Команда %d из %d\nНазвание команды: %s\nКоличество членов в команде: %d\nНомер выбранной задачи: %d", currentRegistrationNumber, countRegistrations, currentRegistration.getTeamName(), currentRegistration.getNumberOfParticipants(), currentRegistration.getSelectedTask()));

            if (currentRegistrationNumber == countRegistrations) {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getLastShowKeyboard());

                transmittedData.setState(State.WaitingLastShowCommands);
            } else {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getMiddleShowKeyboard());

                transmittedData.setState(State.WaitingMiddleShowCommands);
            }

        } else if (textFromUser.equals(InlineButtonsStorage.FinishShow.getCallBackData()) == true) {
            transmittedData.getDataStorage().clear();

            messageToUser.setText("Просмотр закончен. Вернитесь в начало путём нажатия на /start");

            transmittedData.setState(State.WaitingCommandStart);
        }


        return messageToUser;
    }

    public SendMessage processWaitingMiddleShowCommands(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.equals(InlineButtonsStorage.MoveNextShow.getCallBackData()) == false && textFromUser.equals(InlineButtonsStorage.FinishShow.getCallBackData()) == false && textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) == false) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MoveNextShow.getCallBackData()) == true) {

            ArrayList<Registration> registrations = (ArrayList<Registration>) transmittedData.getDataStorage().get("registrations");

            int countRegistrations = (int) transmittedData.getDataStorage().get("countRegistrations");

            int currentRegistrationNumber = (int) transmittedData.getDataStorage().get("currentRegistrationNumber");

            currentRegistrationNumber++;

            transmittedData.getDataStorage().addOrUpdate("currentRegistrationNumber", currentRegistrationNumber);

            Registration currentRegistration = registrations.get(currentRegistrationNumber - 1);

            messageToUser.setText(String.format("Команда %d из %d\nНазвание команды: %s\nКоличество членов в команде: %d\nНомер выбранной задачи: %d", currentRegistrationNumber, countRegistrations, currentRegistration.getTeamName(), currentRegistration.getNumberOfParticipants(), currentRegistration.getSelectedTask()));

            if (currentRegistrationNumber == countRegistrations) {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getLastShowKeyboard());

                transmittedData.setState(State.WaitingLastShowCommands);
            } else {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getMiddleShowKeyboard());

                transmittedData.setState(State.WaitingMiddleShowCommands);
            }

        }
        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) == true) {
            ArrayList<Registration> registrations = (ArrayList<Registration>) transmittedData.getDataStorage().get("registrations");

            int countRegistrations = (int) transmittedData.getDataStorage().get("countRegistrations");

            int currentRegistrationNumber = (int) transmittedData.getDataStorage().get("currentRegistrationNumber");

            currentRegistrationNumber--;

            transmittedData.getDataStorage().addOrUpdate("currentRegistrationNumber", currentRegistrationNumber);

            Registration currentRegistration = registrations.get(currentRegistrationNumber - 1);

            messageToUser.setText(String.format("Команда %d из %d\nНазвание команды: %s\nКоличество членов в команде: %d\nНомер выбранной задачи: %d", currentRegistrationNumber, countRegistrations, currentRegistration.getTeamName(), currentRegistration.getNumberOfParticipants(), currentRegistration.getSelectedTask()));

            if (currentRegistrationNumber == 1) {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getFirstShowKeyboard());

                transmittedData.setState(State.WaitingFirstShowCommands);
            } else {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getMiddleShowKeyboard());

                transmittedData.setState(State.WaitingMiddleShowCommands);
            }
        } else if (textFromUser.equals(InlineButtonsStorage.FinishShow.getCallBackData()) == true) {
            transmittedData.getDataStorage().clear();

            messageToUser.setText("Просмотр закончен. Вернитесь в начало путём нажатия на /start");

            transmittedData.setState(State.WaitingCommandStart);
        }


        return messageToUser;
    }

    public SendMessage processWaitingLastShowCommands(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) == false && textFromUser.equals(InlineButtonsStorage.FinishShow.getCallBackData()) == false) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) == true) {

            ArrayList<Registration> registrations = (ArrayList<Registration>) transmittedData.getDataStorage().get("registrations");

            int countRegistrations = (int) transmittedData.getDataStorage().get("countRegistrations");

            int currentRegistrationNumber = (int) transmittedData.getDataStorage().get("currentRegistrationNumber");

            currentRegistrationNumber--;

            transmittedData.getDataStorage().addOrUpdate("currentRegistrationNumber", currentRegistrationNumber);

            Registration currentRegistration = registrations.get(currentRegistrationNumber - 1);

            messageToUser.setText(String.format("Команда %d из %d\nНазвание команды: %s\nКоличество членов в команде: %d\nНомер выбранной задачи: %d", currentRegistrationNumber, countRegistrations, currentRegistration.getTeamName(), currentRegistration.getNumberOfParticipants(), currentRegistration.getSelectedTask()));

            if (currentRegistrationNumber == 1) {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getFirstShowKeyboard());

                transmittedData.setState(State.WaitingFirstShowCommands);
            } else {
                messageToUser.setReplyMarkup(InlineKeyboardsStorage.getMiddleShowKeyboard());

                transmittedData.setState(State.WaitingMiddleShowCommands);
            }

        } else if (textFromUser.equals(InlineButtonsStorage.FinishShow.getCallBackData()) == true) {
            transmittedData.getDataStorage().clear();

            messageToUser.setText("Просмотр закончен. Вернитесь в начало путём нажатия на /start");

            transmittedData.setState(State.WaitingCommandStart);
        }


        return messageToUser;
    }




}
