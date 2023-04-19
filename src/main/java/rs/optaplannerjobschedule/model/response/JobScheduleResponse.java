package rs.optaplannerjobschedule.model.response;

import lombok.Data;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;

import java.util.List;

@Data
public class JobScheduleResponse {
    private List<Employee> employeeList;
    private List<Task> taskList;
    private HardMediumSoftScore score;
}
