//@@author Kyholmes
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteAppointmentCommand object
 */
public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAppointmentCommand
     * and returns an DeleteAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s");

        Index index;

        try {
            index = ParserUtil.parseIndex(nameKeywords[nameKeywords.length - 1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE));
        }

        return new DeleteAppointmentCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), index);
    }
}
