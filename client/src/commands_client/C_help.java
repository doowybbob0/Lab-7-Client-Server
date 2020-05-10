package commands_client;

public class C_help {
	public static StringBuilder help() {
		StringBuilder helpString = new StringBuilder();

		helpString.append("Available commands:");
		helpString.append(System.lineSeparator());
		helpString.append("help : вывести справку по доступным командам");
		helpString.append(System.lineSeparator());
		helpString.append("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов");
		helpString.append(System.lineSeparator());
		helpString.append("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
		helpString.append(System.lineSeparator());
		helpString.append("insert_key : добавить новый элемент с автоматически сгенерированным ключом");
		helpString.append(System.lineSeparator());
		helpString.append("update_id {element} : обновить значение элемента коллекции, id которого равен заданному");
		helpString.append(System.lineSeparator());
		helpString.append("remove_key {key} : удалить элемент из коллекции по его ключу");
		helpString.append(System.lineSeparator());
		helpString.append("clear : очистить коллекцию");
		helpString.append(System.lineSeparator());
		helpString.append("save : сохранить коллекцию в файл");
		helpString.append(System.lineSeparator());
		/**helpString.append("execute_script {file_name} : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
		helpString.append(System.lineSeparator());**/
		helpString.append("exit : завершить программу (без сохранения в файл)");
		helpString.append(System.lineSeparator());
		helpString.append("history : вывести последние 14 команд (без их аргументов)");
		helpString.append(System.lineSeparator());
		helpString.append("replace_if_lower {key} : заменить значение по ключу, если новое значение меньше старого");
		helpString.append(System.lineSeparator());
		helpString.append("remove_greater_key {key} : удалить из коллекции все элементы, ключ которых превышает заданный");
		helpString.append(System.lineSeparator());
		helpString.append("filter_greater_than_health {health} : вывести элементы, значение поля health которых больше заданного");
		helpString.append(System.lineSeparator());
		helpString.append("print_field_ascending_health : вывести значения поля health всех элементов в порядке возрастания");
		helpString.append(System.lineSeparator());
		helpString.append("change_password : сменить пароль пользователя");
		helpString.append(System.lineSeparator());
        
		
		return helpString;
	}
}
