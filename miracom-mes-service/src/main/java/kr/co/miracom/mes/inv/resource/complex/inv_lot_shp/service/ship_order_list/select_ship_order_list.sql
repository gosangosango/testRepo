SELECT
        A.SHIP_ORD_NO
      , A.CUSTOMER_CODE
      , B.KEY_DESC AS CUSTOMER_NAME
      , A.MAT_CODE
      , C.MAT_SHORT_DESC
      , A.SHIP_EXP_DATE
      , A.SHIP_PLAN_QTY
      , A.SHIP_QTY
      , A.DESTINATION
  FROM  MINVSHPORD A        INNER JOIN  MGCMTBLDAT B
                                    ON  A.FACTORY_CODE        = B.FACTORY_CODE
                                   AND  B.TABLE_NAME          = 'CUSTOMER_CODE'
                                   AND  A.CUSTOMER_CODE       = B.KEY_1
                            INNER JOIN  MWIPMATDEF C
                                    ON  A.FACTORY_CODE        = C.FACTORY_CODE
                                   AND  A.MAT_CODE            = C.MAT_CODE
 WHERE  A.FACTORY_CODE               = :factoryCode
   AND  A.SHIP_EXP_DATE        BETWEEN :startDate AND :endDate
#if(!$Utils.isEmpty($customerCode))
   AND  A.CUSTOMER_CODE              = :customerCode
#end
#if(!$Utils.isEmpty($matCode))
   AND  A.MAT_CODE                   = :matCode
#end
 ORDER BY A.SHIP_EXP_DATE, A.SHIP_ORD_NO