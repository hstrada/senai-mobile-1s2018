package sp.senai.br.android_todo_minimal.model;

/**
 * Created by helena.strada on 16/01/18.
 */

public class ToDoItem {

    private Long id;
    private String description;
    private String color;

    public ToDoItem() {

    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoItem toDoItem = (ToDoItem) o;

        if (id != null ? !id.equals(toDoItem.id) : toDoItem.id != null) return false;
        if (description != null ? !description.equals(toDoItem.description) : toDoItem.description != null)
            return false;
        return color != null ? color.equals(toDoItem.color) : toDoItem.color == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
