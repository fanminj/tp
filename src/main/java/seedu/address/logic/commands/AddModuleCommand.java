package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Module;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addMod";
    public static final String MESSAGE_SUCCESS = "New module has been added";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds module."
            + "Parameters: "
            + PREFIX_MODULE + "NAME ";

    private final Module toAdd;

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // TODO implement after changing model and addressbook class
        if (model.hasShowable(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addShowable(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
