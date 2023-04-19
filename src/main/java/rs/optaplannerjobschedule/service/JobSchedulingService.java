package rs.optaplannerjobschedule.service;

import rs.optaplannerjobschedule.domain.JobSchedule;

public interface JobSchedulingService {
    JobSchedule solve(Integer numOfEmployee, Integer numOfJobs, Integer numOfSkills, Integer secondsForOptimization,
                      Integer unimprovedSecondsSpentLimit, Integer employeeWorkingMinutes);
}
