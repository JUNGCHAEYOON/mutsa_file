select concat('aaa', 'bbb', 'ccc');

select insert('aaaaa', 2, 2, 'bbb');

select insert('aaaaa', 2, 0, 'bbb');

select replace('aabbcc', 'bb', 'ff');

select instr('abcdefg', 'cde');

select instr('abcdefg', 'kkk');

select left('abcdefg', 3);

select right('abcdefg', 3);

select mid('abcdefg', 3, 3);

select substring('abcdefg', 3, 3);

select concat('[', '       abc        ', ']');

select concat('[', ltrim('       abc        '), ']');

select concat('[', rtrim('       abc        '), ']');

select concat('[', trim('       abc        '), ']');

select lcase('abCDef');
select lower('abCDef');

select ucase('abCDef');
select upper('abCDef');

select reverse('abcdef');

-- 사원의 이름을 가져온다. 성과 이름을 하나의 문자열로 가져온다.

select lower(concat(first_name, ' ', last_name))
from employees;







