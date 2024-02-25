INSERT INTO `sprint` (`id`, `data_fim`, `data_inicio`) VALUES
(1,	'2024-04-04 00:00:00.000000',	NULL),
(2,	NULL,	NULL),
(3,	NULL,	NULL),
(4,	NULL,	NULL),
(5,	NULL,	NULL);

INSERT INTO `team` (`id`, `nome`) VALUES
(1,	'Time A'),
(2, 'Time B'),
(3, 'Time C');

INSERT INTO `feature` (`id_feature`, `acceptance_criteria`, `effort`, `hypothesis`, `priority`, `title`, `value_date`, `sprint_id`, `time_id`) VALUES
(1,	'string',	0,	'string',	0,	'string',	'2024-02-18',	1,	1),
(2,	'string',	0,	'string',	0,	'string',	'2024-02-18',	1,	1),
(3,	'string',	0,	'string',	0,	'string',	'2024-02-18',	1,	1),
(4,	'string',	0,	'string',	0,	'string',	'2024-02-18',	1,	1);

INSERT INTO `task` (`id`, `descricao`, `dev`, `end_date`, `notas`, `prioridade`, `status`, `titulo`, `feature_id_feature`, `dead_line`) VALUES
(1,	'string',	'string',	'2024-02-18',	'string',	0,	0,	'string',	1,	'2024-02-18'),
(2,		'string',	'string',	'2024-02-18',	'string',	0,	0,	'string',	1,	'2024-02-18');