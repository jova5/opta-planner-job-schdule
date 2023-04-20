package rs.optaplannerjobschedule.service.impl;

import org.springframework.stereotype.Service;
import rs.optaplannerjobschedule.constraint.provider.JobScheduleConstraintProvider;
import rs.optaplannerjobschedule.domain.JobSchedule;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;
import rs.optaplannerjobschedule.service.JobSchedulingService;
import rs.optaplannerjobschedule.solver.CustomSolver;

import java.time.LocalTime;
import java.util.*;

@Service
public class JobSchedulingServiceImpl implements JobSchedulingService {
    private final List<String> SKILL_LIST = new ArrayList<>();

    private List<String> employeesSkill = new ArrayList<>();

    @Override
    public JobSchedule solve(Integer numOfEmployee, Integer numOfJobs, Integer numOfSkills,
                             Integer secondsForOptimization, Integer unimprovedSecondsSpentLimit,
                             Integer employeeWorkingMinutes) {
        generateSkillList(numOfSkills);
        List<Employee> employeeList = generateEmployees(numOfEmployee, employeeWorkingMinutes);
        List<Task> taskList = generateTasks(numOfJobs);
        List<LocalTime> localTimeList = getListOfLocalTime();
        JobSchedule problem = new JobSchedule(1L, taskList, employeeList, localTimeList);

        String score;
        if (numOfEmployee * 8 <= numOfJobs) {
            score = "0hard/" + numOfEmployee * 8 + "medium/0soft";
        } else {
            score = "0hard/" + numOfJobs + "medium/0soft";
        }
        JobSchedule solution =
                CustomSolver.solveJobSchedulingOne(JobScheduleConstraintProvider.class, problem, secondsForOptimization,
                                                score, false, null, (long) unimprovedSecondsSpentLimit, Task.class,
                                                Employee.class);
        return solution;
    }

    private void generateSkillList(Integer numOfSkills) {
        for (int i = 0; i < numOfSkills; i++) {
            SKILL_LIST.add("Skill_" + i);
        }
    }

    private List<Employee> generateEmployees(Integer numOfEmployees, Integer employeeWorkingMinutes) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < numOfEmployees; i++) {
            Set<String> skills = getEmployeesSkills();
            employeeList.add(new Employee((long) i, "Employee_" + i, employeeWorkingMinutes, skills));
        }
        return employeeList;
    }

    private Set<String> getEmployeesSkills() {
        int maxNumOfSkills = 4;
        Set<String> skills = new HashSet<>();
        for (int j = 0; j < maxNumOfSkills; j++) {
            String skill = getRandomSkill();
            if (skills.isEmpty()) {
                skills.add(skill);
                if (!employeesSkill.contains(skill)) {
                    employeesSkill.add(skill);
                }
            } else if (!skills.contains(skill)) {
                skills.add(skill);
                if (!employeesSkill.contains(skill)) {
                    employeesSkill.add(skill);
                }
            } else {
                j--;
            }
        }
        return skills;
    }

    private List<Task> generateTasks(Integer numOfTasks) {
        Random random = new Random();
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < numOfTasks; i++) {
            taskList.add(new Task((long) i, "Task_" + i, random.nextInt(60) + 1, getRandomTaskSkill(), random.nextInt(10) + 1));
        }
        return taskList;
    }

    private String getRandomSkill() {
        Random random = new Random();
        return SKILL_LIST.get(random.nextInt(10));
    }

    private String getRandomTaskSkill() {
        Random random = new Random();
        return employeesSkill.get(random.nextInt(employeesSkill.size()));
    }

    private List<LocalTime> getListOfLocalTime() {
        LocalTime localTime = LocalTime.of(7, 30);
        List<LocalTime> localTimeList = new ArrayList<>();
        for (int i = 0; i < 540; i++) {
            if (localTimeList.isEmpty()) {
                localTimeList.add(localTime);
            } else {
                localTime = localTime.plusMinutes(1);
                localTimeList.add(localTime);
            }
        }
        return localTimeList;
    }
}
