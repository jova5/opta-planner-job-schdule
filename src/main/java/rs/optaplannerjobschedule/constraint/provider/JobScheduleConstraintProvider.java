package rs.optaplannerjobschedule.constraint.provider;

import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;

import java.time.LocalTime;
import java.util.Set;

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
//                higherPriorityShouldBeAssigned(constraintFactory),
//                rewardAssignedTaskWithHigherPriority(constraintFactory),
                penalizeUnAssignedTasksWithHigherPriorityIfLoverPriorityTaskIsAssigned(constraintFactory),
        };
    }

    public Constraint noMoreTaskThanWorkingMinutes(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Employee.class)
                                .filter(employee -> employee.getSumMinutesOfAssignedTasks() >
                                                    employee.getWorkingMinutes())
                                .penalize(HardMediumSoftScore.ONE_HARD)
                                .asConstraint("noMoreTaskThanWorkingMinutes");
    }

    public Constraint rewardEveryAssignedTask(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .filter(task -> task.getEmployee() != null)
                                .reward(HardMediumSoftScore.ONE_SOFT)
                                .asConstraint("everyTaskShouldBeAssigned");
    }

    // Not necessary
    public Constraint everyTaskShouldBeAssigned(ConstraintFactory constraintFactory) {
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

    // Not necessary
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

    public Constraint higherPriorityShouldBeAssigned(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .join(Task.class)
                                .penalize(HardMediumSoftScore.ONE_MEDIUM, (task, task2) -> {
                                    if (task.getPriority() > task2.getPriority()) {
                                        if (task.getEmployee() == null && task.getStartTime() == null) {
                                            return task.getPriority();
                                        }
                                    }
                                    if (task.getPriority() < task2.getPriority()) {
                                        if (task2.getEmployee() == null && task2.getStartTime() == null) {
                                            return task2.getPriority();
                                        }
                                    }
                                    return 0;
                                })
                                .asConstraint("tsts");
    }

    public Constraint rewardAssignedTaskWithHigherPriority(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .join(Task.class)
                                .reward(HardMediumSoftScore.ONE_SOFT, (task, task2) -> {
//                                    if (task.getPriority() > task2.getPriority()) {
//                                        if (task.getEmployee() != null && task.getStartTime() != null) {
//                                            return task.getPriority();
//                                        }
//                                    }
//                                    if (task.getPriority() < task2.getPriority()) {
//                                        if (task2.getEmployee() != null && task2.getStartTime() != null) {
//                                            return task2.getPriority();
//                                        }
//                                    }
//                                    return 0;
                                    if (task.getEmployee() != null && task2.getEmployee() == null) {
                                        Set<String> skills = task.getEmployee().getSkills();
                                        if (skills.contains(task2.getSkillRequired())) {
                                            if (task.getPriority() > task2.getPriority()) {
                                                return task.getPriority();
                                            }
                                        }
                                        return 0;
                                    }
                                    if (task2.getEmployee() != null && task.getEmployee() == null) {
                                        Set<String> skills = task2.getEmployee().getSkills();
                                        if (skills.contains(task.getSkillRequired())) {
                                            if (task2.getPriority() > task.getPriority()) {
                                                return task2.getPriority();
                                            }
                                        }
                                        return 0;
                                    }
                                    return 0;
                                })
                                .asConstraint("rewardAssignedTaskWithHigherPriority");
    }

    public Constraint penalizeUnAssignedTasksWithHigherPriorityIfLoverPriorityTaskIsAssigned(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachIncludingNullVars(Task.class)
                                .join(Task.class)
                                .penalize(HardMediumSoftScore.ONE_SOFT, (Task task1, Task task2) -> {
                                    if (task1.getEmployee() != null && task2.getEmployee() == null) {
                                        Set<String> skills = task1.getEmployee().getSkills();
                                        if (skills.contains(task2.getSkillRequired())) {
                                            if (task1.getPriority() < task2.getPriority()) {
                                                return task2.getPriority();
                                            }
                                        }
                                        return 0;
                                    }
                                    if (task2.getEmployee() != null && task1.getEmployee() == null) {
                                        Set<String> skills = task2.getEmployee().getSkills();
                                        if (skills.contains(task1.getSkillRequired())) {
                                            if (task2.getPriority() < task1.getPriority()) {
                                                return task1.getPriority();
                                            }
                                        }
                                        return 0;
                                    }
                                    return 0;
                                })
                                .asConstraint("penalizeUnAssignedTasksWithHigherPriorityIfLoverPriorityTaskIsAssigned");
    }
}
