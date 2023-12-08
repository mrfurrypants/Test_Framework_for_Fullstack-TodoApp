package readDataFromXLSX;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("Sheet1")
public class tasks_providerPOJO1 {
    @ExcelCellName("Task_title")
    private String Task_title;
    @ExcelCellName("Due_date")
    private int Due_date;
    @ExcelCellName("Description")
    private String Description;

    public String getTask_title() {
        return Task_title;
    }

    public void setTask_title(String task_title) {
        Task_title = task_title;
    }

    public int getDue_date() {
        return Due_date;
    }

    public void setDue_date(int due_date) {
        Due_date = due_date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
