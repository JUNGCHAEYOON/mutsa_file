-- 현재 받는 급여의 평균보다 많이 받는 사원들의 사원번호, 급여액을 가져온다.

select avg(salary) from salaries where to_date='9999-01-01';

select emp_no, salary
from salaries
where salary > (select avg(salary) from salaries where to_date='9999-01-01')
      and to_date='9999-01-01';
    
-- d001부서에 근무하고 있는 사원들의 사원번호와 first_name을 가져온다.
    
select a1.emp_no, a1.first_name
from employees a1, dept_emp a2
where a1.emp_no = a2.emp_no and a2.dept_no = 'd001';

select emp_no from dept_emp where dept_no='d001';

select emp_no, first_name
from employees
where emp_no in(select emp_no from dept_emp where dept_no='d001');

-- 1960년 이후에 태어난 사원들이 근무하고 있는 사원들의 사원번호, 근무 부서 번호를 가져온다.
      
select emp_no from employees where birth_date >= '1960-01-01';
      
select emp_no, dept_no
from dept_emp
where emp_no in (select emp_no from employees where birth_date >= '1960-01-01');
      
      
      
      
      