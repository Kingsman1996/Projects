import actors.user.*;
import data.Data;
import message.UserMessage;
import validate.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserMessage.systemTitle();
        int choice = Validator.inputValidInt();
        switch (choice) {
            case 1:
                UserAction.login();
                break;
            case 2:
                UserAction.register();
                break;
            case 0:
                break;
            default:
                UserMessage.invalidChoice();
        }
    }
}
