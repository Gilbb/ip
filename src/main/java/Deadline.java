public class Deadline extends Task{
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    @Override
    public String markDone() {
        super.markDone();
        return this.toString();
    }
    @Override
    public String unmarkDone() {
        super.unmarkDone();
        return this.toString();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
