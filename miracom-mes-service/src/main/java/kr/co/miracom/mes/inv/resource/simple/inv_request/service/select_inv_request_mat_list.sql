select 
	mat.mat_code req_mat_code, 
	mat.mat_short_desc req_mat_short_desc, 
	mat.mat_desc req_mat_desc, 
	sub.req_qty, 
	sub.issue_qty, 
	sub.remain_qty
from (
	select 
		dtl.factory_code, 
		dtl.req_no, 
		dtl.req_mat_code, 
		dtl.req_qty, 
		nvl(sum(iss.issue_qty), 0) issue_qty, 
		dtl.req_qty-nvl(sum(iss.issue_qty), 0) remain_qty
	from (
		select 
			factory_code, 
			req_no, 
			req_mat_code, 
			req_qty 
      	from minvreqdtl
      	where factory_code=:factoryCode 
      	and req_no=:reqNo
     ) dtl
	left join 
		minvmatiss iss
	on 
		iss.factory_code=dtl.factory_code 
	and iss.req_no=dtl.req_no 
	and iss.req_mat_code=dtl.req_mat_code
	group by 
		dtl.factory_code, 
		dtl.req_no, 
		dtl.req_mat_code, 
		dtl.req_qty
) sub, mwipmatdef mat
where mat.factory_code=sub.factory_code 
and mat.mat_code=sub.req_mat_code