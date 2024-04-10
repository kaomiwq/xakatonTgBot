package org.example.service;

import org.example.service.logic.RegisterLogic;
import org.example.service.logic.ShowLogic;
import org.example.service.logic.StartLogic;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private Map<String, Service> methods;
    private RegisterLogic registerLogic;
    private ShowLogic showLogic;
    private StartLogic startLogic;

    public ServiceManager() {

        methods = new HashMap<>();

        registerLogic = new RegisterLogic();
        showLogic = new ShowLogic();
        startLogic = new StartLogic();

        methods.put(State.WaitingCommandStart, startLogic::processWaitingCommandStart);
        methods.put(State.WaitingShowOrRegisterCommand, startLogic::processWaitingShowOrRegisterCommand);
        methods.put(State.WaitingInputTeamName, registerLogic::processWaitingInputTeamName);
        methods.put(State.WaitingInputNumberOfParticipants, registerLogic::processWaitingInputNumberOfParticipants);
        methods.put(State.WaitingInputSelectedTask, registerLogic::processWaitingInputSelectedTask);
        methods.put(State.WaitingApproveData, registerLogic::processWaitingApproveData);
        methods.put(State.WaitingFirstShowCommands, showLogic::processWaitingFirstShowCommands);
        methods.put(State.WaitingMiddleShowCommands, showLogic::processWaitingMiddleShowCommands);
        methods.put(State.WaitingLastShowCommands, showLogic::processWaitingLastShowCommands);
    }

    public SendMessage callLogicMethod(String textFromUser, TransmittedData transmittedData) throws Exception {
        String state = transmittedData.getState();
        return methods.get(state).process(textFromUser, transmittedData);
    }
}
