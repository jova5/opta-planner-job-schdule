package rs.optaplannerjobschedule.constraint.provider;

import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;

import java.time.LocalTime;

import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class JobScheduleConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                noMoreTaskThanWorkingMinutes(constraintFactory),
                everyTaskShouldBeAssigned(constraintFactory),
                everyTaskShouldBeAssignedToEmployeeWithAdequateSkill(constraintFactory),
                tasksDontOverlap(constraintFactory),
                rewardEveryAssignedTask(constraintFactory),
                everyAssignedTaskShouldBeInitializedWithTime(constraintFactory),
                rewardEveryAssignedTaskInitializedWithTime(constraintFactory),
        };
    }

    public Constraint noMoreTaskThanWorkingMinutes(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Employee.class)
                                .filter(employee -> employee.getSumMinutesOfAssignedTasks() >
                                                    employee.getWorkingMinutes())
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("noMoreTaskThanWorkingMinutes");
    }

    public Constraint everyTaskShouldBeAssigned(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .filter(task -> task.getEmployee() != null)
                                .reward(HardMediumSoftScore.ONE_SOFT)
                                .asConstraint("everyTaskShouldBeAssigned");
    }

    public Constraint rewardEveryAssignedTask(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .filter(task -> task.getEmployee() == null)
                                .penalize(HardMediumSoftScore.ONE_MEDIUM)
                                .asConstraint("everyTaskShouldBeInitialized");
    }

    public Constraint everyTaskShouldBeAssignedToEmployeeWithAdequateSkill(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .filter(task -> {
                                    if (task.getEmployee() == null) {
                                        return false;
                                    }
                                    return !task.getEmployee().getSkills().contains(task.getSkillRequired());
                                })
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("everyTaskShouldBeAssignedToEmployeeWithAdequateSkill");
    }

    public Constraint tasksDontOverlap(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Task.class,
                                                   equal(Task::getEmployee))
                                .filter((task1, task2) -> {
                                    LocalTime startTime1 = task1.getStartTime();
                                    LocalTime startTime2 = task2.getStartTime();
                                    if (startTime1 == null || startTime2 == null) {
                                        return false;
                                    }
                                    LocalTime endTime1 = startTime1.plusMinutes(task1.getTimeNeeded());
                                    LocalTime endTime2 = startTime2.plusMinutes(task2.getTimeNeeded());
                                    return startTime1.isBefore(endTime2) && startTime2.isBefore(endTime1);
                                })
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("tasksDontOverlap");
    }

    public Constraint everyAssignedTaskShouldBeInitializedWithTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .filter(task -> {
                                    if (task.getEmployee() != null) {
                                        return task.getStartTime() == null;
                                    }
                                    return false;
                                })
                                .penalize(HardMediumSoftScore.ONE_MEDIUM)
                                .asConstraint("everyTaskShouldBeInitializedWithTime");
    }

    public Constraint rewardEveryAssignedTaskInitializedWithTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .filter(task -> {
                                    if (task.getEmployee() != null) {
                                        return task.getStartTime() != null;
                                    }
                                    return false;
                                })
                                .reward(HardMediumSoftScore.ONE_SOFT)
                                .asConstraint("rewardEveryAssignedTaskInitializedWithTime");
    }
}
