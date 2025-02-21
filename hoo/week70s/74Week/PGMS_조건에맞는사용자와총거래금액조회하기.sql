-- 코드를 입력하세요
select ugb.writer_id user_id, ugu.nickname, ugb.total_sales
from used_goods_user ugu
join (select writer_id, sum(price) total_sales
        from used_goods_board
        where status = "DONE"
        group by writer_id) ugb
on ugu.user_id = ugb.writer_id
where total_sales >= 700000
order by total_sales asc;