-- 사원의 수를 구한다.
select count(*) from employees;

-- 남자 사원의 수를 구한다.
select count(*)
from employees
where gender='M';

-- d005부서 현재 근무 하고 있는 사원들의 수를 가져온다.
-- to_date가 9999년 01월 01일인 사원이 현재 근무하고 있는 사원이다.
select count(*)
from dept_emp
where dept_no='d005' and to_date='9999-01-01';

-- 현재 받고 있는 급여의 총합을 구한다.
select sum(salary)
from salaries
where to_date='9999-01-01';

-- 현재 받고 있는 급여의 평균을 구한다.
select avg(salary)
from salaries
where to_date='9999-01-01';

-- 현재 받고 있는 급여의 최고액을 구한다.
select max(salary)
from salaries
where to_date='9999-01-01';

-- 현재 받고 있는 급여의 최저액을 구한다.
select min(salary)
from salaries
where to_date='9999-01-01';




