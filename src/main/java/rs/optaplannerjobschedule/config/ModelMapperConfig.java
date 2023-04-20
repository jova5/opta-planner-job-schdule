package rs.optaplannerjobschedule.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.optaplannerjobschedule.domain.JobSchedule;
import rs.optaplannerjobschedule.model.planningEntity.Employee;
import rs.optaplannerjobschedule.model.planningEntity.Task;
import rs.optaplannerjobschedule.model.response.JobScheduleResponse;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ModelMapperConfig {
    public ModelMapperConfig() {
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.createTypeMap(JobSchedule.class, JobScheduleResponse.class).setConverter(ctx -> {
            JobSchedule src = ctx.getSource();
            JobScheduleResponse dest = new JobScheduleResponse();

            List<Employee> employees = new ArrayList<>();
            for (Employee employee : src.getEmployeeList()) {
                Employee ee = new Employee();
                ee.setId(employee.getId());
                ee.setName(employee.getName());
                ee.setWorkingMinutes(employee.getWorkingMinutes());
                ee.setSkills(employee.getSkills());

                List<Task> tasks1 = new ArrayList<>();
                for (Task task : employee.getTasks()) {
                    Task t = new Task();
                    Employee e = new Employee();
                    if (task.getEmployee() != null) {
                        e.setId(task.getEmployee().getId());
                        e.setName(task.getEmployee().getName());
                    }
                    t.setId(task.getId());
                    t.setTaskName(task.getTaskName());
                    t.setEmployee(e);
                    t.setTimeNeeded(task.getTimeNeeded());
                    t.setSkillRequired(task.getSkillRequired());
                    t.setStartTime(task.getStartTime());
                    t.setPriority(task.getPriority());
                    tasks1.add(t);
                }

                ee.setTasks(tasks1);
                employees.add(ee);
            }
            dest.setEmployeeList(employees);

            List<Task> taskList = new ArrayList<>();
            for (Task task : src.getTaskList()) {
                Task t = new Task();
                t.setId(task.getId());
                t.setTaskName(task.getTaskName());
                t.setSkillRequired(task.getSkillRequired());
                t.setTimeNeeded(task.getTimeNeeded());
                t.setStartTime(task.getStartTime());
                t.setPriority(task.getPriority());

                Employee e = new Employee();
                if (task.getEmployee() != null) {
                    e.setId(task.getEmployee().getId());
                    e.setName(task.getEmployee().getName());
                    e.setWorkingMinutes(task.getEmployee().getWorkingMinutes());
                    e.setSkills(task.getEmployee().getSkills());
                }

                t.setEmployee(e);
                taskList.add(t);
            }
            dest.setTaskList(taskList);

            dest.setScore(src.getScore());
            return dest;
        });

        return modelMapper;
    }

}
