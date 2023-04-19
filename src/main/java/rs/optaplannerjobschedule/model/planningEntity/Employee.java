package rs.optaplannerjobschedule.model.planningEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@PlanningEntity
public class Employee {
    private Long id;
    private String name;
    private Integer workingMinutes;
    private Set<String> skills;
    @InverseRelationShadowVariable(sourceVariableName = "employee")
    private List<Task> tasks;

    public Employee() {
    }

    public Employee(Long id, String name, Integer workingMinutes, Set<String> skills) {
        this.id = id;
        this.name = name;
        this.workingMinutes = workingMinutes;
        this.skills = skills;
        this.tasks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkingMinutes() {
        if (workingMinutes == null){
            return 0;
        }
        return workingMinutes;
    }

    public void setWorkingMinutes(Integer workingMinutes) {
        this.workingMinutes = workingMinutes;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Double getSumMinutesOfAssignedTasks() {
        if (tasks == null) {
            return 0.0;
        }
        return tasks.stream().mapToDouble(Task::getTimeNeeded).sum();
    }

    @JsonIgnore
    public Set<String> getTasksRequiredSkills() {
        Set<String> skillSet = new HashSet<>();
        if (this.tasks == null) {
            return skillSet;
        }
        for (Task task : this.tasks) {
            skillSet.add(task.getSkillRequired());
        }
        return skillSet;
    }

    public Boolean checkIfTasksOverlap() {
        if (this.tasks == null) {
            return false;
        }
        int size = this.tasks.size();
        for (int i = 0; i < size; i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.getStartTime() == null) {
                return false;
            }
            LocalTime currentTaskEndTime = currentTask.getStartTime().plusMinutes(currentTask.getTimeNeeded());
            for (int j = i + 1; j < size; j++) {
                Task otherTask = this.tasks.get(j);
                if (otherTask.getStartTime() == null) {
                    return false;
                }
                LocalTime otherTaskEndTime = otherTask.getStartTime().plusMinutes(otherTask.getTimeNeeded());
                if (currentTask.getStartTime().isBefore(otherTaskEndTime) &&
                    otherTask.getStartTime().isBefore(currentTaskEndTime)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        return name;
    }
}
