select
	sum(issue_qty)
from
	minvmatiss
where
	factory_code = :factoryCode
and req_no = :reqNo
and req_mat_code = :reqMatCode