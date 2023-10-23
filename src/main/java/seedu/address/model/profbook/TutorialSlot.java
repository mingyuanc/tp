package seedu.address.model.profbook;

import javafx.scene.layout.Region;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.ui.TutorialSlotCard;
import seedu.address.ui.UiPart;

import java.util.Map;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class TutorialSlot extends ChildrenAndTaskListManager<Group> {

    /**
     * Unique identifier of the group
     */
    private final Id id;

    /**
     * Constructs a Group instance with all fields.
     *
     * @param taskList - The task list associated with this tutorial slot
     * @param groups   - The map of groups in this tutorial slot
     * @param id       - Unique identifier of the tutorial slot
     */
    public TutorialSlot(TaskList taskList, Map<Id, Group> groups, Id id) {
        super(groups, taskList);
        requireAllNonNull(id);
        this.id = id;
    }

    /**
     * Construct a new Tutorial slot instance without task list or children
     *
     * @param id the id of the tutorial slot
     */
    public TutorialSlot(Id id) {
        super();
        this.id = id;
    }

    public Id getId() {
        return id;
    }


    /**
     * Creates a clone of the current element, this is to achieve immutability
     *
     * @return The clone of the IChildElement
     */
    @Override
    public TutorialSlot getClone() {
        return new TutorialSlot(new TaskList(getAllTask()), this.getChildren(), this.id);
    }

    @Override
    public UiPart<Region> getDisplayCard(int displayedIndex) {
        return new TutorialSlotCard(this, displayedIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Tutorial Id", id)
                .add("Groups", super.toString())
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialSlot)) {
            return false;
        }

        TutorialSlot otherTutorial = (TutorialSlot) other;
        return super.equals(otherTutorial)
                && this.id.equals(otherTutorial.id);
    }

}
