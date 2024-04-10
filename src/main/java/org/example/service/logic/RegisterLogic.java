package org.example.service.logic;

import org.example.db.Registration;
import org.example.db.RegistrationsRepository;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.NumberUtil;
import org.example.util.buttons.InlineButtonsStorage;
import org.example.util.buttons.InlineKeyboardsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RegisterLogic {
    private RegistrationsRepository registrationsRepository;

    public RegisterLogic() {
        registrationsRepository = new RegistrationsRepository();
    }

    public SendMessage processWaitingInputTeamName(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.length() > 30) {
            messageToUser.setText("Ошибка ввода названия команды. Длина названия должна быть от 1 до 30 символов. Повторите ввод названия команды");
            return messageToUser;
        }

        transmittedData.getDataStorage().addOrUpdate("teamName", textFromUser);

        messageToUser.setText("Название команды успешно записано. Теперь введите количество человек в команде от 1 до 4");
        transmittedData.setState(State.WaitingInputNumberOfParticipants);


        return messageToUser;
    }

    public SendMessage processWaitingInputNumberOfParticipants(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (NumberUtil.isIntNumber(textFromUser) == false) {
            messageToUser.setText("Ошибка ввода количества членов команды. Вы ввели не арабское число");
            return messageToUser;
        }

        int numberOfParticipants = NumberUtil.stringToInt(textFromUser);

        if (NumberUtil.isNumberInRange(numberOfParticipants, 1, 4) == false) {
            messageToUser.setText("Ошибка ввода количества членов команды. Количество членов должно быть от 1 до 4");
            return messageToUser;
        }

        transmittedData.getDataStorage().addOrUpdate("numberOfParticipants", numberOfParticipants);

        messageToUser.setText("Количество членов команды успешно записано. Теперь номер выбранной задачи.\nЗадача №1 - Разработать чат бота\nЗадача №2 - Разработать мобильное приложение\nЗадача №3 - Разработать сайт");

        transmittedData.setState(State.WaitingInputSelectedTask);

        return messageToUser;
    }

    public SendMessage processWaitingInputSelectedTask(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (NumberUtil.isIntNumber(textFromUser) == false) {
            messageToUser.setText("Ошибка ввода номера выбранной задачи. Вы ввели не арабское число");
            return messageToUser;
        }

        int selectedTask = NumberUtil.stringToInt(textFromUser);

        if (NumberUtil.isNumberInRange(selectedTask, 1, 3) == false) {
            messageToUser.setText("Ошибка ввода номера выбранной задачи. Количество задач должно быть от 1 до 3");
            return messageToUser;
        }

        transmittedData.getDataStorage().addOrUpdate("selectedTask", selectedTask);

        String teamName = (String) transmittedData.getDataStorage().get("teamName");
        int numberOfParticipants = (int) transmittedData.getDataStorage().get("numberOfParticipants");

        messageToUser.setText(String.format("Выбранная задача успешно записана. Теперь проверьте корретность введённых данных и нажмите кнопку подтверждения или отмены.\nНазвание команды: %s\nКоличество членов в команде: %d\nНомер выбранной задачи: %d", teamName, numberOfParticipants, selectedTask));

        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getApproveRegisterKeyboard());

        transmittedData.setState(State.WaitingApproveData);



        return messageToUser;
    }

    public SendMessage processWaitingApproveData(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.equals(InlineButtonsStorage.ApproveRegister.getCallBackData()) == false && textFromUser.equals(InlineButtonsStorage.DisapproveRegister.getCallBackData()) == false) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.ApproveRegister.getCallBackData()) == true) {
            String teamName = (String) transmittedData.getDataStorage().get("teamName");
            int numberOfParticipants = (int) transmittedData.getDataStorage().get("numberOfParticipants");
            int selectedTask = (int) transmittedData.getDataStorage().get("selectedTask");

            Registration registration = new Registration(0, teamName, numberOfParticipants, selectedTask);

            registrationsRepository.addNew(registration);

            transmittedData.getDataStorage().clear();

            messageToUser.setText("Данные о регистрации успещно сохранены. Вернитесь в начало регистрации путём нажатия на /start");
        } else if (textFromUser.equals(InlineButtonsStorage.DisapproveRegister.getCallBackData()) == true) {
            transmittedData.getDataStorage().clear();

            messageToUser.setText("Регистрация успешно отменена. Вернитесь в начало регистрации путём нажатия на /start");
        }

        transmittedData.setState(State.WaitingCommandStart);


        return messageToUser;
    }
}
