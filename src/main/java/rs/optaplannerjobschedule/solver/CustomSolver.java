package rs.optaplannerjobschedule.solver;

import lombok.extern.slf4j.Slf4j;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.ConstraintStreamImplType;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.constructionheuristic.placer.QueuedEntityPlacerConfig;
import org.optaplanner.core.config.heuristic.selector.common.SelectionCacheType;
import org.optaplanner.core.config.heuristic.selector.entity.EntitySelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.value.ValueSelectorConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;
import rs.optaplannerjobschedule.model.planningEntity.Task;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CustomSolver {

    public static <T, E extends SolverEventListener<T>> T solveJobSchedulingOne(
            Class<? extends ConstraintProvider> constraintProviderClass,
            T problem, Integer duration,
            String bestScoreLimit,
            Boolean bestScoreFeasible,
            E bestSolutionEventListener,
            Long unimprovedSecondsSpentLimit,
            Class<?>... entityClasses) {

        Class<?> problemClass = problem.getClass();
        TerminationConfig terminationConfig =
                new TerminationConfig().withBestScoreLimit(bestScoreLimit)
                                       .withSecondsSpentLimit((long) duration)
                                       .withUnimprovedSecondsSpentLimit(unimprovedSecondsSpentLimit);
        if (bestScoreFeasible) {
            terminationConfig.withBestScoreFeasible(true);
        }

        ConstructionHeuristicPhaseConfig phaseConfig = new ConstructionHeuristicPhaseConfig();
        ChangeMoveSelectorConfig changeMoveSelectorConfig = new ChangeMoveSelectorConfig();
        ValueSelectorConfig valueSelectorConfig = new ValueSelectorConfig();
        valueSelectorConfig.setVariableName("employee");
        changeMoveSelectorConfig.setValueSelectorConfig(valueSelectorConfig);
        ChangeMoveSelectorConfig changeMoveSelectorConfig1 = new ChangeMoveSelectorConfig();
        ValueSelectorConfig valueSelectorConfig1 = new ValueSelectorConfig();
        valueSelectorConfig1.setVariableName("startTime");
        changeMoveSelectorConfig1.setValueSelectorConfig(valueSelectorConfig1);
        phaseConfig.setMoveSelectorConfigList(Arrays.asList(changeMoveSelectorConfig, changeMoveSelectorConfig1));

        SolverConfig solverConfig = new SolverConfig().withSolutionClass(problemClass)
                                                      .withEntityClasses(entityClasses)
                                                      .withConstraintProviderClass(constraintProviderClass)
                                                      .withTerminationSpentLimit(Duration.ofSeconds(duration))
                                                      .withConstraintStreamImplType(ConstraintStreamImplType.BAVET)
                                                      .withTerminationConfig(terminationConfig)
                                                      .withPhaseList(List.of(phaseConfig));

        SolverFactory<T> solverFactory = SolverFactory.create(solverConfig);
        Solver<T> solver = solverFactory.buildSolver();
        if (bestSolutionEventListener != null) {
            solver.addEventListener(bestSolutionEventListener);
        }
        return solver.solve(problem);
    }

    public static <T, E extends SolverEventListener<T>> T solveJobSchedulingTwo(
            Class<? extends ConstraintProvider> constraintProviderClass,
            T problem, Integer duration,
            String bestScoreLimit,
            Boolean bestScoreFeasible,
            E bestSolutionEventListener,
            Long unimprovedSecondsSpentLimit,
            Class<?>... entityClasses) {

        Class<?> problemClass = problem.getClass();
        TerminationConfig terminationConfig =
                new TerminationConfig().withBestScoreLimit(bestScoreLimit)
                                       .withSecondsSpentLimit((long) duration)
                                       .withUnimprovedSecondsSpentLimit(unimprovedSecondsSpentLimit);
        if (bestScoreFeasible) {
            terminationConfig.withBestScoreFeasible(true);
        }

        ConstructionHeuristicPhaseConfig phaseConfig = new ConstructionHeuristicPhaseConfig();
        QueuedEntityPlacerConfig queuedEntityPlacerConfig = new QueuedEntityPlacerConfig();
        EntitySelectorConfig entitySelectorConfig = new EntitySelectorConfig();
        entitySelectorConfig.setCacheType(SelectionCacheType.PHASE);
        entitySelectorConfig.setEntityClass(Task.class);

        ChangeMoveSelectorConfig changeMoveSelectorConfig = new ChangeMoveSelectorConfig();
        changeMoveSelectorConfig.setEntitySelectorConfig(entitySelectorConfig);
        ValueSelectorConfig valueSelectorConfig = new ValueSelectorConfig();
        valueSelectorConfig.setVariableName("employee");
        changeMoveSelectorConfig.setValueSelectorConfig(valueSelectorConfig);

        ChangeMoveSelectorConfig changeMoveSelectorConfig2 = new ChangeMoveSelectorConfig();
        changeMoveSelectorConfig2.setEntitySelectorConfig(entitySelectorConfig);
        ValueSelectorConfig valueSelectorConfig2 = new ValueSelectorConfig();
        valueSelectorConfig2.setVariableName("startTime");
        changeMoveSelectorConfig2.setValueSelectorConfig(valueSelectorConfig);

        queuedEntityPlacerConfig.setEntitySelectorConfig(entitySelectorConfig);
        queuedEntityPlacerConfig.setMoveSelectorConfigList(
                Arrays.asList(changeMoveSelectorConfig, changeMoveSelectorConfig2));
        phaseConfig.setEntityPlacerConfig(queuedEntityPlacerConfig);

        SolverConfig solverConfig = new SolverConfig().withSolutionClass(problemClass)
                                                      .withEntityClasses(entityClasses)
                                                      .withConstraintProviderClass(constraintProviderClass)
                                                      .withTerminationSpentLimit(Duration.ofSeconds(duration))
                                                      .withConstraintStreamImplType(ConstraintStreamImplType.BAVET)
                                                      .withTerminationConfig(terminationConfig)
                                                      .withPhaseList(List.of(phaseConfig));

        SolverFactory<T> solverFactory = SolverFactory.create(solverConfig);
        Solver<T> solver = solverFactory.buildSolver();
        if (bestSolutionEventListener != null) {
            solver.addEventListener(bestSolutionEventListener);
        }
        return solver.solve(problem);
    }

    public static <T, E extends SolverEventListener<T>> T solveJobSchedulingThree(
            Class<? extends ConstraintProvider> constraintProviderClass,
            T problem, Integer duration,
            String bestScoreLimit,
            Boolean bestScoreFeasible,
            E bestSolutionEventListener,
            Long unimprovedSecondsSpentLimit,
            Class<?>... entityClasses) {

        Class<?> problemClass = problem.getClass();
        TerminationConfig terminationConfig =
                new TerminationConfig().withBestScoreLimit(bestScoreLimit)
                                       .withSecondsSpentLimit((long) duration)
                                       .withUnimprovedSecondsSpentLimit(unimprovedSecondsSpentLimit);
        if (bestScoreFeasible) {
            terminationConfig.withBestScoreFeasible(true);
        }

        ConstructionHeuristicPhaseConfig phaseConfig = new ConstructionHeuristicPhaseConfig();
        QueuedEntityPlacerConfig queuedEntityPlacerConfig = new QueuedEntityPlacerConfig();
        EntitySelectorConfig entitySelectorConfig = new EntitySelectorConfig();
        entitySelectorConfig.setCacheType(SelectionCacheType.PHASE);
        entitySelectorConfig.setEntityClass(Task.class);

        ChangeMoveSelectorConfig changeMoveSelectorConfig = new ChangeMoveSelectorConfig();
        changeMoveSelectorConfig.setEntitySelectorConfig(entitySelectorConfig);
        ValueSelectorConfig valueSelectorConfig = new ValueSelectorConfig();
        valueSelectorConfig.setVariableName("employee");
        changeMoveSelectorConfig.setValueSelectorConfig(valueSelectorConfig);

        queuedEntityPlacerConfig.setEntitySelectorConfig(entitySelectorConfig);
        queuedEntityPlacerConfig.setMoveSelectorConfigList(List.of(changeMoveSelectorConfig));
        phaseConfig.setEntityPlacerConfig(queuedEntityPlacerConfig);

        ConstructionHeuristicPhaseConfig phaseConfig2 = new ConstructionHeuristicPhaseConfig();
        QueuedEntityPlacerConfig queuedEntityPlacerConfig2 = new QueuedEntityPlacerConfig();

        ChangeMoveSelectorConfig changeMoveSelectorConfig2 = new ChangeMoveSelectorConfig();
        changeMoveSelectorConfig2.setEntitySelectorConfig(entitySelectorConfig);
        ValueSelectorConfig valueSelectorConfig2 = new ValueSelectorConfig();
        valueSelectorConfig2.setVariableName("startTime");
        changeMoveSelectorConfig2.setValueSelectorConfig(valueSelectorConfig);

        queuedEntityPlacerConfig2.setEntitySelectorConfig(entitySelectorConfig);
        queuedEntityPlacerConfig2.setMoveSelectorConfigList(List.of(changeMoveSelectorConfig2));
        phaseConfig2.setEntityPlacerConfig(queuedEntityPlacerConfig2);

        SolverConfig solverConfig = new SolverConfig().withSolutionClass(problemClass)
                                                      .withEntityClasses(entityClasses)
                                                      .withConstraintProviderClass(constraintProviderClass)
                                                      .withTerminationSpentLimit(Duration.ofSeconds(duration))
                                                      .withConstraintStreamImplType(ConstraintStreamImplType.BAVET)
                                                      .withTerminationConfig(terminationConfig)
                                                      .withPhaseList(Arrays.asList(phaseConfig, phaseConfig2));

        SolverFactory<T> solverFactory = SolverFactory.create(solverConfig);
        Solver<T> solver = solverFactory.buildSolver();
        if (bestSolutionEventListener != null) {
            solver.addEventListener(bestSolutionEventListener);
        }
        return solver.solve(problem);
    }
}
