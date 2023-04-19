package rs.optaplannerjobschedule.constraint.provider;

import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;

public class JobScheduleConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                noMoreTaskThanWorkingMinutes(constraintFactory),
                everyTaskShouldBeAssigned(constraintFactory),
                everyTaskShouldBeAssignedToEmployeeWithAdequateSkill(constraintFactory),
                tasksDontOverlap(constraintFactory),
                everyTaskShouldBeInitialized(constraintFactory),
                everyTaskShouldBeInitializedWithTime(constraintFactory),
        };
    }

    public Constraint noMoreTaskThanWorkingMinutes(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Employee.class)
                                .filter(employee -> employee.getSumMinutesOfAssignedTasks() >
                                                    employee.getWorkingMinutes())
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("noMoreTaskThanWorkingMinutes");
    }

    public Constraint everyTaskShouldBeAssigned(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Task.class)
                                .filter(task -> task.getEmployee() != null)
                                .reward(HardMediumSoftScore.ONE_MEDIUM)
                                .asConstraint("everyTaskShouldBeAssigned");
    }

    public Constraint everyTaskShouldBeAssignedToEmployeeWithAdequateSkill(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Task.class)
                                .filter(task -> {
                                    if (task.getEmployee() == null) {
                                        return false;
                                    }
                                    return !task.getEmployee().getSkills().contains(task.getSkillRequired());
                                })
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("everyTaskShouldBeAssignedToEmployeeWithAdequateSkill");
    }

    public Constraint everyTaskShouldBeInitialized(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Task.class)
                                .filter(task -> task.getEmployee() == null)
                                .penalize(HardMediumSoftScore.ONE_MEDIUM)
                                .asConstraint("everyTaskShouldBeInitialized");
    }

    public Constraint everyTaskShouldBeInitializedWithTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Task.class)
                                .filter(task -> task.getStartTime() == null)
                                .penalize(HardMediumSoftScore.ONE_MEDIUM)
                                .asConstraint("everyTaskShouldBeInitializedWithTime");
    }

    public Constraint tasksDontOverlap(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Employee.class)
                                .filter(Employee::checkIfTasksOverlap)
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("tasksDontOverlap");
    }
}
