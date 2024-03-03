package by.test.task.core.exceptions.abstracts;

public interface ExceptionEnum {
    String getDescription();

    default String getName() {
        try {
            return String.format("%s-%s", this.getClass().getSimpleName(), ((Enum)this).name());
        } catch (Exception var2) {
            return this.getClass().getSimpleName();
        }
    }

    default String getDescription(String... args) {
        return String.format(this.getDescription(), (Object[])args);
    }
}