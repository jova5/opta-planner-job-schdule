package rs.optaplannerjobschedule.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;

import java.time.LocalTime;
import java.util.List;

@PlanningSolution
public class JobSchedule {
    private Long id;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<Employee> employeeList;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<LocalTime> localTimeList;

    @PlanningEntityCollectionProperty
    private List<Task> taskList;

    @PlanningScore
    private HardMediumSoftScore score;

    public JobSchedule() {
    }

    public JobSchedule(Long id, List<Task> taskList, List<Employee> employeeList, List<LocalTime> localTimeList) {
        this.id = id;
        this.taskList = taskList;
        this.employeeList = employeeList;
        this.localTimeList = localTimeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<LocalTime> getLocalTimeList() {
        return localTimeList;
    }

    public void setLocalTimeList(List<LocalTime> localTimeList) {
        this.localTimeList = localTimeList;
    }

    public HardMediumSoftScore getScore() {
        return score;
    }

    public void setScore(HardMediumSoftScore score) {
        this.score = score;
    }
}
