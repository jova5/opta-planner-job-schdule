# opta-planner-job-schdule
Scheduling jobs with custom Construction Heuristic phase for better scaling.

First approach:

```java
    ConstructionHeuristicPhaseConfig phaseConfig = new ConstructionHeuristicPhaseConfig();
    ChangeMoveSelectorConfig changeMoveSelectorConfig = new ChangeMoveSelectorConfig();
    ValueSelectorConfig valueSelectorConfig = new ValueSelectorConfig();
    valueSelectorConfig.setVariableName("employee");
    changeMoveSelectorConfig.setValueSelectorConfig(valueSelectorConfig);
    ChangeMoveSelectorConfig changeMoveSelectorConfig1 = new ChangeMoveSelectorConfig();
    ValueSelectorConfig valueSelectorConfig1 = new ValueSelectorConfig();
    valueSelectorConfig1.setVariableName("startTime");
    changeMoveSelectorConfig1.setValueSelectorConfig(valueSelectorConfig1);
    phaseConfig.setMoveSelectorConfigList(Arrays.asList(changeMoveSelectorConfig,changeMoveSelectorConfig1));

    SolverConfig solverConfig = new SolverConfig().withPhaseList(List.of(phaseConfig));
```

First approach log:

```
Solving started: time spent (43), best score (0hard/0medium/0soft), environment mode (REPRODUCIBLE), move thread count (NONE), random (JDK with seed 0).
CH step (0), time spent (64), score (0hard/0medium/0soft), selected move count (6), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (1), time spent (72), score (0hard/0medium/0soft), selected move count (541), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (2), time spent (73), score (0hard/0medium/0soft), selected move count (6), picked move (Task_1 Employee: null Start Time: null {null -> null}).
CH step (3), time spent (74), score (0hard/0medium/0soft), selected move count (541), picked move (Task_1 Employee: null Start Time: null {null -> null}).
CH step (4), time spent (74), score (0hard/0medium/0soft), selected move count (6), picked move (Task_2 Employee: null Start Time: null {null -> null}).
CH step (5), time spent (76), score (0hard/0medium/0soft), selected move count (541), picked move (Task_2 Employee: null Start Time: null {null -> null}).
CH step (6), time spent (76), score (0hard/0medium/0soft), selected move count (6), picked move (Task_3 Employee: null Start Time: null {null -> null}).
CH step (7), time spent (77), score (0hard/0medium/0soft), selected move count (541), picked move (Task_3 Employee: null Start Time: null {null -> null}).
CH step (8), time spent (77), score (0hard/0medium/0soft), selected move count (6), picked move (Task_4 Employee: null Start Time: null {null -> null}).
CH step (9), time spent (78), score (0hard/0medium/0soft), selected move count (541), picked move (Task_4 Employee: null Start Time: null {null -> null}).
CH step (10), time spent (79), score (0hard/0medium/0soft), selected move count (6), picked move (Task_5 Employee: null Start Time: null {null -> null}).
CH step (11), time spent (80), score (0hard/0medium/0soft), selected move count (541), picked move (Task_5 Employee: null Start Time: null {null -> null}).
CH step (12), time spent (80), score (0hard/0medium/0soft), selected move count (6), picked move (Task_6 Employee: null Start Time: null {null -> null}).
CH step (13), time spent (81), score (0hard/0medium/0soft), selected move count (541), picked move (Task_6 Employee: null Start Time: null {null -> null}).
CH step (14), time spent (81), score (0hard/0medium/0soft), selected move count (6), picked move (Task_7 Employee: null Start Time: null {null -> null}).
CH step (15), time spent (82), score (0hard/0medium/0soft), selected move count (541), picked move (Task_7 Employee: null Start Time: null {null -> null}).
CH step (16), time spent (82), score (0hard/0medium/0soft), selected move count (6), picked move (Task_8 Employee: null Start Time: null {null -> null}).
CH step (17), time spent (84), score (0hard/0medium/0soft), selected move count (541), picked move (Task_8 Employee: null Start Time: null {null -> null}).
CH step (18), time spent (84), score (0hard/0medium/0soft), selected move count (6), picked move (Task_9 Employee: null Start Time: null {null -> null}).
CH step (19), time spent (85), score (0hard/0medium/0soft), selected move count (541), picked move (Task_9 Employee: null Start Time: null {null -> null}).
CH step (20), time spent (85), score (0hard/0medium/0soft), selected move count (6), picked move (Task_10 Employee: null Start Time: null {null -> null}).
CH step (21), time spent (86), score (0hard/0medium/0soft), selected move count (541), picked move (Task_10 Employee: null Start Time: null {null -> null}).
CH step (22), time spent (86), score (0hard/0medium/0soft), selected move count (6), picked move (Task_11 Employee: null Start Time: null {null -> null}).
CH step (23), time spent (88), score (0hard/0medium/0soft), selected move count (541), picked move (Task_11 Employee: null Start Time: null {null -> null}).
CH step (24), time spent (88), score (0hard/0medium/0soft), selected move count (6), picked move (Task_12 Employee: null Start Time: null {null -> null}).
CH step (25), time spent (90), score (0hard/0medium/0soft), selected move count (541), picked move (Task_12 Employee: null Start Time: null {null -> null}).
CH step (26), time spent (91), score (0hard/0medium/0soft), selected move count (6), picked move (Task_13 Employee: null Start Time: null {null -> null}).
CH step (27), time spent (92), score (0hard/0medium/0soft), selected move count (541), picked move (Task_13 Employee: null Start Time: null {null -> null}).
CH step (28), time spent (92), score (0hard/0medium/0soft), selected move count (6), picked move (Task_14 Employee: null Start Time: null {null -> null}).
CH step (29), time spent (93), score (0hard/0medium/0soft), selected move count (541), picked move (Task_14 Employee: null Start Time: null {null -> null}).
CH step (30), time spent (93), score (0hard/0medium/0soft), selected move count (6), picked move (Task_15 Employee: null Start Time: null {null -> null}).
CH step (31), time spent (94), score (0hard/0medium/0soft), selected move count (541), picked move (Task_15 Employee: null Start Time: null {null -> null}).
CH step (32), time spent (95), score (0hard/0medium/0soft), selected move count (6), picked move (Task_16 Employee: null Start Time: null {null -> null}).
CH step (33), time spent (96), score (0hard/0medium/0soft), selected move count (541), picked move (Task_16 Employee: null Start Time: null {null -> null}).
CH step (34), time spent (96), score (0hard/0medium/0soft), selected move count (6), picked move (Task_17 Employee: null Start Time: null {null -> null}).
CH step (35), time spent (97), score (0hard/0medium/0soft), selected move count (541), picked move (Task_17 Employee: null Start Time: null {null -> null}).
CH step (36), time spent (97), score (0hard/0medium/0soft), selected move count (6), picked move (Task_18 Employee: null Start Time: null {null -> null}).
CH step (37), time spent (98), score (0hard/0medium/0soft), selected move count (541), picked move (Task_18 Employee: null Start Time: null {null -> null}).
CH step (38), time spent (99), score (0hard/0medium/0soft), selected move count (6), picked move (Task_19 Employee: null Start Time: null {null -> null}).
CH step (39), time spent (100), score (0hard/0medium/0soft), selected move count (541), picked move (Task_19 Employee: null Start Time: null {null -> null}).
CH step (40), time spent (100), score (0hard/0medium/0soft), selected move count (6), picked move (Task_20 Employee: null Start Time: null {null -> null}).
CH step (41), time spent (101), score (0hard/0medium/0soft), selected move count (541), picked move (Task_20 Employee: null Start Time: null {null -> null}).
CH step (42), time spent (102), score (0hard/0medium/0soft), selected move count (6), picked move (Task_21 Employee: null Start Time: null {null -> null}).
CH step (43), time spent (103), score (0hard/0medium/0soft), selected move count (541), picked move (Task_21 Employee: null Start Time: null {null -> null}).
CH step (44), time spent (103), score (0hard/0medium/0soft), selected move count (6), picked move (Task_22 Employee: null Start Time: null {null -> null}).
CH step (45), time spent (104), score (0hard/0medium/0soft), selected move count (541), picked move (Task_22 Employee: null Start Time: null {null -> null}).
CH step (46), time spent (105), score (0hard/0medium/0soft), selected move count (6), picked move (Task_23 Employee: null Start Time: null {null -> null}).
CH step (47), time spent (106), score (0hard/0medium/0soft), selected move count (541), picked move (Task_23 Employee: null Start Time: null {null -> null}).
CH step (48), time spent (106), score (0hard/0medium/0soft), selected move count (6), picked move (Task_24 Employee: null Start Time: null {null -> null}).
CH step (49), time spent (107), score (0hard/0medium/0soft), selected move count (541), picked move (Task_24 Employee: null Start Time: null {null -> null}).
CH step (50), time spent (107), score (0hard/0medium/0soft), selected move count (6), picked move (Task_25 Employee: null Start Time: null {null -> null}).
CH step (51), time spent (109), score (0hard/0medium/0soft), selected move count (541), picked move (Task_25 Employee: null Start Time: null {null -> null}).
CH step (52), time spent (109), score (0hard/0medium/0soft), selected move count (6), picked move (Task_26 Employee: null Start Time: null {null -> null}).
CH step (53), time spent (111), score (0hard/0medium/0soft), selected move count (541), picked move (Task_26 Employee: null Start Time: null {null -> null}).
CH step (54), time spent (111), score (0hard/0medium/0soft), selected move count (6), picked move (Task_27 Employee: null Start Time: null {null -> null}).
CH step (55), time spent (113), score (0hard/0medium/0soft), selected move count (541), picked move (Task_27 Employee: null Start Time: null {null -> null}).
CH step (56), time spent (113), score (0hard/0medium/0soft), selected move count (6), picked move (Task_28 Employee: null Start Time: null {null -> null}).
CH step (57), time spent (114), score (0hard/0medium/0soft), selected move count (541), picked move (Task_28 Employee: null Start Time: null {null -> null}).
CH step (58), time spent (115), score (0hard/0medium/0soft), selected move count (6), picked move (Task_29 Employee: null Start Time: null {null -> null}).
CH step (59), time spent (116), score (0hard/0medium/0soft), selected move count (541), picked move (Task_29 Employee: null Start Time: null {null -> null}).
CH step (60), time spent (116), score (0hard/0medium/0soft), selected move count (6), picked move (Task_30 Employee: null Start Time: null {null -> null}).
CH step (61), time spent (118), score (0hard/0medium/0soft), selected move count (541), picked move (Task_30 Employee: null Start Time: null {null -> null}).
CH step (62), time spent (118), score (0hard/0medium/0soft), selected move count (6), picked move (Task_31 Employee: null Start Time: null {null -> null}).
CH step (63), time spent (119), score (0hard/0medium/0soft), selected move count (541), picked move (Task_31 Employee: null Start Time: null {null -> null}).
CH step (64), time spent (119), score (0hard/0medium/0soft), selected move count (6), picked move (Task_32 Employee: null Start Time: null {null -> null}).
CH step (65), time spent (120), score (0hard/0medium/0soft), selected move count (541), picked move (Task_32 Employee: null Start Time: null {null -> null}).
CH step (66), time spent (120), score (0hard/0medium/0soft), selected move count (6), picked move (Task_33 Employee: null Start Time: null {null -> null}).
CH step (67), time spent (121), score (0hard/0medium/0soft), selected move count (541), picked move (Task_33 Employee: null Start Time: null {null -> null}).
CH step (68), time spent (121), score (0hard/0medium/0soft), selected move count (6), picked move (Task_34 Employee: null Start Time: null {null -> null}).
CH step (69), time spent (122), score (0hard/0medium/0soft), selected move count (541), picked move (Task_34 Employee: null Start Time: null {null -> null}).
CH step (70), time spent (123), score (0hard/0medium/0soft), selected move count (6), picked move (Task_35 Employee: null Start Time: null {null -> null}).
CH step (71), time spent (124), score (0hard/0medium/0soft), selected move count (541), picked move (Task_35 Employee: null Start Time: null {null -> null}).
CH step (72), time spent (124), score (0hard/0medium/0soft), selected move count (6), picked move (Task_36 Employee: null Start Time: null {null -> null}).
CH step (73), time spent (125), score (0hard/0medium/0soft), selected move count (541), picked move (Task_36 Employee: null Start Time: null {null -> null}).
CH step (74), time spent (125), score (0hard/0medium/0soft), selected move count (6), picked move (Task_37 Employee: null Start Time: null {null -> null}).
CH step (75), time spent (126), score (0hard/0medium/0soft), selected move count (541), picked move (Task_37 Employee: null Start Time: null {null -> null}).
CH step (76), time spent (126), score (0hard/0medium/0soft), selected move count (6), picked move (Task_38 Employee: null Start Time: null {null -> null}).
CH step (77), time spent (127), score (0hard/0medium/0soft), selected move count (541), picked move (Task_38 Employee: null Start Time: null {null -> null}).
CH step (78), time spent (128), score (0hard/0medium/0soft), selected move count (6), picked move (Task_39 Employee: null Start Time: null {null -> null}).
CH step (79), time spent (128), score (0hard/0medium/0soft), selected move count (541), picked move (Task_39 Employee: null Start Time: null {null -> null}).
CH step (80), time spent (128), score (0hard/0medium/0soft), selected move count (6), picked move (Task_40 Employee: null Start Time: null {null -> null}).
CH step (81), time spent (129), score (0hard/0medium/0soft), selected move count (541), picked move (Task_40 Employee: null Start Time: null {null -> null}).
Construction Heuristic phase (0) ended: time spent (130), best score (0hard/0medium/0soft), score calculation speed (291272/sec), step total (82).
Solving ended: time spent (130), best score (0hard/0medium/0soft), score calculation speed (172530/sec), phase total (1), environment mode (REPRODUCIBLE), move thread count (NONE).

```

