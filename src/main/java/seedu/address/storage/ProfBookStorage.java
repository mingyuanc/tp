package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyProfBook;

public interface ProfBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns ProfBook data as a {@link ReadOnlyProfBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyProfBook> readAddressBook() throws DataLoadingException;

    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyProfBook} to the storage.
     * @param profBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyProfBook profBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyProfBook) 
     */
    void saveAddressBook(ReadOnlyProfBook profBook, Path filePath) throws IOException;
}
