package rs.optaplannerjobschedule.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.optaplannerjobschedule.model.response.JobScheduleResponse;
import rs.optaplannerjobschedule.service.JobSchedulingService;

@RestController
@RequestMapping("/jobScheduling")
public class JobSchedulingController {
    private final JobSchedulingService service;
    private final ModelMapper modelMapper;

    public JobSchedulingController(JobSchedulingService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/solve")
    public ResponseEntity<?> solve(@RequestParam(name = "numOfEmployee") Integer numOfEmployee,
                                   @RequestParam(name = "numOfJobs") Integer numOfJobs,
                                   @RequestParam(name = "numOfSkills") Integer numOfSkills,
                                   @RequestParam(name = "secondsForOptimization") Integer secondsForOptimization,
                                   @RequestParam(name = "unimprovedSecondsSpentLimit") Integer unimprovedSecondsSpentLimit,
                                   @RequestParam(name = "employeeWorkingMinutes") Integer employeeWorkingMinutes) {
        return ResponseEntity.ok(modelMapper.map(
                service.solve(numOfEmployee, numOfJobs, numOfSkills, secondsForOptimization,
                              unimprovedSecondsSpentLimit, employeeWorkingMinutes), JobScheduleResponse.class));
    }
}
