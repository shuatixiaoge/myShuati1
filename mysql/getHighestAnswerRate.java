// https://leetcode.com/problems/get-highest-answer-rate-question/description/
// the major thing is help you understand group by, which if you use it, it would only do
// operations on that group.
// Also aggregation function could be used with a if or case condition


//Solution1
SELECT question_id as survey_log
FROM
(
    SELECT question_id,
         SUM(case when action="answer" THEN 1 ELSE 0 END) as num_answer,
        SUM(case when action="show" THEN 1 ELSE 0 END) as num_show
    FROM survey_log
    GROUP BY question_id
) as tbl
ORDER BY (num_answer / num_show) DESC
LIMIT 1;

//Solution2
SELECT
    question_id AS 'survey_log'
FROM
    survey_log
GROUP BY question_id
ORDER BY COUNT(answer_id) / COUNT(IF(action = 'show', 1, 0)) DESC
LIMIT 1;
