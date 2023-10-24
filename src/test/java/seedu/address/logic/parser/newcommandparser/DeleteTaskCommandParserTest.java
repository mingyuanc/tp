package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CommandTestUtil;
import seedu.address.logic.newcommands.DeleteTaskCommand;
import seedu.address.model.path.AbsolutePath;

public class DeleteTaskCommandParserTest {
    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();
    private AbsolutePath absolutePath = CommandTestUtil.getValidGroupAbsolutePath();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", absolutePath, new DeleteTaskCommand(FIRST_INDEX));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", absolutePath,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
    }
}