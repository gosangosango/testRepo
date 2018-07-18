SELECT 
        A.END_TIME
      , A.MAT_CODE
      , C.MAT_SHORT_DESC AS MAT_DESC
      , B.LABEL_ID
      , A.QTY
      , :labelType AS LABEL_TYPE
  FROM  MWIPLOTSTS A INNER JOIN  MINVLBLLOT B
                             ON  A.FACTORY_CODE      = B.FACTORY_CODE
					   	    AND  A.LOT_ID            = B.LABEL_ID
                            AND  B.SMALL_LABEL_ID   IS NULL
                            AND  B.BOX_NO           IS NULL
                            AND  B.LARGE_LABEL_ID   IS NULL
                            AND  B.PALLET_NO        IS NULL
                     INNER JOIN  MWIPMATDEF C
                             ON  A.FACTORY_CODE      = C.FACTORY_CODE
                            AND  A.MAT_CODE          = C.MAT_CODE
 WHERE  A.PROD_COMPLETE_FLAG = 1
   AND  A.FACTORY_CODE       = :factoryCode
   AND  A.LINE_CODE          = :lineCode
   AND  A.MAT_CODE           = :matCode
   AND  A.HOLD_FLAG          = 0
   AND  A.LOT_DEL_FLAG       = 0
 ORDER BY A.END_TIME, A.MAT_CODE, A.LOT_ID