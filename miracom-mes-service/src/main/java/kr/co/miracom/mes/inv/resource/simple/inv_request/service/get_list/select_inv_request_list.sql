select
	REQ.req_no id,
	REQ.req_no,
	REQ.req_date,
	ORD.order_no,
	MAT.mat_code,
	MAT.mat_short_desc,
	ORD.ord_qty,
	ORD.ord_priority,
	GCM.key_1 ord_status,
	GCM.data_1 ord_status_desc,
	LIN.line_code,
	LIN.line_desc
from
	MWIPORDSTS ORD,
	MINVREQMST REQ,
	MWIPMATDEF MAT,
	MGCMTBLDAT GCM,
	MWIPLINDEF LIN

where
	REQ.factory_code = :factoryCode
	and REQ.req_date >= :fromDate
	and REQ.req_date <= :toDate
	
	and ORD.factory_code = REQ.factory_code
	and ORD.order_no = REQ.order_no
	#if (!$Utils.isEmpty($lineCode))
		and ORD.line_code like :lineCode
	#end
	
	and MAT.factory_code = ORD.factory_code
	and MAT.mat_code = ORD.mat_code
	
	and GCM.factory_code = ORD.factory_code
	and GCM.table_name='ORD_STATUS'
	and GCM.key_1 = ORD.ord_status

	and LIN.factory_code = ORD.factory_code
	and LIN.line_code = ORD.line_code
order by
	REQ.req_date asc,
	REQ.req_no asc