select
	distinct
	OPER.oper_code id,
	OPER.oper_code,
	OPER.oper_desc,
	OPER.unit,
	OPER.start_require_flag,
	OPER.push_pull_flag,
	OPER.erp_if_flag,
	OPER.erp_oper_code,
	OPER.store_flag,
	OPER.store_grp,
	OPER.create_user_id,
	OPER.create_time,
	OPER.update_user_id,
	OPER.update_time

from
	MWIPOPRDEF OPER,
	MRASEQPOPR EQOP

where
	OPER.factory_code = :factoryCode
	
	#if (!$Utils.isEmpty($operCode))
		and OPER.oper_code like :operCode
	#end
	#if (!$Utils.isEmpty($operDesc))
		and OPER.oper_desc like :operDesc
	#end
	#if ($Utils.toBoolean($storeFlag))
		and OPER.store_flag = 1
	#end
	#if (!$Utils.isEmpty($storeGrp))
		and OPER.store_grp in (:storeGrp)
	#end
	
	and EQOP.factory_code = OPER.factory_code
	and EQOP.oper_code = OPER.oper_code

order by
	OPER.oper_code