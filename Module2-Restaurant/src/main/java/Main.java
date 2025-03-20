import message.UserMessage;
import user.User;
import validate.Validator;

public class Main {
    public static void main(String[] args) {
        UserMessage.systemTitle();
        int choice = Validator.inputValidInt();
        while (true) {
            switch (choice) {
                case 1:
                    User.login();
                    return;
                case 2:
                    User.register();
                    return;
                case 0:
                    return;
                default:
                    UserMessage.invalidChoice();
                    choice = Validator.inputValidInt();
            }
        }
    }
}
