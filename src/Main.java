public class Main {
    // Регулярное выражение для проверки логина и пароля
    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9-_]+$";

    public static void main(String[] args) {
        check("anatolij", "123", "123");
        check("anatolij", "012345678901234567890123456789", "012345678901234567890123456789");
        check("anatolij01234567890123456789", "123", "123");
        check("anatolij!", "123", "123");
        check("anatolij", "123?", "123?");

    }

    private static void check(String login, String password, String confirmPassword){
        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (LoginException e) {
            System.out.println("Ошибка в логине: " + e.getMessage());;
        } catch (ConfirmPasswordException e) {
            System.out.println("Ошибка в пароле: " + e.getMessage());
        }
    }
    public static void checkLogin(String login) throws LoginException{
        if (!login.matches(VALIDATE_PATTERN)){
            throw new LoginException("Логин некорректен! Допустимы только латинские буквы, цифры и знак подчеркивания!");
        }
        if (login.length() > 20){
            throw  new LoginException("Ошибка длины логина! Длина логина может быть не больше 20 символов.");
        }
    }

    public static void checkPassword(String password, String confirmPassword) throws ConfirmPasswordException{
        if (!password.matches(VALIDATE_PATTERN)){
            throw new ConfirmPasswordException("Пароль некорректен! Допустимы только латинские буквы, цифры и знак подчеркивания!");
        }
        if (password.length() > 20){
            throw  new ConfirmPasswordException("Ошибка длины пароля! Длина пароля может быть не больше 20 символов.");
        }
        if (!password.equals(confirmPassword)){
            throw  new ConfirmPasswordException("Ошибка! Пароли и его подтверждение не совпадают");
        }
    }
}