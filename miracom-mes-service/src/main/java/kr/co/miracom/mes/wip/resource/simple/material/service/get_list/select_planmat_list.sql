select
	distinct
    MAT.mat_code id,
    MAT.mat_code,
    MAT.mat_desc,
    MAT.mat_short_desc,
    MAT.purchase_insp_flag,
    MAT.import_insp_flag,
    MAT.outgoing_insp_flag,
    MAT.mat_type,
    MAT.mat_grp,
    MAT.weight_net,
    MAT.weight_gross,
    MAT.volume_unit,
    MAT.volume,
    MAT.dimension_hr,
    MAT.dimension_hr_unit,
    MAT.dimension_vt,
    MAT.dimension_vt_unit,
    MAT.dimension_ht,
    MAT.dimension_ht_unit,
    MAT.pack_type,
    MAT.pack_qty,
    MAT.deduction_type,
    MAT.fifo_flag,
    MAT.delete_flag,
    MAT.delete_user_id,
    MAT.delete_time,
    MAT.create_user_id,
    MAT.create_time,
    MAT.update_user_id,
    MAT.update_time

from
	MWIPMATDEF MAT,
	MWIPPLNDEF PLN

where
	MAT.factory_code = :factoryCode
	
	#if (!$Utils.isEmpty($matCode))
		and MAT.mat_code like :matCode
	#end
	#if (!$Utils.isEmpty($matDesc))
		and MAT.mat_desc like :matDesc
	#end
	#if (!$Utils.isEmpty($matShortDesc))
		and MAT.mat_short_desc like :matShortDesc
	#end
	#if (!$Utils.isEmpty($matGrp))
		and MAT.mat_grp in ($matGrp)
	#end
	#if (!$Utils.isEmpty($matType))
		and MAT.mat_grp in ($matType)
	#end
	
	and PLN.factory_code = MAT.factory_code
	and PLN.mat_code = MAT.mat_code
	and PLN.plan_month = :planMonth

order by
	MAT.mat_code