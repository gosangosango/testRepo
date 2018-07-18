SELECT  DISTINCT 
        C.LINE_CODE
      , D.LINE_DESC
      , A.MAT_CODE
      , E.MAT_SHORT_DESC AS MAT_DESC
      , A.LABEL_ID
      , A.PACK_DATE
      , A.PACK_QTY
  FROM  MINVLBLDEF A  INNER JOIN  MINVLBLLOT B
                              ON  A.FACTORY_CODE         = B.FACTORY_CODE
						     AND  ((A.LABEL_TYPE         = 'LOT'    AND A.LABEL_ID        = B.LABEL_ID      )
                              OR   (A.LABEL_TYPE         = 'SMALL'  AND A.LABEL_ID        = B.SMALL_LABEL_ID)
                              OR   (A.LABEL_TYPE         = 'BOX'    AND A.LABEL_ID        = B.BOX_NO        )
                              OR   (A.LABEL_TYPE         = 'LARGE'  AND A.LABEL_ID        = B.LARGE_LABEL_ID)
                              OR   (A.LABEL_TYPE         = 'PALLET' AND A.LABEL_ID        = B.PALLET_NO     ))
                      INNER JOIN  MWIPLOTSTS C
                              ON  B.LABEL_ID             = C.LOT_ID
                             AND  B.FACTORY_CODE         = C.FACTORY_CODE
                      INNER JOIN  MWIPLINDEF D
                              ON  C.FACTORY_CODE         = D.FACTORY_CODE
                             AND  C.LINE_CODE            = D.LINE_CODE 
                      INNER JOIN  MWIPMATDEF E
                              ON  C.FACTORY_CODE         = E.FACTORY_CODE
                             AND  C.MAT_CODE             = E.MAT_CODE 
 WHERE  1                  = 1
   AND  A.INSP_NO         IS NULL
#if(!$Utils.isEmpty($fromDate))   
   AND  A.PACK_DATE   BETWEEN :fromDate AND :toDate
#end   
   AND  A.FACTORY_CODE     = :factoryCode
   AND  A.LABEL_TYPE       = :labelType
#if(!$Utils.isEmpty($lineCode))   
   AND  C.LINE_CODE        = :lineCode
#end   
#if(!$Utils.isEmpty($matCode))   
   AND  A.MAT_CODE         = :matCode
#end
#if(!$Utils.isEmpty($orderNo))   
   AND  A.ORDER_NO         = :orderNo
#end