Second approach:

```java
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
    queuedEntityPlacerConfig.setMoveSelectorConfigList(Arrays.asList(changeMoveSelectorConfig, changeMoveSelectorConfig2));
    phaseConfig.setEntityPlacerConfig(queuedEntityPlacerConfig);

    SolverConfig solverConfig = new SolverConfig().withPhaseList(List.of(phaseConfig));
```

Second approach log:

```
Solving started: time spent (48), best score (0hard/0medium/0soft), environment mode (REPRODUCIBLE), move thread count (NONE), random (JDK with seed 0).
CH step (0), time spent (82), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (1), time spent (86), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (2), time spent (88), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (3), time spent (89), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (4), time spent (90), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (5), time spent (91), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (6), time spent (93), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (7), time spent (94), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (8), time spent (95), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (9), time spent (96), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (10), time spent (97), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (11), time spent (98), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (12), time spent (99), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (13), time spent (100), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (14), time spent (101), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (15), time spent (102), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (16), time spent (104), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (17), time spent (104), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (18), time spent (106), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (19), time spent (107), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (20), time spent (108), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (21), time spent (109), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (22), time spent (110), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (23), time spent (111), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (24), time spent (113), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (25), time spent (114), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (26), time spent (115), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (27), time spent (117), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (28), time spent (118), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (29), time spent (119), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (30), time spent (120), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (31), time spent (121), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (32), time spent (122), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (33), time spent (123), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (34), time spent (124), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (35), time spent (125), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (36), time spent (126), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (37), time spent (127), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (38), time spent (128), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (39), time spent (129), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (40), time spent (131), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (41), time spent (132), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (42), time spent (133), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (43), time spent (135), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (44), time spent (135), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (45), time spent (136), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (46), time spent (137), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (47), time spent (138), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (48), time spent (139), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (49), time spent (140), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (50), time spent (141), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (51), time spent (143), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (52), time spent (144), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (53), time spent (145), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (54), time spent (146), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (55), time spent (147), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (56), time spent (148), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (57), time spent (149), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (58), time spent (150), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (59), time spent (151), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (60), time spent (152), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (61), time spent (153), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (62), time spent (154), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (63), time spent (155), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (64), time spent (156), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (65), time spent (157), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (66), time spent (158), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (67), time spent (159), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (68), time spent (160), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (69), time spent (161), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (70), time spent (162), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (71), time spent (163), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (72), time spent (164), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (73), time spent (165), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (74), time spent (166), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (75), time spent (167), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (76), time spent (169), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (77), time spent (170), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (78), time spent (172), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (79), time spent (173), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (80), time spent (174), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (81), time spent (175), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
Construction Heuristic phase (0) ended: time spent (175), best score (0hard/0medium/0soft), score calculation speed (173905/sec), step total (82).
Solving ended: time spent (175), best score (0hard/0medium/0soft), score calculation speed (115280/sec), phase total (1), environment mode (REPRODUCIBLE), move thread count (NONE).
```

