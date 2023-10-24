package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.newcommands.CommandTestUtil.TASK_DESC_DESC;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_CATEGORY_STUDENT;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_TASK_DESC;
import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CommandTestUtil;
import seedu.address.logic.newcommands.CreateTodoCommand;
import seedu.address.model.taskmanager.ToDo;

public class CreateTodoCommandParserTest {
    private CreateTodoCommandParser parser = new CreateTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser,
                VALID_STUDENT_DIR_PREAMBLE + TASK_DESC_DESC,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateTodoCommand(CommandTestUtil.getValidStudentAbsolutePath(), new ToDo(VALID_TASK_DESC)));
    }

    @Test
    public void parse_allFieldsPresentWithCatergory_success() {
        assertParseSuccess(parser,
                VALID_STUDENT_DIR_PREAMBLE + TASK_DESC_DESC + VALID_CATEGORY_STUDENT,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateTodoCommand(CommandTestUtil.getValidStudentAbsolutePath(),
                        new ToDo(VALID_TASK_DESC), "allStu"));
    }
}