package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.ToDo;

/**
 * Represents a command for creating a new "ToDo" task in ProfBook.
 * This command is typically used to add a "ToDo" task.
 */
public class CreateTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String ERROR_MESSAGE_DUPLICATE = "This Todo task has already been allocated";
    public static final String ERROR_MESSAGE_INVALID_PATH = "This path is invalid.";
    public static final String ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";
    public static final String MESSAGE_DUPLICATE_TODO_TASK_STUDENT =
            "This ToDo task has already been allocated to this student in ProfBook";
    public static final String MESSAGE_DUPLICATE_TODO_TASK_GROUP =
            "This ToDo task has already been allocated to this group in ProfBook";
    public static final String MESSAGE_SUCCESS_ALL_STUDENTS =
            "New ToDo task added to all students in group: %1$s";
    public static final String MESSAGE_SUCCESS_ALL_GROUPS =
            "New ToDo task added to all groups in root: %1$s";

    public static final String MESSAGE_ERROR = "Invalid target encountered while creating this todo task";
    public static final String MESSAGE_SUCCESS = "New ToDo task has been added to: %1$s";
    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook.";
    public static final String MESSAGE_NOT_TASK_MANAGER = "Cannot create task for this path.";
    public static final String MESSAGE_INVALID_PATH_FOR_ALL_STU = "All stu flag is only allowed for group path";
    public static final String MESSAGE_INVALID_PATH_FOR_ALL_GROUP = "All Group flag is only allowed for root path";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student";

    private final AbsolutePath target;
    private final ToDo todo;
    private String category = null;

    /**
     * Constructs a {@code CreateTodoCommand} with the specified relative path and "ToDo" task details.
     *
     * @param relativePath The relative path to the group where the "ToDo" task will be added.
     * @param todo         The details of the "ToDo" task to be created.
     */
    public CreateTodoCommand(AbsolutePath target, ToDo todo) {
        requireAllNonNull(target, todo);
        this.target = target;
        this.todo = todo;
    }

    /**
     * Constructs a {@code CreateTodoCommand} with the specified absolute path and "ToDo" task details.
     *
     * @param target The absolute path to the group where the "ToDo" task will be added.
     * @param todo         The details of the "ToDo" task to be created.
     * @param category     The specific category of people to add ToDo task to each.
     */
    public CreateTodoCommand(AbsolutePath target, ToDo todo, String category) {
        requireAllNonNull(target, todo, category);
        this.target = target;
        this.todo = todo;
        this.category = category;
    }

    /**
     * Executes the CreateTodoCommand, adding a "ToDo" task to either a group or a specific student as specified
     * in the relative path.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);
        if (this.category == null) {
            TaskOperation taskOperation = state.taskOperation(target);
            if (taskOperation.hasTask(this.todo)) {
                throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK_STUDENT);
            }
            taskOperation.addTask(this.todo);
            state.updateList();
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));
        }

        if (this.category.equals("allStu")) {
            if (!target.isGroupDirectory()) {
                throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_STU);
            }
            ChildOperation<Student> groupOper = state.groupChildOperation(target);
            groupOper.addTaskToAllChildren(todo, 1);
            state.updateList();
            return new CommandResult(MESSAGE_SUCCESS_ALL_STUDENTS);
        }

        if (!target.isRootDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_GROUP);
        }
        ChildOperation<Group> rootOper = state.rootChildOperation();
        rootOper.addTaskToAllChildren(todo, 1);;

        state.updateList();

        return new CommandResult(MESSAGE_SUCCESS_ALL_GROUPS);
    }

    /**
     * Checks if this CreateTodoCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateTodoCommand)) {
            return false;
        }

        CreateTodoCommand otherCreateTodoCommand = (CreateTodoCommand) other;
        return this.target.equals(otherCreateTodoCommand.target)
                && this.todo.equals(otherCreateTodoCommand.todo);
    }

    /**
     * Returns the string representation of this CreateTodoCommand.
     *
     * @return A string representation of the CreateTodoCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateTodo", this.todo)
                .toString();
    }
}