Third approach:

```java
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

    SolverConfig solverConfig = new SolverConfig().withPhaseList(Arrays.asList(phaseConfig, phaseConfig2));
```

Third approach log:

```
Solving started: time spent (51), best score (0hard/0medium/0soft), environment mode (REPRODUCIBLE), move thread count (NONE), random (JDK with seed 0).
CH step (0), time spent (82), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (1), time spent (86), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (2), time spent (87), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (3), time spent (89), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (4), time spent (91), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (5), time spent (94), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (6), time spent (96), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (7), time spent (97), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (8), time spent (97), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (9), time spent (98), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (10), time spent (100), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (11), time spent (101), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (12), time spent (102), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (13), time spent (103), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (14), time spent (104), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (15), time spent (105), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (16), time spent (107), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (17), time spent (109), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (18), time spent (111), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (19), time spent (112), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (20), time spent (114), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (21), time spent (115), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (22), time spent (116), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (23), time spent (117), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (24), time spent (119), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (25), time spent (120), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (26), time spent (122), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (27), time spent (123), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (28), time spent (124), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (29), time spent (125), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (30), time spent (126), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (31), time spent (127), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (32), time spent (128), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (33), time spent (130), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (34), time spent (131), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (35), time spent (132), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (36), time spent (133), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (37), time spent (135), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (38), time spent (137), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (39), time spent (137), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (40), time spent (138), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
Construction Heuristic phase (0) ended: time spent (138), best score (0hard/0medium/0soft), score calculation speed (129320/sec), step total (41).
CH step (0), time spent (141), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (1), time spent (142), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (2), time spent (143), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (3), time spent (144), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (4), time spent (145), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (5), time spent (147), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (6), time spent (147), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (7), time spent (149), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (8), time spent (150), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (9), time spent (151), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (10), time spent (152), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (11), time spent (153), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (12), time spent (155), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (13), time spent (156), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (14), time spent (157), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (15), time spent (158), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (16), time spent (159), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (17), time spent (162), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (18), time spent (162), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (19), time spent (163), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (20), time spent (164), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (21), time spent (165), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (22), time spent (166), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (23), time spent (167), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (24), time spent (168), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (25), time spent (169), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (26), time spent (170), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (27), time spent (171), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (28), time spent (172), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (29), time spent (174), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (30), time spent (175), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (31), time spent (176), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (32), time spent (177), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (33), time spent (178), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (34), time spent (179), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (35), time spent (180), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (36), time spent (181), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (37), time spent (183), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (38), time spent (184), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (39), time spent (185), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
CH step (40), time spent (187), score (0hard/0medium/0soft), selected move count (246), picked move (Task_0 Employee: null Start Time: null {null -> null}).
Construction Heuristic phase (1) ended: time spent (187), best score (0hard/0medium/0soft), score calculation speed (214617/sec), step total (41).
Solving ended: time spent (187), best score (0hard/0medium/0soft), score calculation speed (107887/sec), phase total (2), environment mode (REPRODUCIBLE), move thread count (NONE).
```
