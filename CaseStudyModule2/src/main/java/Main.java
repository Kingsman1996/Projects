import message.UserMessage;
import useraction.UserAction;
import validate.Validator;

public class Main {
    public static void main(String[] args) {
        UserMessage.systemTitle();
        int choice = Validator.inputValidInt();
        while (true) {
            switch (choice) {
                case 1:
                    UserAction.login();
                    return;
                case 2:
                    UserAction.register();
                    return;
                case 0:
                    return;
                default:
                    UserMessage.invalidChoice();
                    choice = Validator.inputValidInt();
            }
        }
//        AdminAction.handle();
//        CustomerAction.handle();
    }
}
