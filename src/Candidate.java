import list.DoubleLinkedList;

public class Candidate {
    // Private fields for the candidate's name and their current match
    private String name, currentMatch;
    // A DoubleLinkedList to store the candidate's ordered choices
    private DoubleLinkedList<String> orderedChoices;

    /**
     * Default constructor that delegates to the main constructor with a null name.
     */
    public Candidate() {
        this(null);
    }

    /**
     * Constructor to create a new Candidate with a given name.
     * 
     * @param name The name of the candidate.
     */
    public Candidate(String name) {
        this.name = name;
        this.currentMatch = null; // Initially, the candidate has no match
        orderedChoices = new DoubleLinkedList<>(); // Initialize the list of choices
    }

    /**
     * Adds a choice to the candidate's list of preferences.
     * 
     * @param priority The priority of the choice.
     * @param nm       The name of the choice to be added.
     * @return boolean indicating if the choice was successfully added.
     */
    public boolean addChoice(int priority, String nm){
        return orderedChoices.insert(priority, nm);
    }

    /**
     * Retrieves a choice based on its priority.
     * 
     * @param priority The priority level of the choice to retrieve.
     * @return String representing the choice at the given priority.
     */
    public String getChoice(int priority){
        if (priority > orderedChoices.getLength())
            throw new IndexOutOfBoundsException(); // Ensure priority is within list bounds
        return orderedChoices.getEntry(priority);
    }

    /**
     * Gets the candidate's name.
     * 
     * @return The name of the candidate.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the candidate's name.
     * 
     * @param name The new name for the candidate.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current match of the candidate.
     * 
     * @return The name of the current match.
     */
    public String getCurrentMatch() {
        return currentMatch;
    }

    /**
     * Sets the current match for the candidate.
     * 
     * @param currentMatch The name of the new match.
     */
    public void setCurrentMatch(String currentMatch) {
        this.currentMatch = currentMatch;
    }

    /**
     * Finds the priority of a given name in the candidate's choice list.
     * 
     * @param nm The name to find the priority for.
     * @return int representing the priority, or -1 if the name is not in the list.
     */
    public int getPriorityOf(String nm){
        int size = orderedChoices.getLength();
        for(int i = 1; i <= size; i++){
            if(orderedChoices.getEntry(i).equals(nm))
                return i;
        }
        return -1;
    }

    /**
     * Provides a string representation of the candidate and their current match.
     * 
     * @return A string indicating the candidate's name and their match.
     */
    @Override
    public String toString() {
        return getName() + " <-> " + getCurrentMatch();
    }
}
