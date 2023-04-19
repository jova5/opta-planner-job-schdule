package rs.optaplannerjobschedule.model.planningEntity;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalTime;

@PlanningEntity
public class Task {
    @PlanningId
    private Long id;
    private String taskName;
    private Integer timeNeeded;
    private String skillRequired;
    @PlanningVariable(nullable = true)
    private Employee employee;
    @PlanningVariable(nullable = true)
    private LocalTime startTime;

    public Task() {
    }

    public Task(Long id, String taskName, Integer timeNeeded, String skillRequired) {
        this.id = id;
        this.taskName = taskName;
        this.timeNeeded = timeNeeded;
        this.skillRequired = skillRequired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(Integer timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public String getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(String skillRequired) {
        this.skillRequired = skillRequired;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        String name;
        if (employee == null) {
            name = "null";
        } else {
            name = employee.getName();
        }
        return taskName + " Employee: " + name + " Start Time: " + startTime;
    }
}
