-- 부서번호가 d005인 부서의 매니저의 사원의 사원번호와 부서번호를 가져온다.
select emp_no, dept_no
from dept_manager
where dept_no = 'd005'; 

-- 부서가 d003이 아닌 매니저 사원들의 사원번호와 부서 번호를 가져온다.
select emp_no, dept_no
from dept_manager
where dept_no <> 'd003';

-- 급여액이 150000 이상인 사원의 사원번호, 급여액을 가져온다.
select emp_no, salary
from salaries
where salary >= 150000;

-- 급여액이 40000 이하인 사원의 사원번호, 급여액을 가져온다.
select emp_no, salary
from salaries
where salary <= 40000;

-- 1986년 이후에 입사한 사원들의 사원번호, 입사일, 성과 이름을 가져온다.
select emp_no, hire_date, last_name, first_name
from employees
where hire_date >= '1986-01-01';

-- 1990년 이후에 매니저가 된 사원들의 사원번호, 부서번호, 매니저 시작 날짜를 가져온다.
select emp_no, dept_no, from_date
from dept_manager
where from_date >= '1990-01-01';

-- 1990년 이전에 입사한 사원들의 사원번호, 입사일을 가져온다.
select emp_no, hire_date
from employees
where hire_date < '1990-01-01';


