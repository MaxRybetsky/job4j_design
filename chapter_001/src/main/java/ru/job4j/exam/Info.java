package ru.job4j.exam;

public class Info {
    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        return added == info.added
                && changed == info.changed
                && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(added);
        result = 31 * result + Integer.hashCode(changed);
        result = 31 * result + Integer.hashCode(deleted);
        return result;
    }

    @Override
    public String toString() {
        return added + ", "
                + changed + ", "
                + deleted;
    }
}
